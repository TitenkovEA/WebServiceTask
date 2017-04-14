package com.epam.webService.controller;

import com.epam.webService.controller.command.Command;
import com.epam.webService.controller.command.impl.Create;
import com.epam.webService.controller.command.impl.Delete;
import com.epam.webService.controller.command.impl.Read;
import com.epam.webService.controller.command.impl.Update;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private final Map<CommandName, Command> commandMap = new HashMap<>();

    CommandProvider() {
        commandMap.put(CommandName.PUT, new Create());
        commandMap.put(CommandName.GET, new Read());
        commandMap.put(CommandName.POST, new Update());
        commandMap.put(CommandName.DELETE, new Delete());
    }

    Command getCommand(String name) {
        CommandName commandName;
        Command command;

    //    try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = commandMap.get(commandName);
   //     } catch (NullPointerException | IllegalArgumentException e) {
        //    command = commandMap.get(CommandName.WRONG_REQUEST);
      //      logger.error(e);
       // }

        return command;
    }
}
