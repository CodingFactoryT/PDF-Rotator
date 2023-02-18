package org.CodingFactoryT.PDFRotator;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileDropTarget implements DropTargetListener {
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent event) {
        event.acceptDrop(DnDConstants.ACTION_COPY);

        Transferable transferable = event.getTransferable();
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        for(DataFlavor flavor : flavors){
            if(flavor.isMimeTypeEqual("application/x-java-file-list")){
                try {
                    for(File file : (List<File>) transferable.getTransferData(flavor)){
                        if(file.isDirectory() || !file.getName().toLowerCase().endsWith(".pdf")){
                            return;
                        }
                        FileHandler.openFile(file);
                    }
                } catch (UnsupportedFlavorException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
