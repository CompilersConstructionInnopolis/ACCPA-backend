package com.inno.accpa.compiler;

import com.inno.accpa.compiler.utils.StandardLibraryUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.inno.accpa.compiler.utils.StandardLibraryUtils.addImportsToFile;


public class Main {
    public String main(Boolean log) {

        // user will write program into main.txt
        final String DEFAULT_PROGRAM_PATH = "code/main.txt";

        // compiler will be running run.txt
        // run.txt will contain original program from main.txt + all the code from imported libraries
        final String RUNNABLE_PROGRAM_PATH = "code/run.txt";

        File runnableFile = new File(RUNNABLE_PROGRAM_PATH);
//        final boolean LOG_DEFAULT = false;
//        final String LOG_PARAMETER_NAME = "log";
//        final String programSourcePath = args.length >= 1 ? args[0] : DEFAULT_PROGRAM_PATH;
//        boolean logging = args.length >= 2 ? LOG_PARAMETER_NAME.equals(args[1]) : LOG_DEFAULT;
        try {
            // method for adding code from imports to our run.txt file
            addImportsToFile(DEFAULT_PROGRAM_PATH, RUNNABLE_PROGRAM_PATH);

            var result = run(RUNNABLE_PROGRAM_PATH, log);
            runnableFile.delete();
            StandardLibraryUtils.usedImports = new ArrayList<>();
            removeLibraries();
            return result;
        } catch (Error | Exception error) {
            runnableFile.delete();
            StandardLibraryUtils.usedImports = new ArrayList<>();
            removeLibraries();
            return error.getMessage();
        }
    }

    public String run(final String programSourcePath, final boolean logging) throws IOException {
//        if (logging) {
//            LogMode logMode = new LogMode(programSourcePath);
//            logMode.logging();
//        } else {
            try {
                Compiler compiler = new Compiler(programSourcePath);
                return compiler.interpret();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
    }

    public static void removeLibraries() {
        File dir = new File("standard_library");
        for(File file: dir.listFiles())
            if (!file.isDirectory() && !file.getName().equals("std.txt"))
                file.delete();
    }
}
