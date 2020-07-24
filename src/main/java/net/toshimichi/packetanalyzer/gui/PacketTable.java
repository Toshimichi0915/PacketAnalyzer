package net.toshimichi.packetanalyzer.gui;

import javax.swing.*;
import java.awt.*;

public class PacketTable extends JTable {

    private final PacketTableModel model;

    public PacketTable() {
        model = new PacketTableModel();
        Font font = new Font("Meiryo", Font.PLAIN, 12);
        setFont(font);
        getTableHeader().setFont(font);
        setModel(model);
    }

    public void addPacket(PacketDetail packet) {
        model.addPacket(packet);
    }
}
