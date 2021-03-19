package com.hospital.baronic.web;

import com.hospital.baronic.domain.patient.Patient;
import com.hospital.baronic.service.PatientService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@RestController
public class PatientController {
    // @Autowired는 스프링이 관리하는 빈(Bena)을 주입 받게 해준다.
    @Autowired
    PatientService patientService; //이거 이름 이상! patientService 인지 reservation service 인지. naming issue! ==> 수정함

    // 환자 1명 정보 등록하기(map으로 정보받아서 그대로 map으로 service로 넘기기)
    // 객체로 말고 map으로 받아서 service로 넘긴다 -> 그런데 이게 jpa할 때도 이렇게 할 수 있는지 확인해 봐야 함
    @PostMapping("/reservation/addOnePatient")
    public void reservePatientInfo(@RequestBody Map<String, Object> params) throws Exception {
    }

    @RequestMapping("/reservation/addBok")
    public int addBok() throws Exception {
//        return this.patientService.insertPatientInfo("youngbin", "male", 32, "wisdom tooth");
        return this.patientService.insertPatientInfo(1, "bok");
    }

    @RequestMapping("/reservation/addHeo")
    public int addHeo() throws Exception {
//        return this.patientService.insertPatientInfo("kangjun", "male", 33, "implant");
        return this.patientService.insertPatientInfo(2, "Heo");
    }

    @RequestMapping("/reservation/addJoo")
    public int addJoo() throws Exception {
//        return this.patientService.insertPatientInfo("hosung", "male", 32, "cavity");
        return this.patientService.insertPatientInfo(3, "Joo");
    }

    @RequestMapping("/reservation/addKim")
    public int addKim() throws Exception {
//        return this.patientService.insertPatientInfo("wooyoung", "male", 33, "scaling");
        return this.patientService.insertPatientInfo(4, "Kim");
    }

    //@PostMapping 하면 안됨. why?
    //RequestMapping 해도 안됨. why?
    @GetMapping("/reservation/getAllReservations")
    public List<Patient> getAllReservations() throws Exception {
        return this.patientService.getAllReservations();
    }


    @GetMapping("/insertPatientInfo")
    public String insertPatientInfo(){

//        int chartId = -1;
//        String name = "";

        String ans = "insert PatientInfo finished";

        try {
            // 나중에 파일이름 자동으로 할 때
//            Date nowDate = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//            String strNowDate = simpleDateFormat.format(nowDate);
//            String fileName = strNowDate + "_진료일검색.xls";

            // 수동 파일이름(이전 날짜든지 ..)
//            FileInputStream file = new FileInputStream("C:\\Users\\highj\\OneDrive\\바탕 화면\\baronic\\20210115_진료일검색.xls"); // 집pc 경로
            FileInputStream file = new FileInputStream("C:\\Users\\user\\Desktop\\baronic\\20210115_진료일검색.xls"); // 회사pc 경로
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowindex = 0;
            int columnindex = 0;

            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0을 준다), 만약 각 시트를 읽기 위해서는 for문을 한번 더 돌려준다.
            // 행의 수
            int rows = sheet.getPhysicalNumberOfRows(); // 229행
//            for(rowindex=0 ; rowindex<rows; rowindex++) { // 0행~228행(총 229행)
            for (rowindex = 1; rowindex < rows; rowindex++) { // 1행~228행(총 228행) - 1행부터 읽기시작 (0행 제외)
                // 행 읽기 시작
                XSSFRow row = sheet.getRow(rowindex);
                if (row != null) {

                    int chartId = -1;
                    double temp = 0;
                    String name = "";

//                    int cells = row.getPhysicalNumberOfCells(); // 전체 cell 구할 때
//                    for(columnindex=0; columnindex<=cells; columnindex++) {
                    for (columnindex = 2; columnindex <= 3; columnindex++) { // 2열부터 3열(cells) 까지만
                        // 셀 값을 읽는다.
                        XSSFCell cell = row.getCell(columnindex);
                        String value = "";
                        // 셀이 빈 값일 경우를 위한 널체크
                        if (cell == null) {
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

                            if (columnindex % 2 == 0) { // 짝수
//                                chartId = Integer.parseInt(value);
//                                chartId = (int)value;
                                temp = Double.parseDouble(value); // "18.0" -> 18.0
                                chartId = Integer.parseInt(String.valueOf(Math.round(temp)));
//                                System.out.println("chartId = " + chartId);

                            } else { // 홀수
                                name = value;
                            }
                            System.out.println(rowindex + "번 행 : " + columnindex + "번 열 값은 : " + value);
                        }
                    } // 안 for문 끝. 2~3열

                        this.patientService.insertPatientInfo(chartId, name);
                } else {
                    String row_null_error = "row = null";
                    return row_null_error;

                }
            } // 바깥 for문 끝. 228행

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

}
