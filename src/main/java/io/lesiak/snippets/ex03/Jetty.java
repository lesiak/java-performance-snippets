package io.lesiak.snippets.ex03;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Jetty {
    private static final byte[] HELLO_WORLD_BYTES = toUtf8Bytes(
            "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head><title>Hello, World!</title></head>\n" +
                    "<body>Hello, World!</body>\n" +
                    "</html>");

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new DefaultHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                baseRequest.setHandled(true);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getOutputStream().write(HELLO_WORLD_BYTES);
            }
        });
        server.start();
    }

    private static byte[] toUtf8Bytes(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }
}
