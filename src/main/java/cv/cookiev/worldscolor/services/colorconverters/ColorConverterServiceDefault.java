package cv.cookiev.worldscolor.services.colorconverters;

import org.bukkit.ChatColor;

public class ColorConverterServiceDefault implements ColorConverterService {
    @Override
    public String convertColor(String color) throws IllegalArgumentException {
        try {
            return ChatColor.valueOf(color) + "";
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("No color for code \"" + color + "\"");
        }
    }
}