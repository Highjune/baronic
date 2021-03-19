package com.hospital.baronic.web;

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

    @GetMapping("/insertSchedule") // column 먼저 읽기(세로로)

    public String insertSchedule() {
//        int chartId = -1;
//        String name = "";

        String ans = "insert schedule finished";

        try {

            // A) TODO 병원에서 생성되는 파일 PATH 넣기
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
//            int rowindex = 1; // 0행(day) 은 미리 따로 읽을것임
            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0), 만약 각 시트를 읽기 위해서는 for문으로 더.

            XSSFRow firstRow = sheet.getRow(0);
            int firstRowLength = firstRow.getPhysicalNumberOfCells(); // 열의 총 갯수(A~H, 8)

            String reservation_date = ""; // TODO 데이터 형식은 Date로 변환하기(DB컬럼도), reservation_date = day_arr + time_arr (ex. "01/10(일요일)" + "오전 10:00")

//            String reservation_content = "";

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 1) 날짜(day, "01/10일요일) 먼저 list에 담기
            ArrayList<String> reservation_day = new ArrayList<String>(); // "01/10(일요일)", "01/11(월요일)" ...
            for(int i = 1 ; i<=firstRowLength ; i++){ // 열의 총 갯수
//                XSSFRow row = sheet.getRow(0); // 0행만 먼저 읽음

                String value_1 = "";
                if(firstRow != null) {
                    XSSFCell cell = firstRow.getCell(i); // 첫번째 cell("시간") 제외라서 i=1부터
                    if(cell == null) {
                        continue;
                    } else {
                        switch(cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value_1=cell.getCellFormula(); break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                value_1=cell.getNumericCellValue()+""; break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value_1=cell.getStringCellValue()+""; break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                value_1=cell.getBooleanCellValue()+""; break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value_1=cell.getErrorCellValue()+""; break;
                        }
                    }
                }
                reservation_day.add(value_1); // 담기
            } // 1행(날짜들)만 읽는 for문 끝
            // 잘 들어갔는지 확인차 출력
            System.out.println("===========day start==========");
            for(int i = 0 ; i < reservation_day.size() ; i++){
                System.out.println(reservation_day.get(i));
            }
            System.out.println("===========day start==========");

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 2) 시간(time, "오전 10:00") list에 담기
            ArrayList<String> reservation_time = new ArrayList<String>();

            int rows = sheet.getPhysicalNumberOfRows();
            for(int i = 1; i < rows ; i++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(0);

                String value_2 = "";
                if (cell == null){
                    continue;
                } else {
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_FORMULA:
                            value_2 = cell.getCellFormula();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            value_2 = cell.getNumericCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            value_2 = cell.getStringCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            value_2 = cell.getBooleanCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value_2 = cell.getErrorCellValue() + "";
                            break;
                    }
                }
                reservation_time.add(value_2); // 담기
            }
            // 잘 들어갔는지 확인차 출력
            System.out.println("===========time start==========");
            for(int i = 0 ; i < reservation_time.size() ; i++){
                System.out.println(reservation_time.get(i));
            }
            System.out.println("===========time end===========");


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 3) DB입력. 1)+2) 로 날짜+시간 만들어서 content랑 같이 DB 삽입

            // 바깥 for문 시작(열)
            for (columnindex = 1; columnindex <= firstRowLength; columnindex++) {
                int rowindex = 1;

                String reservation_content = "";

                // 안쪽 for문 시작(행)
                for (; rowindex < rows ; rowindex++) { // 총 90행 // Q) < or <=
                    XSSFRow row = sheet.getRow(rowindex); // 0행 제외하고 1행부터 시작
                    XSSFCell cell = row.getCell(columnindex);

                    String value = "";
                    if (cell == null) {
//                        value = "no_data"; // TODO) 이거 해결해야 됨. 공백도 데이터로 인식하게 해야 시간이랑 매칭이 맞음(안 그러면 밀림)

                        continue;
                    } else {
                        // 타입별로 내용 읽기
                        switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                value = cell.getNumericCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                value = cell.getBooleanCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value = cell.getErrorCellValue() + "";
                                break;
                        }
                        System.out.println(columnindex + "번 열 " + rowindex + "번 행 값은 : " + value);
                    }

                    reservation_date = reservation_day.get(columnindex-1) + " " + reservation_time.get(rowindex-1); // 01/10(일요일) 오전10:00
                    reservation_content = value;

                    SimpleDateFormat noYearDateFormat = new SimpleDateFormat("MM/dd(E) ahh:mm");
                    Date dateTypeReservation_date  = noYearDateFormat.parse(reservation_date);

                    System.out.println("===========================================================");
                    System.out.println(reservation_date + "----here---" + reservation_content);
                    System.out.println("===========================================================");
//                    this.reservationService.insertReservationSchedule(reservation_date, reservation_content);
                    this.reservationService.insertReservationSchedule(dateTypeReservation_date, reservation_content);

                } // 안쪽 for문 끝
//                    System.out.println("===========================================================");
//                    System.out.println(reservation_date + "---" + reservation_content);
//                    System.out.println("===========================================================");
//                    this.reservationService.insertReservationSchedule(reservation_date, reservation_content);

            } // 바깥 for문 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}

