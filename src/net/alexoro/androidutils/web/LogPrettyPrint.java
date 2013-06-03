package net.alexoro.androidutils.web;

import android.util.Log;
import net.alexoro.androidutils.errors.ExceptionsHelper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

/**
 * User: alexoro
 * Date: 30.04.13
 * Time: 12:51
 */
public class LogPrettyPrint {

    protected static final String LOG_TAG = LogPrettyPrint.class.getSimpleName();

    private LogPrettyPrint() {

    }

    public static void logRequest(HttpUriRequest httpRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("Request\n");

        // build protocol, method, uri
        sb.append(httpRequest.getProtocolVersion()).append(" ")
                .append(httpRequest.getMethod()).append(" ")
                .append(httpRequest.getURI())
                .append('\n');

        // build headers
        for (Header h : httpRequest.getAllHeaders()) {
            sb.append(h.getName()).append(" = ").append(h.getValue()).append('\n');
        }

        // build body
        if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
            HttpEntityEnclosingRequestBase r = (HttpEntityEnclosingRequestBase) httpRequest;
            try {
                sb.append(EntityUtils.toString(r.getEntity(), "UTF-8"));
            } catch (Throwable t) {
                sb.append(t.getMessage()).append('\n').append(ExceptionsHelper.stacktraceToString(t));
            }
        }

        // print
        Log.d(LOG_TAG, sb.toString());
    }

    public static void logResponse(HttpResponse httpResponse, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append("Response\n");

        // build protocol, method, uri
        sb
                .append(httpResponse.getStatusLine().toString())
                .append('\n');

        // build headers
        for (Header h : httpResponse.getAllHeaders()) {
            sb.append(h.getName()).append(" = ").append(h.getValue()).append('\n');
        }

        // build body
        sb.append(body);

        // print
        Log.d(LOG_TAG, sb.toString());
    }

}