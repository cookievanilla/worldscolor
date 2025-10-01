package cv.cookiev.worldscolor.services.colorchangers;

import org.bukkit.entity.Player;
import cv.cookiev.worldscolor.services.colorchoosers.ColorChooserService;

public class ColorChangerServiceTab implements ColorChangerService {
    private final ColorChooserService colorChooserService;

    public ColorChangerServiceTab(ColorChooserService colorChooserService) {
        this.colorChooserService = colorChooserService;
    }

    @Override
    public void changeColor(Player player) {
        String worldName = player.getWorld().getName();
        String playerName = player.getName();
        player.setPlayerListName(this.colorChooserService.getWorldColor(worldName) + playerName);
    }
}