package com.hospital.baronic.web;

import com.hospital.baronic.domain.Reservation.Reservation;
import com.hospital.baronic.service.ReservationService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/insertReservationExcelData") // column 먼저 읽기(세로로)
    public String insertReservationExcelData() {
        String ans = "insert schedule finished";

        try {
            // TODO 병원에서 생성되는 파일 PATH 넣기
//            Date todayDate = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//            String yyyyMMdd = simpleDateFormat.format(todayDate);
//            String directoryPath = "C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\"; // 파일의 위치
//            String reservationExcelFileName = "예약내역(" + yyyyMMdd + ").xls"; //  파일 이름
//            FileInputStream file = new FileInputStream(directoryPath + reservationExcelFileName);

            // TODO) 파일 이름 하드 코딩(해당하는 날짜로 이름 붙이기)
//            FileInputStream file = new FileInputStream("C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\예약내역(20210110).xls"); // 집pc 경로
            FileInputStream file = new FileInputStream("C:\\Users\\user\\Desktop\\baronic\\예약내역(20210110).xls"); // 회사pc 경로

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int columnindex = 0;
            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0), 만약 각 시트를 읽기 위해서는 for문으로 더.

            XSSFRow firstRow = sheet.getRow(0);
            int firstRowLength = firstRow.getPhysicalNumberOfCells(); // 열의 총 갯수(A~H, 총 8개)

            String reservation_date = ""; // reservation_date = day_arr + time_arr (ex. "01/10(일요일)" + "오전 10:00")

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 1) 날짜(day, "01/10일요일", "01/11(월요일)", ...) 먼저 list에 담기
            ArrayList<String> reservation_day_list = new ArrayList<String>(); // "01/10(일요일)", "01/11(월요일)", ...
            for (int i = 1 ; i <= firstRowLength ; i++) { // 열의 총 갯수
//                XSSFRow row = sheet.getRow(0); // 0행만 먼저 읽음
                String reservation_day = "";
                if (firstRow != null) {
                    XSSFCell cell = firstRow.getCell(i); // 첫번째 cell("시간") 제외라서 i=1부터
                    if (cell == null) {
                        continue;
                    } else {
                        switch(cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                reservation_day = cell.getCellFormula(); break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                reservation_day = cell.getNumericCellValue()+""; break;
                            case XSSFCell.CELL_TYPE_STRING:
                                reservation_day = cell.getStringCellValue()+""; break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                reservation_day = cell.getBooleanCellValue()+""; break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                reservation_day = cell.getErrorCellValue()+""; break;
                        }
                    }
                }
                reservation_day_list.add(reservation_day); // 담기
            } // 첫 행(날짜들)만 읽는 for문 끝

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 2) 시간(time, "오전 10:00") list에 담기
            ArrayList<String> reservation_time_list = new ArrayList<String>();

            int rows = sheet.getPhysicalNumberOfRows();
            for(int i = 1; i < rows ; i++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(0);

                String reservation_time = "";
                if (cell == null){
                    continue;
                } else {
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_FORMULA:
                            reservation_time = cell.getCellFormula();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            reservation_time = cell.getNumericCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            reservation_time = cell.getStringCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            reservation_time = cell.getBooleanCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            reservation_time = cell.getErrorCellValue() + "";
                            break;
                    }
                }
                reservation_time_list.add(reservation_time); // 담기
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 3) DB입력. 1)+2) 로 날짜+시간 만들어서 content랑 같이 DB 삽입

            // 바깥 for문 시작(열)
            for (columnindex = 1; columnindex <= firstRowLength; columnindex++) {
                int rowindex = 1;

                String todo = "";
                int chart_id = -1;
                String dump = "";
                int position = 0;

                // 안쪽 for문 시작(행)
                for (; rowindex < rows ; rowindex++) { // 총 90행
                    XSSFRow row = sheet.getRow(rowindex); // 0행 제외하고 1행부터 시작
                    XSSFCell cell = row.getCell(columnindex);

                    String reservation_cell_data = "";
                    if (cell == null) {
                        position++;
                        continue;
                    } else {
                        // 타입별로 내용 읽기
                        switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                reservation_cell_data = cell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                reservation_cell_data = cell.getNumericCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                reservation_cell_data = cell.getStringCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                reservation_cell_data = cell.getBooleanCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                reservation_cell_data = cell.getErrorCellValue() + "";
                                break;
                        }
                        System.out.println(columnindex + "번 열 " + rowindex + "번 행 값은 : " + reservation_cell_data);
                    }

                    reservation_date = reservation_day_list.get(columnindex-1) + " " + reservation_time_list.get(rowindex-1); // 01/10(일요일) 오전10:00
                    SimpleDateFormat noYearDateFormat = new SimpleDateFormat("MM/dd(E) ahh:mm");
                    Date dateTypeReservation_date  = noYearDateFormat.parse(reservation_date);

                    // 엑셀데이터에서 chart_id와 to do를 파싱하기 위한 함수
                    Reservation inputReservationData = this.reservationService.parsingReservationExcelDumpData(reservation_cell_data);
                    chart_id = inputReservationData.getChart_id();
                    todo = inputReservationData.getTodo();
                    dump = inputReservationData.getDump();

                    System.out.println("===========================================================");
                    System.out.println("chart_id : " + chart_id + ", todo : " + todo + ", dump : " +  dump + ", reservation_date : " +  dateTypeReservation_date);
                    System.out.println("===========================================================");

                    this.reservationService.insertReservationSchedule(chart_id, todo, dump, dateTypeReservation_date, position);
                    position++;

                } // 안쪽 for문 끝

            } // 바깥 for문 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}

