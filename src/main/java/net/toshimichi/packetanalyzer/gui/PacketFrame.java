package net.toshimichi.packetanalyzer.gui;

import javax.swing.*;
import java.awt.*;

public class PacketFrame extends TableFrame {
    public PacketFrame(Object packet) {
        super("Packet (" + packet.getClass().getSimpleName() + ")", new PacketTable(packet));
    }
}
