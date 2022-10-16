package com.taxiservice.controllers;

import com.taxiservice.controllers.commands.ActionFactory;
import com.taxiservice.controllers.commands.Command;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(value = "/servlet")
public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        LOGGER.info("MainServlet.init()");
        config.getServletContext().setAttribute("loggedUsers", new ConcurrentHashMap<String, HttpSession>());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("MainServlet.doGet()");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("MainServlet.doPost()");
        processRequest(req, resp);
    }

    @Override
    public void destroy() {
        LOGGER.info("MainServlet.destroy()");
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        LOGGER.debug("MainServlet.processRequest");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String page ;

        ActionFactory client = new ActionFactory();
        Command command = client.defineCommand(request);
        LOGGER.debug("Command ActionFactory page: "+command.getClass());
        page = command.execute(request, response);
        LOGGER.debug("Command return page: "+page);
        if (page != null) {
            if(page.contains("redirect")){
                response.sendRedirect(page.replace(":redirect", ""));
            }else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        }

    }



}
