package cv.cookiev.worldscolor.configs;

import org.bukkit.ChatColor;

public enum Message {
    CONFIG_RELOAD_SUCCESSFULLY("messages.config-reload-successfully"),
    NO_ARGUMENTS("messages.errors.no-arguments");

    private static final String LOGO;
    private final String configPath;
    private String text;

    static {
        LOGO = ChatColor.translateAlternateColorCodes('&', "&8[&9Worlds&bColor&8] ");
    }

    Message(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return this.configPath;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return LOGO + ChatColor.translateAlternateColorCodes('&', this.text);
    }
}