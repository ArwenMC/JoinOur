package com.arwenmc.commands;

import com.arwenmc.JoinOur;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ApplyCommand implements CommandExecutor {

    JoinOur plugin;
    public ApplyCommand(JoinOur instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (plugin.getConfig().getBoolean("enabled_commands.apply")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                TextComponent applyMSG = new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.apply.chat")));
                applyMSG.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, plugin.getConfig().getString("messages.apply.link")));
                applyMSG.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.apply.hover_text"))).create()));
                player.spigot().sendMessage(applyMSG);
            } else {
                commandSender.sendMessage(plugin.notPlayer);
            }
            return true;
        } else {
            if (plugin.getConfig().getBoolean("other.disabled_command_send_no_permission")) {
                commandSender.sendMessage(plugin.noPermission);
            } else {
                commandSender.sendMessage(plugin.commandDisabled);
            }
            return true;
        }
    }
}
