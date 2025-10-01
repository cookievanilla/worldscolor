package cv.cookiev.worldscolor.utils;

import java.util.Arrays;
import java.util.Objects;
import org.bukkit.Bukkit;
import cv.cookiev.worldscolor.services.colorconverters.ColorConverterService;
import cv.cookiev.worldscolor.services.colorconverters.ColorConverterServiceDefault;
import cv.cookiev.worldscolor.services.colorconverters.ColorConverterServiceHex;

public class ServicesInitializer {
    public static ColorConverterService initializeColorConverter() {
        if (checkStringForContaining(Bukkit.getVersion(), new String[]{"1.12", "1.13", "1.14", "1.15"})) {
            return new ColorConverterServiceDefault();
        }
        return new ColorConverterServiceHex();
    }

    private static boolean checkStringForContaining(String source, String[] words) {
        Objects.requireNonNull(source);
        return Arrays.stream(words).anyMatch(source::contains);
    }
}