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

    public static String PATH = "src/main/java/com/inno/accpa/compiler/code/";

    @GetMapping("/compile")
    public ResultDto compileProgram(@RequestBody ProgramDto programDto) {
        try {
            for (var tab : programDto.getTabs()) {
                var printWriter = new PrintWriter(tab.getTitle(), "UTF-8");
                printWriter.println(tab.getContent());
                printWriter.close();
            }
            return new ResultDto(main.main(programDto.getLog()), null);
        } catch (Exception e) {
            return new ResultDto(null, e.getMessage());
        }
    }
}