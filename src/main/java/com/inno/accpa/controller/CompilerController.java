package com.inno.accpa.controller;

import com.inno.accpa.compiler.Main;
import com.inno.accpa.dto.ProgramDto;
import com.inno.accpa.dto.ResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;

@RestController
public class CompilerController {

    private Main main = new Main();

    final String DEFAULT_PATH = "code/";
    final String LIBRARY_PATH = "standard_library/";

    @GetMapping("/compile")
    public ResultDto compileProgram(@RequestBody ProgramDto programDto) {
        try {
            for (var tab : programDto.getTabs()) {
                if (tab.getTitle().equals("main.txt")){
                    var printWriter = new PrintWriter(DEFAULT_PATH + tab.getTitle(), "UTF-8");
                    printWriter.println(tab.getContent());
                    printWriter.close();
                } else {
                    var printWriter = new PrintWriter(LIBRARY_PATH + tab.getTitle(), "UTF-8");
                    printWriter.println(tab.getContent());
                    printWriter.close();
                }
            }
            return new ResultDto(main.main(programDto.getLog()), null);
        } catch (Exception e) {
            return new ResultDto(null, e.getMessage());
        }
    }
}
