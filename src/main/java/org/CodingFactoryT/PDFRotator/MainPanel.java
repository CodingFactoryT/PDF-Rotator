package org.CodingFactoryT.PDFRotator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainPanel extends JPanel {
    private PDFViewer pdfViewer = new PDFViewer();
    private JButton rotateClockwiseButton = new JButton();
    private JButton rotateCounterClockwiseButton = new JButton();

    public MainPanel() throws IOException {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;

        rotateCounterClockwiseButton.addActionListener(pdfViewer.rotateCounterClockwiseListener);
        rotateCounterClockwiseButton.setFocusPainted(false);
        InputStream rotateCounterClockwiseIconStream = new BufferedInputStream(getClass().getResourceAsStream("/icons/counterClockwiseRotation.png"));
        rotateCounterClockwiseButton.setIcon(new ImageIcon(ImageIO.read(rotateCounterClockwiseIconStream)));
        this.add(rotateCounterClockwiseButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel pdfViewerPanel = new JPanel();
        pdfViewerPanel.setLayout(new GridLayout());

        JScrollPane scrollPane = new JScrollPane(pdfViewer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null);

        pdfViewerPanel.add(scrollPane);
        this.add(pdfViewerPanel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;

        rotateClockwiseButton.addActionListener(pdfViewer.rotateClockwiseListener);
        rotateClockwiseButton.setFocusPainted(false);
        InputStream rotateClockwiseIconStream = new BufferedInputStream(getClass().getResourceAsStream("/icons/clockwiseRotation.png"));
        rotateClockwiseButton.setIcon(new ImageIcon(ImageIO.read(rotateClockwiseIconStream)));
        this.add(rotateClockwiseButton, gbc);

    }
}
