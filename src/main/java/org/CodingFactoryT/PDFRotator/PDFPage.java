package org.CodingFactoryT.PDFRotator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PDFPage extends JPanel {
    public static final int BORDER_THICKNESS = 5;
    private BufferedImage pageImage;
    private double pageWidth;
    private double pageHeight;
    private PDDocument pdfDocument;
    private PDPage page;
    private int pageIndex;
    private JLabel imageLabel;

    public PDFPage(PDDocument pdfDocument, int pageIndex, int rotation){
        this.pageIndex = pageIndex;
        this.pdfDocument = pdfDocument;
        this.page = pdfDocument.getPage(pageIndex);
        page.setRotation(rotation);

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS));

        pageImage = getImage(page);
        imageLabel = new JLabel(new ImageIcon(pageImage));

        JPanel pageIndexPanel = new JPanel();
        pageIndexPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JTextField pageIndexField = new JTextField("-" + (pageIndex + 1) + "-");
        pageIndexField.setLayout(new FlowLayout());
        pageIndexPanel.add(pageIndexField);
        pageIndexField.setBorder(null);
        pageIndexField.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        pageIndexField.setOpaque(false);

        this.add(imageLabel, BorderLayout.CENTER);
        this.add(pageIndexPanel, BorderLayout.SOUTH);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) {
                PDFViewer.updateSelectedPage(pageIndex);
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
    }


    private void rotatePage(int pageIndex, int rotation){
        page.setRotation(rotation);
        imageLabel.setIcon(new ImageIcon(getImage(page)));
    }

    public void rotateClockwise(){
        rotatePage(pageIndex, page.getRotation() + 90);
    }

    public void rotateCounterClockwise(){
        rotatePage(pageIndex, page.getRotation() - 90);
    }

    private BufferedImage getImage(PDPage page){
        pageWidth = page.getMediaBox().getWidth();
        pageHeight = page.getMediaBox().getHeight();

        BufferedImage image;

        PDFRenderer documentRenderer = new PDFRenderer(pdfDocument);

        try {
            image = documentRenderer.renderImage(pageIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public PDPage getPage(){
        return page;
    }
}
