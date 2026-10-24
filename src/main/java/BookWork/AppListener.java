
package BookWork;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.time.LocalDateTime;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println(
                "[" + LocalDateTime.now() + "] Приложение работает!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println(
                "[" + LocalDateTime.now() + "] Приложение остановлено.");
    }
}