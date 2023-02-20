package org.CodingFactoryT.PDFRotator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MenuBar extends JMenuBar {
    public MenuBar(){
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JMenu fileMenu = new JMenu("File");
        fileMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fileMenu.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke("control O"));
        openItem.addActionListener(e -> {
            PDFViewer.fileHandler.openFileWithFileChooser();
        });

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
        saveItem.addActionListener(e -> {
            File currentFile = FileHandler.getCurrentFile();
            if(currentFile != null){
                PDFViewer.saveFile(currentFile.getAbsolutePath());
            }
        });

        JMenuItem saveAsItem = new JMenuItem("SaveAs");
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        saveAsItem.addActionListener(e -> {
            if(FileHandler.getCurrentFile() == null) {
                return;
            }

            JFileChooser directoryChooser = new JFileChooser();

            directoryChooser.setDialogTitle("Select a directory to save your PDF");
            directoryChooser.setAcceptAllFileFilterUsed(false);
            directoryChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF File", "pdf", "PDF"));
            directoryChooser.setMultiSelectionEnabled(false);

            int returnCode = directoryChooser.showOpenDialog(this);
            if(returnCode == JFileChooser.APPROVE_OPTION){
                File pdf = directoryChooser.getSelectedFile();
                if(!pdf.getName().toLowerCase().endsWith(".pdf")){
                    pdf = new File(pdf.getAbsolutePath() + ".pdf");
                }
                FileHandler.setCurrentFile(pdf);
                PDFViewer.saveFile(FileHandler.getCurrentFile().getAbsolutePath());
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);

        this.add(fileMenu);
    }
}
