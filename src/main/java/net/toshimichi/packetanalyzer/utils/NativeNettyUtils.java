package net.toshimichi.packetanalyzer.utils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.bukkit.entity.Player;

public class NativeNettyUtils implements NettyUtils {

    private final NativeUtils utils = new NativeUtils();

    private Channel getChannel(Player player) {
        Object handle = utils.invoke(player, "getHandle");
        Object connection = utils.getValue(handle, "playerConnection");
        Object network = utils.getValue(connection, "networkManager");
        return (Channel) utils.getValue(network, "channel");
    }

    @Override
    public void inject(Player player, String name, ChannelHandler handler) {
        Channel channel = getChannel(player);
        extract(player, name);
        channel.pipeline().addBefore("packet_handler", name, handler);
    }

    @Override
    public void extract(Player player, String name) {
        Channel channel = getChannel(player);
        if (name == null || channel.pipeline().get(name) == null) return;
        channel.pipeline().remove(name);
    }
}
