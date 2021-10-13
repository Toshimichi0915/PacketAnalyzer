package net.toshimichi.packetanalyzer.gui;

import javax.swing.JTable;

public class PacketTable extends JTable {

    public PacketTable(Object packet) {
        setModel(new PacketTableModel(packet));
    }
}
