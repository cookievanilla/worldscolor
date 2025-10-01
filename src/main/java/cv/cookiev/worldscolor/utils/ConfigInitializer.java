package cv.cookiev.worldscolor.utils;

import java.io.File;
import java.util.logging.Logger;
import cv.cookiev.worldscolor.WorldsColor;

public class ConfigInitializer {
    public static void initialize(WorldsColor plugin) {
        File config = new File(plugin.getDataFolder() + File.separator + "config.yml");
        if (config.exists())
            return;
        Logger logger = plugin.getLogger();
        logger.info("Creating config file...");
        try {
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveDefaultConfig();
            logger.info("Creating config file is DONE!");
        } catch (Exception e) {
            logger.info("Creating config file is FAILED!");
        }
    }
}