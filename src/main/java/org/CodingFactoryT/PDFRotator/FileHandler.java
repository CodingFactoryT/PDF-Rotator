package org.CodingFactoryT.PDFRotator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

public class FileHandler {
    private static File currentFile;
    private static ArrayList<FileChangedListener> fileChangedListeners = new ArrayList<>();

    public void openFileWithFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF File (*.pdf)", "pdf", "PDF"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Select a PDF");
        fileChooser.setMultiSelectionEnabled(false);

        int returnCode = fileChooser.showOpenDialog(Main.getMainFrame());
        if(returnCode == JFileChooser.APPROVE_OPTION){
            openFile(fileChooser.getSelectedFile());
        }
    }

    public static void openFile(File file){
        invokeFileChanged(file);
    }

    public static void invokeFileChanged(File file){
        setCurrentFile(file);
        fileChangedListeners.forEach((el) -> el.fileChanged(file));
    }

    public void addFileChangedListener(FileChangedListener fileChangedListener){
        fileChangedListeners.add(fileChangedListener);
    }

    public void removeFileChangedListener(FileChangedListener fileChangedListener){
        fileChangedListeners.remove(fileChangedListener);
    }

    public static File getCurrentFile() {
        return currentFile;
    }

    public static void setCurrentFile(File currentFile) {
        FileHandler.currentFile = currentFile;
    }
}
