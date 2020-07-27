package net.toshimichi.packetanalyzer;

import net.toshimichi.packetanalyzer.commands.FrameShowCommand;
import net.toshimichi.packetanalyzer.commands.MonitorToggleCommand;
import net.toshimichi.packetanalyzer.lang.Language;
import net.toshimichi.packetanalyzer.lang.PropertiesLanguage;
import net.toshimichi.packetanalyzer.utils.NativeNettyUtils;
import net.toshimichi.packetanalyzer.utils.NettyUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static final NativeNettyUtils nettyUtils = new NativeNettyUtils();
    private static Language language;
    private static Main plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    public static NettyUtils getNettyUtils() {
        return nettyUtils;
    }

    public static Language getLanguage() {
        return language;
    }

    private void copyResource(String resource, String folder) throws IOException {
        File parent = new File(getDataFolder(), folder);
        File file = new File(parent, resource);
        parent.mkdirs();
        file.createNewFile();
        try (FileOutputStream out = new FileOutputStream(file)) {
            IOUtils.copy(getResource(resource), out);
        }
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            copyResource("ja_jp.lang", "lang");
            copyResource("en_us.lang", "lang");
            String lang = getConfig().getString("lang");
            File file = new File(getDataFolder(), "lang/" + lang);
            language = new PropertiesLanguage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        plugin = this;
        getCommand("frame").setExecutor(new FrameShowCommand());
        getCommand("monitor").setExecutor(new MonitorToggleCommand());
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            nettyUtils.extract(p, "packet_monitor");
        }
    }
}
