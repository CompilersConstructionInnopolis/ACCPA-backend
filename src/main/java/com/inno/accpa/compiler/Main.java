package com.inno.accpa.compiler;

import java.io.IOException;

public class Main {
    public String main(Boolean log) throws IOException {
        final String DEFAULT_PATH = "code/main.txt";
//        final boolean LOG_DEFAULT = true;
//        final String LOG_PARAMETER_NAME = "log";
//        final String programSourcePath = args.length >= 1 ? args[0] : DEFAULT_PATH;
//        boolean logging = args.length >= 2 ? LOG_PARAMETER_NAME.equals(args[1]) : LOG_DEFAULT;
        return run(DEFAULT_PATH, log);
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
//    }
}
