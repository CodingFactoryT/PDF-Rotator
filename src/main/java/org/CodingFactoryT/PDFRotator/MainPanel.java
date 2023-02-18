package org.CodingFactoryT.PDFRotator;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public static FileSelector fileSelector = new FileSelector();
    private PDFViewer pdfViewer = new PDFViewer();
    private JButton rotateClockwiseButton = new JButton("->");
    private JButton rotateCounterClockwiseButton = new JButton("<-");

    public MainPanel() {
        this.setLayout(new GridLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout());

        fileSelector.addFileChangedListener(pdfViewer);

        rotateCounterClockwiseButton.addActionListener(pdfViewer.rotateCounterClockwiseListener);
        controlPanel.add(rotateCounterClockwiseButton);
        rotateClockwiseButton.addActionListener(pdfViewer.rotateClockwiseListener);
        controlPanel.add(rotateClockwiseButton);

        this.add(controlPanel);

        JScrollPane scrollPane = new JScrollPane(pdfViewer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null);

        this.add(scrollPane);
    }
}
