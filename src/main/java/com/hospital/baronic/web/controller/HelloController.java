package com.hospital.baronic.web.controller;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;
import java.util.Stack;

// 프로젝트가 잘 띄워지는지 확인용 컨트롤러
@RestController
public class HelloController {

    // HTTP Method인 Get의 요청을 받을 수 있는 api 만들어줌
    // @RequestMapping(method = RequestMethod.GET) 과 동일
    // 프로젝트 실행 시킨 후 http://localhost:8080/hello 들어가서 확인 ㄱㄱ
    @GetMapping("/hello")
    public String hello(){
        return "hello youngbin";
    }

}
