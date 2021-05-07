package net.toshimichi.packetanalyzer.gui;

public class PacketFrame extends TableFrame {
    public PacketFrame(Object packet) {
        super("Packet (" + packet.getClass().getSimpleName() + ")", new PacketTable(packet));
    }
}
