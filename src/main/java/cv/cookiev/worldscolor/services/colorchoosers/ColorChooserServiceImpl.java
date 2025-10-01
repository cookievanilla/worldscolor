package cv.cookiev.worldscolor.services.colorchoosers;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import cv.cookiev.worldscolor.WorldsColor;
import cv.cookiev.worldscolor.configs.WorldsColorConfig;
import cv.cookiev.worldscolor.services.colorconverters.ColorConverterService;

public class ColorChooserServiceImpl implements ColorChooserService {
    private final WorldsColorConfig config;
    private final ColorConverterService colorConverterService;
    private final Map<String, String> worldsColors;
    private final Logger logger;

    public ColorChooserServiceImpl(WorldsColorConfig configuration, ColorConverterService colorConverterService) {
        this.config = configuration;
        this.colorConverterService = colorConverterService;
        this.worldsColors = new HashMap<>();
        this.logger = WorldsColor.getPluginLogger();
        initializeColors();
    }

    @Override
    public String getWorldColor(String worldName) {
        return this.worldsColors.get(worldName);
    }

    private void initializeColors() {
        for (String worldName : this.config.getWorlds()) {
            String color = "";
            try {
                color = this.colorConverterService.convertColor(this.config.getColorNameForWorld(worldName));
            } catch (IllegalArgumentException exception) {
                this.logger.warning("Color code for world \"" + worldName + "\" is wrong or your server doesn't support HEX colors (1.16+)");
            }
            this.worldsColors.put(worldName, color);
        }
    }
}