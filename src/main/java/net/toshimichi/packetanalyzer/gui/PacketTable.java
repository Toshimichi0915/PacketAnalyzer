package net.toshimichi.packetanalyzer.gui;

import javax.swing.*;

public class PacketTable extends JTable {

    private final PacketTableModel model;

    public PacketTable() {
        model = new PacketTableModel();
        setModel(model);
    }

    public void addPacket(PacketDetail packet) {
        model.addPacket(packet);
    }
}
