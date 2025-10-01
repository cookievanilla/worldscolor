package cv.cookiev.worldscolor.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import cv.cookiev.worldscolor.services.colorchoosers.ColorChooserService;

public class WorldsColorExpansion extends PlaceholderExpansion {
    private final String pluginVersion;
    private final ColorChooserService colorChooserService;

    public WorldsColorExpansion(ColorChooserService colorChooserService, String pluginVersion) {
        this.pluginVersion = pluginVersion;
        this.colorChooserService = colorChooserService;
    }

    @NotNull
    @Override
    public String getIdentifier() {
        return "worldscolor";
    }

    @NotNull
    @Override
    public String getAuthor() {
        return "cookiev";
    }

    @NotNull
    @Override
    public String getVersion() {
        return this.pluginVersion;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return null;
        if (params.equals("colored_nickname")) {
            String worldName = player.getWorld().getName();
            return this.colorChooserService.getWorldColor(worldName) + player.getName();
        }
        if (params.equals("color")) {
            String worldName = player.getWorld().getName();
            return this.colorChooserService.getWorldColor(worldName);
        }
        return null;
    }
}