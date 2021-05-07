package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketDetail;

import javax.swing.JTable;

public class MonitorTable extends JTable {

    private final MonitorTableModel model;

    public MonitorTable() {
        model = new MonitorTableModel();
        setModel(model);
        addMouseListener(new MonitorTableListener(this));
    }

    @Override
    public MonitorTableModel getModel() {
        return model;
    }

    public void addPacket(PacketDetail packet) {
        model.addPacket(packet);
    }
}
