package com.mine.utils;

import com.spire.pdf.*;
import java.awt.geom.Rectangle2D;
import java.io.*;

public class extractTextFromSpecificArea {
    public static void main(String[] args) throws Exception {
        String input = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2019年考研英语一真题.pdf";

        //Load the PDF file
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile(input);

        //Create a new txt file to save the extracted text
        String result = "output/extractTextFromSpecificArea.txt";
        File file=new File(result);
        if(!file.exists()){
            file.delete();
        }
        file.createNewFile();
        FileWriter fw=new FileWriter(file,true);
        BufferedWriter bw=new BufferedWriter(fw);

        //Get the first page
        PdfPageBase page = pdf.getPages().get(0);

        //Extract text from a specific rectangular area within the page
        String text = page.extractText(new Rectangle2D.Float(80, 180, 500, 200));

        bw.write(text);

        bw.flush();
        bw.close();
        fw.close();
    }
}
