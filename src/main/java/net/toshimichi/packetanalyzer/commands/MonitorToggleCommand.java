package net.toshimichi.packetanalyzer.commands;

import net.toshimichi.packetanalyzer.Main;
import net.toshimichi.packetanalyzer.utils.PacketHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class MonitorToggleCommand implements CommandExecutor {

    private final Set<Object> enabled = Collections.newSetFromMap(new WeakHashMap<>());

    public MonitorToggleCommand() {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 1) {
            commandSender.sendMessage(ChatColor.RED + Main.getLanguage().get("player_not_specified"));
            return true;
        }
        Player player = Bukkit.getPlayerExact(strings[0]);
        if (player == null) {
            commandSender.sendMessage(ChatColor.RED + Main.getLanguage().get("player_offline"));
            return true;
        }

        if (enabled.contains(player)) {
            Main.getNettyUtils().extract(player, "packet_monitor");
            commandSender.sendMessage(Main.getLanguage().get("monitor_stop"));
            enabled.remove(player);
        } else {
            Main.getNettyUtils().inject(player, "packet_monitor", new PacketHandler(player));
            commandSender.sendMessage(Main.getLanguage().get("monitor_start"));
            enabled.add(player);
        }
        return true;
    }
}
