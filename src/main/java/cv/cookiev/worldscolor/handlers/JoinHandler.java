package cv.cookiev.worldscolor.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import cv.cookiev.worldscolor.services.colorchangers.ColorChangerService;

public class JoinHandler implements Listener {
    private final ColorChangerService colorChangerService;

    public JoinHandler(ColorChangerService colorChangerService) {
        this.colorChangerService = colorChangerService;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        this.colorChangerService.changeColor(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.colorChangerService.changeColor(player);
    }
}