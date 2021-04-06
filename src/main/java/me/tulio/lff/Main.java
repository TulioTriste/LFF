package me.tulio.lff;

import lombok.Getter;
import me.tulio.lff.util.CC;
import me.tulio.lff.util.FileConfig;
import me.tulio.lff.util.command.CommandFramework;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
public class Main extends JavaPlugin {

    private CommandFramework commandFramework;
    private FileConfig mainConfig;

    @Override
    public void onEnable() {
        this.mainConfig = new FileConfig(this, "config.yml");
        commandFramework = new CommandFramework(this);
        new LFFCommand();
    }

    @Override
    public void onDisable() {}

    public static Main get() {
        return getPlugin(Main.class);
    }

    public static List<String> getMessage() {
        return Main.get().getMainConfig().getStringList("LFF");
    }
}
