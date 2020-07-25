package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketDetail;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Collection;

public class PacketTimeTable extends JTable {

    private final PacketTimeTableModel model;

    public PacketTimeTable(Collection<PacketDetail> packets) {
        this.model = new PacketTimeTableModel(packets);
        setModel(model);
        addMouseListener(new PacketTimeTableListener(this));
    }

    @Override
    public PacketTimeTableModel getModel() {
        return model;
    }
}
