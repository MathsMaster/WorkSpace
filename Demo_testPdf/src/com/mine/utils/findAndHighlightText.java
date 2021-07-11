package com.mine.utils;

import com.spire.pdf.*;
import com.spire.pdf.general.find.PdfTextFind;

public class findAndHighlightText {
    public static void main(String[] args) throws Exception{
        //Load the document from disk
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2019年考研英语一真题.pdf");

        PdfTextFind[] result = null;
        for (Object pageObj : pdf.getPages()) {
            PdfPageBase page =(PdfPageBase)pageObj;
            // Find text
            result = page.findText("science", false).getFinds();
            for (PdfTextFind find : result) {
                // Highlight searched text
                find.applyHighLight();
            }
        }

        String output = "output/findAndHighlightText.pdf";
        //Save the document
        pdf.saveToFile(output, FileFormat.PDF);
    }
}
