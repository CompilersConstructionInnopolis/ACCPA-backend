package com.inno.accpa.controller;

import com.inno.accpa.compiler.Main;
import com.inno.accpa.dto.ProgramDto;
import com.inno.accpa.dto.ResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.PrintWriter;

@RestController
public class CompilerController {

    private Main main = new Main();

    public static String PATH = "code/";

    @GetMapping("/compile")
    public ResultDto compileProgram(@RequestBody ProgramDto programDto) {
        try {
            for (var tab : programDto.getTabs()) {
                var printWriter = new PrintWriter(PATH + tab.getTitle(), "UTF-8");
                printWriter.println(tab.getContent());
                printWriter.close();
            }
            var result = main.main(programDto.getLog());
//            for (var tab : programDto.getTabs()) {
//                File myObj = new File(PATH + tab.getTitle());
//                myObj.delete();
//            }
            return new ResultDto(result, null);
        } catch (Exception e) {
//            for (var tab : programDto.getTabs()) {
//                File myObj = new File(PATH + tab.getTitle());
//                myObj.delete();
//            }
            return new ResultDto(null, e.getMessage());
        }
    }
}
