package cv.cookiev.worldscolor;

import com.github.Anon8281.universalScheduler.UniversalScheduler;
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler;
import cv.cookiev.worldscolor.commands.WorldsColorCommand;
import cv.cookiev.worldscolor.configs.WorldsColorConfig;
import cv.cookiev.worldscolor.handlers.ChatHandler;
import cv.cookiev.worldscolor.handlers.JoinHandler;
import cv.cookiev.worldscolor.placeholderapi.PlaceholderApiInitializer;
import cv.cookiev.worldscolor.services.colorchangers.ColorChangerService;
import cv.cookiev.worldscolor.services.colorchangers.ColorChangerServiceTab;
import cv.cookiev.worldscolor.services.colorchoosers.ColorChooserService;
import cv.cookiev.worldscolor.services.colorchoosers.ColorChooserServiceImpl;
import cv.cookiev.worldscolor.services.colorconverters.ColorConverterService;
import cv.cookiev.worldscolor.utils.ConfigInitializer;
import cv.cookiev.worldscolor.utils.ServicesInitializer;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class WorldsColor extends JavaPlugin {
    private static Logger logger;
    private static TaskScheduler scheduler;

    public void onEnable() {
        logger = getLogger();
        scheduler = UniversalScheduler.getScheduler(this);
        ConfigInitializer.initialize(this);
        WorldsColorConfig config = new WorldsColorConfig(this);
        ColorConverterService colorConverterService = ServicesInitializer.initializeColorConverter();
        ColorChooserServiceImpl colorChooserServiceImpl = new ColorChooserServiceImpl(config, colorConverterService);
        ColorChangerServiceTab colorChangerServiceTab = new ColorChangerServiceTab(colorChooserServiceImpl);
        registerEvents(config, colorChooserServiceImpl, colorChangerServiceTab);
        registerCommands(config);
        PlaceholderApiInitializer placeholderApiInitializer = new PlaceholderApiInitializer(logger, colorChooserServiceImpl, getDescription().getVersion());
        placeholderApiInitializer.initialize();
    }

    public static Logger getPluginLogger() {
        return logger;
    }

    public static TaskScheduler getScheduler() {
        return scheduler;
    }

    private void registerEvents(WorldsColorConfig config, ColorChooserService colorChooserService, ColorChangerService colorChangerService) {
        Bukkit.getPluginManager().registerEvents(new ChatHandler(config, colorChooserService), this);
        Bukkit.getPluginManager().registerEvents(new JoinHandler(colorChangerService), this);
    }

    private void registerCommands(WorldsColorConfig config) {
        PluginCommand worldscolorCommand = Objects.requireNonNull(getCommand("worldscolor"));
        worldscolorCommand.setExecutor(new WorldsColorCommand(config));
        worldscolorCommand.setTabCompleter(new WorldsColorCommand(config));
    }
}