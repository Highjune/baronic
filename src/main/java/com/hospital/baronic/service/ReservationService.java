package com.hospital.baronic.service;

import com.hospital.baronic.domain.reservation.Reservation;
import com.hospital.baronic.domain.reservation.ReservationMapper;
import com.hospital.baronic.web.dto.ReservationResponseDto;
import com.hospital.baronic.web.dto.ReservationSaveRequestDto;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    public String parseReservationExcelData() {
        String ans = "";
        try {
            // TODO 병원에서 생성되는 파일 PATH 넣기
//            Date todayDate = new Date();
////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
////            String yyyyMMdd = simpleDateFormat.format(todayDate);
////            String directoryPath = "C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\"; // 파일의 위치
////            String reservationExcelFileName = "예약내역(" + yyyyMMdd + ").xls"; //  파일 이름
////            FileInputStream file = new FileInputStream(directoryPath + reservationExcelFileName);

            // TODO) 파일 이름 하드 코딩(해당하는 날짜로 이름 붙이기)
//            FileInputStream file = new FileInputStream("C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\예약내역(20210110).xls"); // 집pc 경로
            FileInputStream file = new FileInputStream("C:\\Users\\user\\Desktop\\baronic\\예약내역(20210110).xls"); // 회사pc 경로

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int columnindex = 0;
            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0), 만약 여러 시트를 읽기 위해서는 for문으로.

            XSSFRow firstRow = sheet.getRow(0);
            int firstRowLength = firstRow.getPhysicalNumberOfCells(); // 열의 총 갯수(A~H, 총 8개)
            int rows = sheet.getPhysicalNumberOfRows();

            String reservation_date = ""; // reservation_date = day_arr + time_arr (ex. "01/10(일요일)" + "오전 10:00")

            // 1) 날짜(day, "01/10일요일", "01/11(월요일)", ...) list에 담기
            ArrayList<String> reservation_day_list = ReservationService.readDayFromExcelData(firstRow); // "01/10(일요일)", "01/11(월요일)", ...

            // 2) 시간(time, "오전 10:00") list에 담기
            ArrayList<String> reservation_time_list = ReservationService.readTimeFromExcelData(sheet);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 3) DB입력. 1)+2) 로 날짜+시간 만들어서 content랑 같이 DB 삽입

            // 바깥 for문 시작(열)
            for (columnindex = 1; columnindex <= firstRowLength ; columnindex++) {
                int rowindex = 1;

                String patient_name = "";
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

                    reservation_date = reservation_day_list.get(columnindex-1) + " " + reservation_time_list.get(rowindex-1); // 문자열 타입 "01/10(일요일) 오전10:00"
                    SimpleDateFormat noYearDateFormat = new SimpleDateFormat("MM/dd(E) ahh:mm");
                    Date dateTypeReservation_date  = noYearDateFormat.parse(reservation_date); // Date 타입 "01/10(일요일) 오전10:00"

                    // 엑셀데이터에서 chart_id와 to do를 파싱하기 위한 함수
                    Reservation inputReservationData = ReservationService.parsingReservationExcelDumpData(reservation_cell_data);
                    patient_name = inputReservationData.getPatient_name();
                    chart_id = inputReservationData.getChart_id();
                    todo = inputReservationData.getTodo();
                    dump = inputReservationData.getDump();

                    System.out.println("===========================================================");
                    System.out.println("chart_id : " + chart_id + ", patient_name : " + patient_name + ", todo : " + todo + ", dump : " +  dump + ", reservation_date : " +  dateTypeReservation_date);
                    System.out.println("===========================================================");

                    ReservationSaveRequestDto reservationSaveRequestDto = new ReservationSaveRequestDto(patient_name, chart_id, todo, dump, dateTypeReservation_date, position);
                    this.reservationMapper.insertReservationExcelData(reservationSaveRequestDto);
                    // this.reservationService.insertReservationSchedule(patient_name, chart_id, todo, dump, dateTypeReservation_date, position);

                    position++;
                    if (!reservation_time_list.get(rowindex).equals(reservation_time_list.get(rowindex-1))){
                        position = 0 ;
                    }

                } // 안쪽 for문 끝

            } // 바깥 for문 끝
            ans = "success";
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            ans = "failure";
            return ans;
        }
    }

    // 엑셀데이터에서 chart_id와 to do를 파싱하기 위한 함수
    public static Reservation parsingReservationExcelDumpData(String dumpData) {
        int chart_id = -1;
        String patient_name = "";
        String todo = "";
        String dump = "";
        dump = dumpData;

        // 할일(to do) 와 chart_id 파싱 시작
        int big_right_boundary = -1; // "]"
        int small_left_boundary_first = -1; // 첫번째 "("
        int small_right_boundary_first = -1; // 첫번째 ")"
        int small_left_boundary_second = -1; // 두번째 ")"
        char[] reservation_content_arr = dump.toCharArray();

        if (dump.contains("temp")) {
            // temp O
            // ex)    [주호성]잇사라펀스켈링신규.(temp1879)\r\n(정수지)
            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == '(') {
                    small_left_boundary_first = i;
//                                chart_id = Integer.parseInt(dump.substring(small_left_boundary_first+5, small_left_boundary_first+9)); // 1879. 이렇게 하면 안되는 형식이 있음.
                    chart_id = -1;
                    break;
                }
            }

            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == ']') {
                    big_right_boundary = i;
                    todo = dump.substring(big_right_boundary+1, small_left_boundary_first); // 잇사라펀스켈링신규.
                    patient_name = dump.substring(big_right_boundary+1, small_left_boundary_first); // 잇사라펀스켈링신규.
                    break;
                }
            }
//                        System.out.println("--------- temp O ---------");
//                        System.out.println("chart_id : " + chart_id + " todo : " + todo);

        } else {
            // temp X
            // ex)    [주호성]피타야콤(02448)\r\nTel:010(하악DBS+#36 B.pit)"
            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == ']') {
                    big_right_boundary = i;
                    break;
                }
            }

            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == '(') {
                    small_left_boundary_first = i;
                    break;
                }
            }

            for (int i = 0 ; i < reservation_content_arr.length ; i++) {
                if (reservation_content_arr[i] == ')') {
                    small_right_boundary_first = i;
                    break;
                }
            }

            try {
                patient_name = dump.substring(big_right_boundary + 1, small_left_boundary_first);
            } catch (NumberFormatException e) {
                patient_name = "error_name";
            }

            try {
                chart_id = Integer.parseInt(dump.substring(small_left_boundary_first + 1, small_right_boundary_first));
            } catch (NumberFormatException e) {
                chart_id = -1;
            }

            String reservation_content_after = "";
            reservation_content_after = dump.substring(small_left_boundary_first+1);
            char[] reservation_content_arr_after = reservation_content_after.toCharArray();

            for (int i = 0 ; i < reservation_content_arr_after.length ; i++) {
                if (reservation_content_arr_after[i] == '(') {
                    small_left_boundary_second = i;
                    break;
                }
                reservation_content_after.substring(small_left_boundary_second+1);
            }

            todo = reservation_content_after.substring(small_left_boundary_second+1, reservation_content_after.length()-2);
//                        System.out.println("---------temp X ---------");
//                        System.out.println("chard_id : " + chart_id + " todo : " + todo);
        }

        Reservation inputReservationData = new Reservation(patient_name, chart_id, todo, dump);
//        inputReservationData.builder()
//                .chart_id(chart_id)
//                .todo(todo)
//                .dump(dump)
//                .build();

        return inputReservationData;
    }

    public static ArrayList<String> readDayFromExcelData(XSSFRow firstRow) throws Exception {
        ArrayList<String> reservation_day_list = new ArrayList<String>();

        int firstRowLength = firstRow.getPhysicalNumberOfCells(); // 열의 총 갯수(A~H, 총 8개)

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
        }
        // 첫 행(날짜들)만 읽는 for문 끝
        return reservation_day_list;
    }

    public static ArrayList<String> readTimeFromExcelData(XSSFSheet sheet) throws Exception {

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

        return reservation_time_list;
    }

    public List<ReservationResponseDto> getAllReservationList() throws Exception {
        return this.reservationMapper.getAllReservationList();
    }
}


