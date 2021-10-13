package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketDetail;

import java.awt.Frame;

public interface PacketListener {
    void addPacket(PacketDetail e);

    Frame getFrame();
}
