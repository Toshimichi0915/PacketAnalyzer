package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketBound;
import net.toshimichi.packetanalyzer.packet.PacketDetail;

import java.awt.Frame;

public class MonitorFrame1 extends TableFrame implements PacketListener {

    private final PacketBound bound;

    public MonitorFrame1(PacketBound bound) {
        super("Packet Monitor (" + bound + ")", new MonitorTable());
        this.bound = bound;
    }

    @Override
    public void addPacket(PacketDetail e) {
        if (e.getBound() == bound)
            ((MonitorTable) getTable()).addPacket(e);
    }

    @Override
    public Frame getFrame() {
        return this;
    }
}
