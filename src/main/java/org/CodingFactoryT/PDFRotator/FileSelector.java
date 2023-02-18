package org.CodingFactoryT.PDFRotator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FileSelector extends JButton implements ActionListener {
    private static File pdf;
    private ArrayList<FileChangedListener> fileChangedListeners = new ArrayList<>();
    private static String currentFilePath;

    public FileSelector(){
        this.addActionListener(this);
        this.setText("Choose PDF");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF File (*.pdf)", "pdf", "PDF"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Select a PDF");
        fileChooser.setMultiSelectionEnabled(false);

        int returnCode = fileChooser.showOpenDialog(this);
        if(returnCode == JFileChooser.APPROVE_OPTION){
            pdf = fileChooser.getSelectedFile();
            currentFilePath = pdf.getAbsolutePath();
            fileChangedListeners.forEach((el) -> el.fileChanged(pdf));
        }
    }

    public File getFile(){
        if(pdf == null){
            return new File("");
        }
        return pdf;
    }


    public static String getCurrentFilePath() {
        return currentFilePath;
    }

    public static void setCurrentFilePath(String currentFilePath) {
        FileSelector.currentFilePath = currentFilePath;
    }

    public void addFileChangedListener(FileChangedListener fileChangedListener){
        fileChangedListeners.add(fileChangedListener);
    }

    public void removeFileChangedListener(FileChangedListener fileChangedListener){
        fileChangedListeners.remove(fileChangedListener);
    }

    public static File getCurrentFile(){
        return pdf;
    }
}
