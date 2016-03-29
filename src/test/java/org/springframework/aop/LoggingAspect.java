package org.springframework.aop;

import java.io.PrintStream;

public class LoggingAspect {

    private PrintStream stream;

    public LoggingAspect(PrintStream stream) {
        this.stream = stream;
    }

    public void logBefore() {
        stream.println("logged before");
    }

    public void logAfter() {
        stream.println("logged after");
    }

}
