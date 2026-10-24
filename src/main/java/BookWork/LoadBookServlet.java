package BookWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/load-book")
@MultipartConfig
public class LoadBookServlet extends HttpServlet {
    private Path uploadDirectory;

    @Override
    public void init() throws ServletException {
        String realPath = getServletContext().getRealPath("/books");
        if (realPath == null) {
            throw new ServletException("Сервер не предоставляет реальный путь к папке books");
        }

        uploadDirectory = Paths.get(realPath);

        try {
            Files.createDirectories(uploadDirectory);
        } catch (IOException e) {
            throw new ServletException("Не удалось создать папку для книг", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/LoadBook.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("book");

        if (part == null
                || part.getSubmittedFileName() == null
                || part.getSize() == 0) {
            response.sendError(400, "Файл не выбран");
            return;
        }

        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

        if (fileName.isBlank()) {
            response.sendError(400, "Некорректное имя файла");
            return;
        }

        Path destination = uploadDirectory.resolve(fileName).normalize();

        if (!destination.getParent().equals(uploadDirectory)) {
            response.sendError(400, "Некорректное имя файла");
            return;
        }

        try (var input = part.getInputStream()) {
            Files.copy(input, destination, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().println("Книга успешно загружена: " + fileName);
    }
}