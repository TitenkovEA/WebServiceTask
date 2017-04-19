package com.epam.webservice.controller;

import com.epam.webservice.controller.command.Command;
import com.epam.webservice.controller.command.impl.Create;
import com.epam.webservice.controller.command.impl.Delete;
import com.epam.webservice.controller.command.impl.Read;
import com.epam.webservice.controller.command.impl.Update;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
class CommandProvider {
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

        commandName = CommandName.valueOf(name.toUpperCase());
        command = commandMap.get(commandName);

        return command;
    }
}
