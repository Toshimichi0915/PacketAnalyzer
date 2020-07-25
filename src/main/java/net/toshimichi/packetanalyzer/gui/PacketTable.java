package net.toshimichi.packetanalyzer.gui;

import javax.swing.*;
import java.awt.*;

public class PacketTable extends JTable{

    public PacketTable(Object packet) {
        setModel(new PacketTableModel(packet));
    }
}
