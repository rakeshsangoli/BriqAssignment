package com.briq.pdfreader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
/**
 * 
 * @author rsangoli
 *
 */
public class ReadPDF extends PDFTextStripper{
	
	static List<String> lines = new ArrayList<String>();
	
	public ReadPDF() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Read PDF file
	 * @param Path
	 * @return
	 * @throws IOException 
	 */
	public List<String> readPDF(String Path) throws IOException{
		PDDocument document = null;
		try {
            document = PDDocument.load( new File(Path) );
            PDFTextStripper stripper = new ReadPDF();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 0 );
            stripper.setEndPage( document.getNumberOfPages() );
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
            
            // print lines
            
            for(String Lines : lines)
            	System.out.println(Lines);
            return lines;
        }
        finally {
            if( document != null ) {
                document.close();
            }
        }
	}
	 /**
     * Override the default functionality of PDFTextStripper.writeString()
     */
    @Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        lines.add(str);
        // you may process the line here itself, as and when it is obtained
    }
	public static void main(String[] args) {
		
	}
}

