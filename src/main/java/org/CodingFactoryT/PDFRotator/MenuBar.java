package org.CodingFactoryT.PDFRotator;

import javax.swing.*;
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

        /*JMenuItem saveAsItem = new JMenuItem("SaveAs");
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        saveAsItem.addActionListener(e -> {
            if(FileHandler.getCurrentFile() == null) {
                return;
            }

            JFileChooser directoryChooser = new JFileChooser();

            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            directoryChooser.setAcceptAllFileFilterUsed(false);
            directoryChooser.setDialogTitle("Select a directory to save your PDF");
            directoryChooser.setMultiSelectionEnabled(false);

            int returnCode = directoryChooser.showOpenDialog(this);
            if(returnCode == JFileChooser.APPROVE_OPTION){
                File pdf = directoryChooser.getSelectedFile();
                FileSelector.setCurrentFilePath(pdf.getAbsolutePath());
                PDFViewer.saveFile(FileSelector.getCurrentFilePath());
            }
        });
*/
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        //fileMenu.add(saveAsItem);

        this.add(fileMenu);
    }
}
