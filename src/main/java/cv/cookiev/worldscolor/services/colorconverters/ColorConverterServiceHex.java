package cv.cookiev.worldscolor.services.colorconverters;

import net.md_5.bungee.api.ChatColor;

public class ColorConverterServiceHex implements ColorConverterService {
    @Override
    public String convertColor(String color) throws IllegalArgumentException {
        try {
            return ChatColor.of(color) + "";
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("No color for code \"" + color + "\"");
        }
    }
}