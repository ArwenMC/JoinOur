package com.arwenmc;

import com.arwenmc.commands.DiscordCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinOur extends JavaPlugin {

    FileConfiguration config = this.getConfig();
    PluginManager pluginManager = this.getServer().getPluginManager();

    public Permission joinourAdmin = new Permission("joinour.admin");

    Permission[] permissions = {
            joinourAdmin
    };

    public String noPermission = ChatColor.translateAlternateColorCodes('&', this.config.getString("messages.general.no_permission"));
    public String notPlayer = ChatColor.translateAlternateColorCodes('&', this.config.getString("messages.general.not_player"));
    public String commandDisabled = ChatColor.translateAlternateColorCodes('&', this.config.getString("messages.command_disabled"));

    @Override
    public void onEnable() {
        config.options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        for (Permission permission : permissions) {
            pluginManager.addPermission(permission);
        }

        getCommand("joinour").setExecutor(new CommandExecutor() {
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (sender.hasPermission(joinourAdmin)) {
                    switch (args[0]) {
                        case "reload":
                            sender.sendMessage(ChatColor.GREEN + "Attempting to reload the configuration file.");
                            reloadConfig();
                            sender.sendMessage(ChatColor.GREEN + "Successfully reloaded the config.");
                            return true;
                        default:
                            sender.sendMessage(ChatColor.RED + "Subcommand not found.");
                            return false;
                    }
                } else {
                    sender.sendMessage(noPermission);
                    return true;
                }
            }
        });

        getCommand("discord").setExecutor(new DiscordCommand(this));

    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
