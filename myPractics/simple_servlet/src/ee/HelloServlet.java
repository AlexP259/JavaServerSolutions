package ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/* у нас этот файл, а вернее его скомпилированная в байт-код версия, лежит внутри tomcat\helloWorld\WEB-INF.
Внутри папки WEB-INF обычно находится файл web.xml, который служит файлом развертывания. Этот файл содержит
информацию о сервлетах, их маппингах (URL, по которым они доступны), параметрах инициализации и других конфигурациях
*/


public class HelloServlet extends HttpServlet {
    // метода main у сервлетов нет. Метод main не является частью жизненного цикла сервлета, поскольку сервлеты
    // запускаются контейнером сервлетов (например, Apache Tomcat) и управляются им.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Hello world!");
    }
}
