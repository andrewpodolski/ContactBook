package com.andrew;

import com.andrew.commands.Command;
import com.andrew.commands.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/*")
@MultipartConfig
public class FrontController extends HttpServlet {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String commandName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        CommandFactory commandFactory = new CommandFactory();
        if (!commandName.isEmpty()) {
            Command command = commandFactory.createCommand(commandName);
            command.execute(req, resp);
        }
        logger.info("Command - " + commandName);
    }
}
