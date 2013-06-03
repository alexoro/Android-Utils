package net.alexoro.androidutils.errors;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * User: alexoro
 * Date: 04.06.13
 * Time: 2:06
 */
public class ExceptionsHelper {

    private ExceptionsHelper() {
    }

    public static String stacktraceToString(Throwable t) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            String result = sw.toString(); // stack trace as a string
            pw.close();
            sw.close();
            return result;
        } catch (IOException e) {
            //MUST NOT BE HAPPEN
            throw new RuntimeException(e);
        }
    }

}