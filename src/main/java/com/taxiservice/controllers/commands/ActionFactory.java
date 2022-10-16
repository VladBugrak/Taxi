package com.taxiservice.controllers.commands;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class ActionFactory {

    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

    public Command defineCommand(HttpServletRequest request) {

        String path = request.getRequestURI();

        LOGGER.info("request_path ="+path);

//        path = path.replaceAll(".*/delivery/" , "").replaceAll(".*/delivery" , "");
        path = path.replaceAll(".*/taxiservice/" , "").replaceAll(".*/taxiservice" , "");


        if ( path.isEmpty()) {
            return CommandEnum.EMPTY.getCurrentCommand();
        }
        else
            return CommandEnum.getByPath(path.toUpperCase());
    }

}
