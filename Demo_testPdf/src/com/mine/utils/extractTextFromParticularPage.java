package com.mine.utils;

import com.spire.pdf.*;
import java.io.*;

public class extractTextFromParticularPage {
    public static void main(String[] args) throws Exception{
        //Create a Pdf file
        PdfDocument doc = new PdfDocument();

        //Load the file from disk
        doc.loadFromFile("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2019年考研英语一真题.pdf");

        //Create a new txt file to save the extracted text
        String result = "output/extractTextFromParticularPage.txt";
        File file=new File(result);
        if(!file.exists()){
            file.delete();
        }
        file.createNewFile();
        FileWriter fw=new FileWriter(file,true);
        BufferedWriter bw=new BufferedWriter(fw);

        // Get the first page
        PdfPageBase page = doc.getPages().get(0);

        // Extract text from page keeping white space
        String text = page.extractText(true);

        // Extract text from page without keeping white space
        //String text = page.extractText(false);

        bw.write(text);

        bw.flush();
        bw.close();
        fw.close();
    }
}
