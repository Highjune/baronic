package com.hospital.baronic.web;

import com.hospital.baronic.service.DiagnosisService;
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
public class DiagnosisController {

    @Autowired
    DiagnosisService diagnosisService;

//    @GetMapping("/insertSchedule1") // row 먼저 읽기
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

    @GetMapping("/insertSchedule2") // column 먼저 읽기
    public String insertSchedule() {
//        int chartId = -1;
//        String name = "";

        String ans = "insert schedule finished2";

        try {

            // 수동 파일이름(이전 날짜든지 ..)
            FileInputStream file = new FileInputStream("C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\예약내역(20210110).xls");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int columnindex = 0;
            int rowindex = 0;
            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0을 준다), 만약 각 시트를 읽기 위해서는 for문을 한번 더 돌려준다.

//            String diag_day = "";
            String diag_date = ""; // M) Date로 변환하기, diag_date = day_arr + time_arr (ex. 01/10(일요일) + 오전 10:00)
            String diag_content = "";

            // 날짜(day) 먼저 컬렉션에 담기 (시간(time)은 이후에)
            ArrayList<String> diag_day = new ArrayList<String>();
            for(int i = 1 ; i<=7 ; i++){
                XSSFRow row = sheet.getRow(0); // 1행만 먼저 읽음
                String value_1 = "";
                if(row != null) {
                    XSSFCell cell = row.getCell(i);
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
                diag_day.add(value_1); // 담기
            } // 0행(날짜들)만 읽는 for문 끝
            // 잘 들어갔는지 확인차 출력
            for(int i = 0 ; i < diag_day.size() ; i++){
                System.out.println(diag_day.get(i));
            }


            ArrayList<String> diag_time = new ArrayList<String>();
            // 바깥 for문 시작(열)
            for (columnindex = 0; columnindex <= 7; columnindex++) { // 총 8열(일단 고정)

                if (columnindex == 0) { // "시간" 날림(0행 0열 날림)
                    rowindex = 1;
                } else {
                    rowindex = 0;
                }
                int rows = sheet.getPhysicalNumberOfRows(); // 90행
                // 안쪽 for문 시작(행)
                for (; rowindex < rows ; rowindex++) { // 총 90행
                    XSSFRow row = sheet.getRow(rowindex);
                    XSSFCell cell = row.getCell(columnindex);

                    String value = "";
                    if (cell == null) {
//                        value = "no_data"; // 이거 해결해야 됨. 공백도 데이터로 인식하게 해야 시간이랑 매칭이 맞음(안 그러면 밀림)
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
                    if(columnindex == 0){ // 오전 10:00, 오전 10:00 ... 넣기 (90개)
                        diag_time.add(value); // 90개
                    }

                } // 안쪽 for문 끝
                    for(int i = 0 ; i < diag_time.size() ; i++){
//                        diag_time.
                    }
                    this.diagnosisService.insertDiagnosisSchedule(diag_date, diag_content);
            } // 바깥 for문 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}

