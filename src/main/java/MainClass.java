import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.Jetty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hadoop on 6/13/16.
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        System.out.println("This is my blog!");

        Logger logger = Logger.getLogger(MainClass.class);
        logger.info("This is my blog log line");
        String getenv = System.getenv("serverport");
        System.out.println(getenv);
//        int port = Integer.parseInt(getenv);
        Server server = new Server(8027);
//        ResourceHandler resourceHandler = new ResourceHandler();
//        resourceHandler.setResourceBase(".");


        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(new ServletHolder(new HelloServlet()), "/");
        contextHandlerCollection.setHandlers(new Handler[]{servletContextHandler});
        server.setHandler(contextHandlerCollection);
        server.start();

        server.join();


    }
}

class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello Servlet!!");
        super.doGet(req, resp);
    }
}

class HelloHandler extends AbstractHandler {
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
    }
}
