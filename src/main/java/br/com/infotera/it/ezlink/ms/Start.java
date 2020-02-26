/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.ms;

import br.com.infotera.common.util.Utils;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author bruno
 */
public class Start {

    private static final String JERSEY_SERVLET_NAME = "jersey-container-servlet";
    private static Logger logger;

    static {
        try {
            logger = Logger.getLogger(WS.class.getName());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                logger.warning("AMBIENTE:" + args[0]);
                Utils.setTpAmbiente(args[0]);
            }
            new Start().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void start() throws Exception {
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8090";
        }
        logger.warning("porta:" + port);
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(server, "/");

        ServletHolder servlet = new ServletHolder(JERSEY_SERVLET_NAME,
                new ServletContainer(new JerseyConfiguration()));
        context.addServlet(servlet, "/ezlink/*");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

}
