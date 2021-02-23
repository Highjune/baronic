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
    PatientService patientService; //이거 이름 이상! patientService 인지 reservation service 인지. naming issue!

    // 환자 1명 정보 등록하기(map으로 정보받아서 그대로 map으로 service로 넘기기)
    // 객체로 말고 map으로 받아서 service로 넘긴다 -> 그런데 이게 jpa할 때도 이렇게 할 수 있는지 확인해 봐야 함
    @PostMapping("/reservation/addOnePatient")
    public void reservePatientInfo(@RequestBody Map<String, Object> params) throws Exception {
    }

    @RequestMapping("/reservation/addBok")
    public int addBok() throws Exception {
//        return this.patientService.addTestReservation("youngbin", "male", 32, "wisdom tooth");
        return this.patientService.addTestReservation(1, "bok");
    }

    @RequestMapping("/reservation/addHeo")
    public int addHeo() throws Exception {
//        return this.patientService.addTestReservation("kangjun", "male", 33, "implant");
        return this.patientService.addTestReservation(2, "Heo");
    }

    @RequestMapping("/reservation/addJoo")
    public int addJoo() throws Exception {
//        return this.patientService.addTestReservation("hosung", "male", 32, "cavity");
        return this.patientService.addTestReservation(3, "Joo");
    }

    @RequestMapping("/reservation/addKim")
    public int addKim() throws Exception {
//        return this.patientService.addTestReservation("wooyoung", "male", 33, "scaling");
        return this.patientService.addTestReservation(4, "Kim");
    }

    //@PostMapping 하면 안됨. why?
    //RequestMapping 해도 안됨. why?
    @GetMapping("/reservation/getAllReservations")
    public List<Patient> getAllReservations() throws Exception {
        return this.patientService.getAllReservations();
    }

    @GetMapping("/insertChart")
    public String insertChart(){
        int chartId = -1;
        String name = "";

        String ans = "finished";

        try {

            FileInputStream file = new FileInputStream("C:\\Users\\user\\Desktop\\baronic\\file.xls"); // 나중에 파일 이름 날짜로 변환해서 넣기
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowindex = 0;
            int columnindex = 0;

            XSSFSheet sheet = workbook.getSheetAt(0); // 시트 수 (첫번째에만 존재하므로 0을 준다), 만약 각 시트를 읽기 위해서는 for문을 한번 더 돌려준다.
            // 행의 수
            int rows = sheet.getPhysicalNumberOfRows(); // rows = 229
//            for(rowindex=0 ; rowindex<rows; rowindex++) { // 0행~228행(총 229행)
            for(rowindex=1 ; rowindex<rows; rowindex++) { // 0행~228행(총 229행)
                // 행 읽기 시작
                XSSFRow row = sheet.getRow(rowindex);
                if(row != null) {
                    // 셀의 수
//                    int cells = row.getPhysicalNumberOfCells(); // 전체 cell 구할 대
                    int cells = 3;
//                    for(columnindex=0; columnindex<=cells; columnindex++) {
                    for(columnindex=2; columnindex<=cells; columnindex++) {
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

//                            chartId = Integer.parseInt(value);
//                            System.out.println("chartId : " + chartId);
                        }
                        System.out.println("========here1=======");
                    }
                        System.out.println("========here2=======");
                }
                        System.out.println("========here3=======");
            }
                        System.out.println("========here4=======");
        } catch (Exception e) {

        }
        return ans;
    }

}