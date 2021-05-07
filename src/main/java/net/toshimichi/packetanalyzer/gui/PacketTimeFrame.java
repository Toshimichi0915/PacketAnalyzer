package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketDetail;

import java.util.Collection;

public class PacketTimeFrame extends TableFrame {
    public PacketTimeFrame(Collection<PacketDetail> packets) {
        super("Packets", new PacketTimeTable(packets));
    }
}
