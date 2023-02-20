package org.CodingFactoryT.PDFRotator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainFrame extends JFrame {
    public MainFrame() throws IOException {
        this.setJMenuBar(new MenuBar());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        this.setTitle("PDF Rotator");
        this.setIconImage(ImageIO.read(getClass().getResourceAsStream("/AppIcon.png")));
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new MainPanel(), BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(1150, 600));
        this.setLocationRelativeTo(null);
        this.setDropTarget(new DropTarget(this, new FileDropTarget()));
        this.setVisible(true);
    }
}
