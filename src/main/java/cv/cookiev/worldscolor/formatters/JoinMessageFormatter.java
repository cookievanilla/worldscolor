package cv.cookiev.worldscolor.formatters;

import java.util.Optional;
import java.util.regex.Matcher;
import org.bukkit.ChatColor;

public class JoinMessageFormatter {
    private final String format;
    private String playerName = "";
    private String color = "";

    public JoinMessageFormatter(String format) {
        this.format = format;
    }

    public String format() {
        String formattedString = this.format.replaceAll("%player%", Matcher.quoteReplacement(this.playerName))
                .replaceAll("%color%", Matcher.quoteReplacement(this.color));
        return ChatColor.translateAlternateColorCodes('&', formattedString);
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = Optional.ofNullable(playerName).orElse("");
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = Optional.ofNullable(color).orElse("");
    }
}