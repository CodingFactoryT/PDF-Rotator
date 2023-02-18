package org.CodingFactoryT.PDFRotator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;

public class PDFViewer extends JPanel implements FileChangedListener {
    private File file;
    private PDDocument pdfDocument;
    private static ArrayList<PDFPage> pagePanels = new ArrayList<>();
    private static int selectedPageIndex = -1;  //-1 indicates that the index wasn't set yet

    public ActionListener rotateCounterClockwiseListener = e -> {
        if(selectedPageIndex < 0){
            return;
        }

        PDFPage page = pagePanels.get(selectedPageIndex);
        page.rotateCounterClockwise();
        updatePage(selectedPageIndex, page);
    };

    public ActionListener rotateClockwiseListener = e -> {
        if(selectedPageIndex < 0){
            return;
        }

        PDFPage page = pagePanels.get(selectedPageIndex);
        page.rotateClockwise();
        updatePage(selectedPageIndex, page);
    };

    public PDFViewer() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(pdfDocument != null){
                try {
                    if(FileSelector.getCurrentFile() != null){
                        saveFile(FileSelector.getCurrentFilePath());
                    }
                    pdfDocument.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Shutdown-Thread"));
    }

    @Override
    public void fileChanged(File newFile) {
        if(pdfDocument != null){
            try {
                pdfDocument.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.removeAll();

        this.file = newFile;

        try {
            pdfDocument = PDDocument.load(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        drawPages();

        if(pdfDocument.getPages().getCount() > 0){
            updateSelectedPage(0);
        }
    }

    private void drawPages(){
        PDPageTree pages = pdfDocument.getPages();
        for(int i = 0; i < pages.getCount(); i++){
            PDFPage page = new PDFPage(pdfDocument, i, pages.get(i).getRotation());
            pagePanels.add(page);
            this.add(page);
        }

        this.revalidate();
        this.repaint();
    }

    private void updatePage(int pageIndex, PDFPage page){
        this.remove(pageIndex);
        this.add(page, pageIndex);
        pagePanels.set(pageIndex, page);

        this.revalidate();
        this.repaint();
    }

    public static void updateSelectedPage(int pageIndex){
        if(pageIndex < 0) {
            return;
        }

        int borderThickness = PDFPage.BORDER_THICKNESS;

        if(selectedPageIndex >= 0){
            pagePanels.get(selectedPageIndex).setBorder(BorderFactory.createEmptyBorder(borderThickness, borderThickness, borderThickness, borderThickness));
        }
        selectedPageIndex = pageIndex;
        pagePanels.get(pageIndex).setBorder(BorderFactory.createLineBorder(Color.RED, borderThickness));
    }

    public static void saveFile(String path){
        PDDocument newDocument = new PDDocument();
        for(PDFPage page : pagePanels){
            newDocument.addPage(page.getPage());
        }
        try {
            newDocument.save(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
