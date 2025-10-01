package cv.cookiev.worldscolor.placeholderapi;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import cv.cookiev.worldscolor.services.colorchoosers.ColorChooserService;

public class PlaceholderApiInitializer {
    private final Logger logger;
    private final ColorChooserService colorChooserService;
    private final String pluginVersion;

    public PlaceholderApiInitializer(Logger logger, ColorChooserService colorChooserService, String pluginVersion) {
        this.logger = logger;
        this.colorChooserService = colorChooserService;
        this.pluginVersion = pluginVersion;
    }

    public void initialize() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            (new WorldsColorExpansion(this.colorChooserService, this.pluginVersion)).register();
        } else {
            this.logger.info("PlaceholderAPI isn't hooked!");
        }
    }
}