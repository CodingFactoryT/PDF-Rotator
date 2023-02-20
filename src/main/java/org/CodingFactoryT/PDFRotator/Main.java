package org.CodingFactoryT.PDFRotator;

import java.io.IOException;

public class Main {
    private static MainFrame mainFrame;
    public static void main(String[] args) throws IOException {
        mainFrame = new MainFrame();
    }

    public static MainFrame getMainFrame(){
        return mainFrame;
    }
}