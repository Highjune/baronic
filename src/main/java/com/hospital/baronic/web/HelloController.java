package com.hospital.baronic.web;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;

// 프로젝트가 잘 띄워지는지 확인용 클래스
// @RestController를 통해 일반컨트롤러 -> JSON형식 데이터로 반환 가능한 컨트롤러
@RestController
public class HelloController {

    // HTTP Method인 Get의 요청을 받을 수 있는 api 만들어줌
    // @RequestMapping(method = RequestMethod.GET) 과 동일
    // 프로젝트 실행 시킨 후 http://localhost:8080/hello 들어가서 확인 ㄱㄱ
    @GetMapping("/hello")
    public String hello(){
        return "hello youngbin";
    }

    @PostMapping("/readExcel")
    public void readExcel(){
        try {

            FileInputStream file = new FileInputStream("C:\\Users\\user\\Desktop\\baronic\\file.xls");
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowindex = 0;
            int columnindex = 0;
            // 시트 수 (첫번째에만 존재하므로 0을 준다)
            // 만약 각 시트를 읽기 위해서는 for문을 한번 더 돌려준다.
            XSSFSheet sheet = workbook.getSheetAt(0);
            // 행의 수
            int rows = sheet.getPhysicalNumberOfRows();
            for(rowindex=0 ; rowindex<rows; rowindex++) {
                // 행 읽기 시작
                XSSFRow row = sheet.getRow(rowindex);
                if(row != null) {
                    //셀의 수
                    int cells = row.getPhysicalNumberOfCells();
                    for(columnindex=0; columnindex<=cells; columnindex++) {
                        // 셀 값을 읽는다.
                        XSSFCell cell = row.getCell(columnindex);
                        String value = "";
                        // 셀이 빈 값일 경우를 위한 널체크
                        if(cell == null) {
                            continue;
                        } else {
                            // 타입별로 내용 읽기
                            switch(cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    value=cell.getCellFormula(); break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    value=cell.getNumericCellValue()+""; break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    value=cell.getStringCellValue()+""; break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    value=cell.getBooleanCellValue()+""; break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value=cell.getErrorCellValue()+""; break;
//                                case FORMULA:
//                                    value = cell.getCellFormula();
//                                    break;
//                                case NUMERIC:
//                                    value = cell.getCellFormula();
//                                    break;
//                                case STRING:
//                                    value = cell.getCellFormula();
//                                    break;
//                                case BLANK:
//                                    value = cell.getCellFormula();
//                                    break;
//                                case ERROR:
//                                    value=cell.getErrorCellValue()+"";
//                                    break;
                            }
                            System.out.println(rowindex + "번 행 : " + columnindex + "번 열 값은 : " + value);
                        }

                    }
                }

            }

        } catch (Exception e) {

        }

    }

}
