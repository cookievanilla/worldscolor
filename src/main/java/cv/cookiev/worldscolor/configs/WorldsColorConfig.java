package cv.cookiev.worldscolor.configs;

import cv.cookiev.worldscolor.WorldsColor;
import cv.cookiev.worldscolor.formatters.ChatMessageFormatter;
import cv.cookiev.worldscolor.formatters.JoinMessageFormatter;
import cv.cookiev.worldscolor.formatters.QuitMessageFormatter;
import org.bukkit.configuration.Configuration;

import java.util.Arrays;
import java.util.List;

public class WorldsColorConfig {
    private final WorldsColor plugin;
    private Configuration config;

    public WorldsColorConfig(WorldsColor plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        updateMessages();
    }

    public void reloadConfig() {
        this.plugin.reloadConfig();
        this.config = this.plugin.getConfig();
        updateMessages();
    }

    public List<String> getWorlds() {
        return this.config.getStringList("worlds");
    }

    public String getColorNameForWorld(String worldName) {
        return this.config.getString("colors." + worldName);
    }

    public boolean isColorsInChatEnabled() {
        return this.config.getBoolean("settings.colors-in-chat.enable");
    }

    public ChatMessageFormatter getChatMessageFormatter() {
        String format = this.config.getString("settings.colors-in-chat.format");
        return new ChatMessageFormatter(format);
    }

    public boolean isColorsInJoinMessageEnabled() {
        return this.config.getBoolean("settings.colors-in-connection-message.join.enable");
    }

    public JoinMessageFormatter getJoinMessageFormatter() {
        String format = this.config.getString("settings.colors-in-connection-message.join.format");
        return new JoinMessageFormatter(format);
    }

    public boolean isColorsInQuitMessageEnabled() {
        return this.config.getBoolean("settings.colors-in-connection-message.quit.enable");
    }

    public QuitMessageFormatter getQuitMessageFormatter() {
        String format = this.config.getString("settings.colors-in-connection-message.quit.format");
        return new QuitMessageFormatter(format);
    }

    private void updateMessages() {
        Arrays.stream(Message.values()).forEach(message -> {
            String textOfMessage = this.config.getString(message.getConfigPath());
            message.setText(textOfMessage);
        });
    }
}