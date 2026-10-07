import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {

        int port = 8080;

        HttpServer server = HttpServer.create(
                new InetSocketAddress(port), 0
        );

        server.createContext("/", new PageHandler());

        server.start();

        System.out.println();
        System.out.println("====================================");
        System.out.println("   PET ADOPTION PLATFORM");
        System.out.println("====================================");
        System.out.println("Server is running!");
        System.out.println("Open: http://localhost:8080");
        System.out.println("Press Ctrl+C to stop the server.");
        System.out.println();
    }

    static class PageHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String requestedPage =
                    exchange.getRequestURI().getPath();

            if (requestedPage.equals("/")) {
                requestedPage = "/index.html";
            }

            /*
             * Find the project folder automatically.
             * This allows Main.java to work even when
             * VS Code runs it from the src folder.
             */

            Path currentFolder =
                    Paths.get("").toAbsolutePath();

            Path webFolder = null;

            for (int i = 0; i < 5; i++) {

                Path possible =
                        currentFolder.resolve("web");

                if (Files.exists(possible)) {
                    webFolder = possible;
                    break;
                }

                currentFolder =
                        currentFolder.getParent();

                if (currentFolder == null) {
                    break;
                }
            }

            String html;

            if (webFolder == null) {

                html = """
                        <!DOCTYPE html>
                        <html>
                        <body>
                            <h1>Project Folder Error</h1>
                            <p>
                            Java cannot find the web folder.
                            </p>
                        </body>
                        </html>
                        """;

            } else {

                Path requestedFile =
                        webFolder.resolve(
                                requestedPage.substring(1)
                        );

                if (Files.exists(requestedFile)
                        && !Files.isDirectory(requestedFile)) {

                    html = Files.readString(
                            requestedFile,
                            StandardCharsets.UTF_8
                    );

                } else {

                    html = """
                            <!DOCTYPE html>
                            <html>
                            <head>
                                <title>404</title>
                                <style>
                                    body {
                                        font-family: Arial;
                                        text-align: center;
                                        padding: 80px;
                                    }

                                    h1 {
                                        color: #ff6b35;
                                    }

                                    a {
                                        color: #ff6b35;
                                    }
                                </style>
                            </head>

                            <body>

                                <h1>404 - Page Not Found</h1>

                                <p>
                                    The requested page does not exist.
                                </p>

                                <a href="/">
                                    ← Back to Home
                                </a>

                            </body>
                            </html>
                            """;
                }
            }

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/html; charset=UTF-8"
            );

            byte[] response =
                    html.getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(
                    200,
                    response.length
            );

            try (OutputStream output =
                         exchange.getResponseBody()) {

                output.write(response);
            }
        }
    }
}