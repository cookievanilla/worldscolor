package cv.cookiev.worldscolor.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import cv.cookiev.worldscolor.configs.Message;
import cv.cookiev.worldscolor.configs.WorldsColorConfig;

public class WorldsColorCommand implements CommandExecutor, TabCompleter {
    private final WorldsColorConfig config;

    public WorldsColorCommand(WorldsColorConfig config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length < 1) {
            sendMessageNoArguments(sender);
            return true;
        }
        if (args[0].equals("reload")) {
            reloadConfigAndSendMessage(sender);
        } else {
            sendMessageNoArguments(sender);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("reload");
            return arguments;
        }
        return new ArrayList<>();
    }

    private void sendMessageNoArguments(CommandSender recipient) {
        recipient.sendMessage(Message.NO_ARGUMENTS.toString());
    }

    private void reloadConfigAndSendMessage(CommandSender recipient) {
        this.config.reloadConfig();
        recipient.sendMessage(Message.CONFIG_RELOAD_SUCCESSFULLY.toString());
    }
}