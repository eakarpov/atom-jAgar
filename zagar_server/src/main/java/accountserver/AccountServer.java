package accountserver;

import accountserver.auth.AuthenticationFilter;
import main.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.h2.server.web.WebServlet;
import org.jetbrains.annotations.NotNull;

public class AccountServer extends Service {

    private final static @NotNull Logger log = LogManager.getLogger(AccountServer.class);
    private final int port;

    public AccountServer(int port) {
        super("account_server");
        this.port = port;
        startApi();
    }

    private void startApi() {

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");

        Server server = new Server(port);
        server.setHandler(context);

        ServletHolder h2Servlet = context.addServlet(WebServlet.class, "/h2/*");
        h2Servlet.setInitOrder(0);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(1);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "accountserver"
        );

        jerseyServlet.setInitParameter(
                "com.sun.jersey.spi.container.ContainerRequestFilters",
                AuthenticationFilter.class.getCanonicalName()
        );

        log.info(getName() + " started on port " + port);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(@NotNull String[] args) throws Exception {
        new AccountServer(8080).startApi();
    }

}
