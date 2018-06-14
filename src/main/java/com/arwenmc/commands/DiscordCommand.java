package com.arwenmc.commands;

import com.arwenmc.JoinOur;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

    JoinOur plugin;
    public DiscordCommand(JoinOur instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (plugin.getConfig().getBoolean("enabled_commands.discord")) {
            if
        } else {

        }
        return false;
    }
}
