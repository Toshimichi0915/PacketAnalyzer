package net.toshimichi.packetanalyzer.utils;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.toshimichi.packetanalyzer.events.PacketReceiveEvent;
import net.toshimichi.packetanalyzer.events.PacketSendEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PacketHandler extends ChannelDuplexHandler {
    private final Player player;

    public PacketHandler(Player player) {
        this.player = player;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        PacketSendEvent e = new PacketSendEvent(player, msg);
        Bukkit.getPluginManager().callEvent(e);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        PacketReceiveEvent e = new PacketReceiveEvent(player, msg);
        Bukkit.getPluginManager().callEvent(e);
    }
}
