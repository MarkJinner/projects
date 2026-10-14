package com.gmail.mailanalyzer.main.pdfmarker;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.util.Matrix;

public class Marker {
	
	private File file;
	
	public Marker(File file) {
		this.file = file;
	}
	
	
	public Marker () {
		
	}
	
	
	public File getFile() {
		return file;
	}



	public void setFile(File file) {
		this.file = file;
	}



	public File mark(File file, String mark, File folder) throws IOException {
		 File sourceFile = new File(file.getPath());
		 System.out.println(sourceFile.getParent());
	        File destFile = new File(folder,"copy_new3.pdf");
	        
		try(PDDocument document = Loader.loadPDF(file)){
			PDPage page = document.getPage(0);
			PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
	        int fontSize = 24;
	        
	        try(PDPageContentStream contentStream = new PDPageContentStream(
	                document, page, PDPageContentStream.AppendMode.APPEND, true, true)){
	        	contentStream.beginText();
	        	contentStream.setFont(font, fontSize); 
	        	

	        	double angleInRadians = Math.toRadians(90); 

	        	float tx = 480;
	        	float ty = 550;

	        	Matrix matrix = Matrix.getRotateInstance(angleInRadians, tx, ty);
	        	contentStream.setTextMatrix(matrix);


	        	contentStream.showText(mark);
	        	contentStream.endText();
	        	System.out.println("Mark successfully put on pdf");
	        		
	        }
	        document.save(destFile);
		}
		
		
        
       catch(IOException e) {
    	   System.err.println("Ошибка при работе с файлом: " + e.getMessage());
           e.printStackTrace();
       }
		return destFile;
       
		
	}
	
	

	public static void main(String[] args) throws IOException {
		Marker marker = new Marker();
//		InputStream is = Marker.class.getClassLoader().getResourceAsStream("copied_test_document.pdf");
		File file = new File("sent_mails/1.pdf");
		if(file.exists()) {
			System.out.println(file.getAbsolutePath());	
		}
		
		marker.mark(file, "2121212", new File("sent_mails"));
//		System.out.println(file.length());
//		File file = Files.copy(is, null, null)

	}

}
