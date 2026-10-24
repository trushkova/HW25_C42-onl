package BookWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String realPath = getServletContext().getRealPath("/books");

        if (realPath == null) {
            throw new ServletException("Не удалось определить путь к папке books");
        }

        Path booksDirectory = Paths.get(realPath);
        Files.createDirectories(booksDirectory);

        Map<String, Path> books;

        try (Stream<Path> files = Files.list(booksDirectory)) {
            books = files.filter(Files::isRegularFile)
                    .collect(Collectors.toMap(path -> path.getFileName().toString(), path -> path));
        }

        String bookName = request.getParameter("name");

        if (bookName == null || bookName.isBlank()) {
            request.setAttribute("books", books.keySet());
            request.getRequestDispatcher("/Books.jsp")
                    .forward(request, response);
            return;
        }
        Path bookPath = books.get(bookName);

        if (bookPath == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Книга не найдена");
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"book.pdf\"; filename*=UTF-8''"
                        + java.net.URLEncoder.encode(bookName, java.nio.charset.StandardCharsets.UTF_8).replace("+", "%20"));

        try (InputStream input = Files.newInputStream(bookPath);
             var output = response.getOutputStream()) {
            input.transferTo(output);
        }
    }
}