package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketBound;
import net.toshimichi.packetanalyzer.packet.PacketDetail;

public class MonitorFrame extends TableFrame {

    private final PacketBound bound;

    public MonitorFrame(PacketBound bound) {
        super("Packet Monitor (" + bound + ")", new MonitorTable());
        this.bound = bound;
    }

    public void addPacket(PacketDetail e) {
        if (e.getBound() == bound)
            ((MonitorTable) getTable()).addPacket(e);
    }
}
