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
import java.util.ArrayList;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

//    @GetMapping("/insertSchedule_old") // row 먼저 읽기(가로로)
//    public String insertSchedule(){
////        int chartId = -1;
////        String name = "";
//
//        String ans = "insert schedule finished1";
//
//        try {
//
//            FileInputStream file = new FileInputStream("C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\예약내역(20210110).xls"); // 나중에 파일 이름 날짜로 변환해서 넣기
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//            int rowindex = 0;
//            int columnindex = 0;
//
//            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0을 준다), 만약 각 시트를 읽기 위해서는 for문을 한번 더 돌려준다.
//            // 행의 수
//            int rows = sheet.getPhysicalNumberOfRows(); // 229행
//            for(rowindex=0 ; rowindex<rows; rowindex++) { // 0행~228행(총 229행)
////            for(rowindex=1 ; rowindex<rows; rowindex++) { // 1행~228행(총 228행) - 1행부터 읽기시작 (0행 제외)
//                // 행 읽기 시작
//                XSSFRow row = sheet.getRow(rowindex);
//                if(row != null) {
//
//                    // 컬럼
////                    int chartId = -1;
////                    String name = "";
//                    if(rowindex == 0) {
//                        columnindex = 1; // "시간" cell 안 읽음
//                    } else {
//                        columnindex = 0;
//                    }
//
//                    int cells = row.getPhysicalNumberOfCells(); // 전체 cell 구할 때
//                    for(;columnindex<=cells; columnindex++) {
////                    for(columnindex=0; columnindex<=cells; columnindex++) {
////                    for(columnindex=2 ; columnindex<=3 ; columnindex++) { // 2열부터 3열(cells) 까지만
//                        // 셀 값을 읽는다.
//                        XSSFCell cell = row.getCell(columnindex);
//                        String value = "";
//                        // 셀이 빈 값일 경우를 위한 널체크
//                        if(cell == null) {
//                            continue;
//                        } else {
//                            // 타입별로 내용 읽기
//                            switch(cell.getCellType()) {
//                                case XSSFCell.CELL_TYPE_FORMULA:
//                                    value=cell.getCellFormula(); break;
//                                case XSSFCell.CELL_TYPE_NUMERIC:
//                                    value=cell.getNumericCellValue()+""; break;
//                                case XSSFCell.CELL_TYPE_STRING:
//                                    value=cell.getStringCellValue()+""; break;
//                                case XSSFCell.CELL_TYPE_BLANK:
//                                    value=cell.getBooleanCellValue()+""; break;
//                                case XSSFCell.CELL_TYPE_ERROR:
//                                    value=cell.getErrorCellValue()+""; break;
//                            }
//                            System.out.println(rowindex + "번 행 : " + columnindex + "번 열 값은 : " + value);
//                        }
//                    } // 안 for문 끝.
//                } else {
//                    String row_null_error = "row = null";
//                    return row_null_error;
//                }
//            } // 바깥 for문 끝.
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ans;
//    }

    @GetMapping("/insertSchedule") // column 먼저 읽기(세로로)
    public String insertSchedule() {
//        int chartId = -1;
//        String name = "";

        String ans = "insert schedule finished";

        try {

            // M) 파일 이름 하드 코딩(해당하는 날짜로 이름 붙이기)
            FileInputStream file = new FileInputStream("C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\예약내역(20210110).xls");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int columnindex = 0;
//            int rowindex = 1; // 0행(day) 은 미리 따로 읽을것임
            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0을 준다), 만약 각 시트를 읽기 위해서는 for문을 한번 더 돌려준다.

            XSSFRow firstRow = sheet.getRow(0);
            int firstRowLength = firstRow.getPhysicalNumberOfCells(); // 열의 총 갯수

            String reservation_date = ""; // M) 데이터 형식은 Date로 변환하기(DB컬럼도), reservation_date = day_arr + time_arr (ex. "01/10(일요일)" + "오전 10:00")
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
//                        value = "no_data"; // M) 이거 해결해야 됨. 공백도 데이터로 인식하게 해야 시간이랑 매칭이 맞음(안 그러면 밀림)
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

                    reservation_date = reservation_day.get(columnindex-1) + reservation_time.get(rowindex-1);
                    reservation_content = value;

                    System.out.println("===========================================================");
                    System.out.println(reservation_date + "---" + reservation_content);
                    System.out.println("===========================================================");
                    this.reservationService.insertReservationSchedule(reservation_date, reservation_content);

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

