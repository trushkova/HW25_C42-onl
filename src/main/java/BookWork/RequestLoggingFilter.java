package BookWork;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter("/*")
public class RequestLoggingFilter implements Filter {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String time = LocalDateTime.now().format(FORMATTER);
        String method = "UNKNOWN";

        if (request instanceof jakarta.servlet.http.HttpServletRequest) {
            var httpRequest = (jakarta.servlet.http.HttpServletRequest) request;
            method = httpRequest.getMethod();
        }
        System.out.println("[" + time + "] Получен запрос. Метод: " + method);
        chain.doFilter(request, response);
    }
}