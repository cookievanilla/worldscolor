package cv.cookiev.worldscolor.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import cv.cookiev.worldscolor.configs.WorldsColorConfig;
import cv.cookiev.worldscolor.formatters.ChatMessageFormatter;
import cv.cookiev.worldscolor.formatters.JoinMessageFormatter;
import cv.cookiev.worldscolor.formatters.QuitMessageFormatter;
import cv.cookiev.worldscolor.services.colorchoosers.ColorChooserService;

public class ChatHandler implements Listener {
    private final ColorChooserService colorChooserService;
    private final WorldsColorConfig config;

    public ChatHandler(WorldsColorConfig config, ColorChooserService colorChooserService) {
        this.colorChooserService = colorChooserService;
        this.config = config;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (!this.config.isColorsInChatEnabled())
            return;
        ChatMessageFormatter chatMessageFormatter = this.config.getChatMessageFormatter();
        chatMessageFormatter.setPlayerName("%1$s");
        String worldName = event.getPlayer().getWorld().getName();
        chatMessageFormatter.setColor(this.colorChooserService.getWorldColor(worldName));
        chatMessageFormatter.setText("%2$s");
        String format = chatMessageFormatter.format();
        event.setFormat(format);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!this.config.isColorsInJoinMessageEnabled())
            return;
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();
        JoinMessageFormatter formatter = this.config.getJoinMessageFormatter();
        formatter.setPlayerName(player.getName());
        formatter.setColor(this.colorChooserService.getWorldColor(worldName));
        String resultMessage = formatter.format();
        event.setJoinMessage(resultMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!this.config.isColorsInQuitMessageEnabled())
            return;
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();
        QuitMessageFormatter formatter = this.config.getQuitMessageFormatter();
        formatter.setPlayerName(player.getName());
        formatter.setColor(this.colorChooserService.getWorldColor(worldName));
        String resultMessage = formatter.format();
        event.setQuitMessage(resultMessage);
    }
}