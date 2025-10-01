package cv.cookiev.worldscolor.formatters;

import java.util.Optional;
import java.util.regex.Matcher;
import org.bukkit.ChatColor;

public class ChatMessageFormatter {
    private final String format;
    private String playerName = "";
    private String color = "";
    private String text = "";

    public ChatMessageFormatter(String format) {
        this.format = format;
    }

    public String format() {
        String formattedString = this.format.replaceAll("%player%", Matcher.quoteReplacement(this.playerName))
                .replaceAll("%color%", Matcher.quoteReplacement(this.color))
                .replaceAll("%text%", Matcher.quoteReplacement(this.text));
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = Optional.ofNullable(text).orElse("");
    }
}