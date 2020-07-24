package net.toshimichi.packetanalyzer.utils;

import io.netty.channel.ChannelHandler;
import org.bukkit.entity.Player;

public interface NettyUtils {
    void inject(Player player, String name, ChannelHandler handler);

    void extract(Player player, String name);
}
