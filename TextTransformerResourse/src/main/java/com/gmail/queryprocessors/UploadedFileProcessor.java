package com.gmail.queryprocessors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.gmail.exceptions.UnsupportedExtentionException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.Parsed;
import com.gmail.queryparsers.ParsedFile;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import logdisplayerprovider.LogDisplayProvider;

public class UploadedFileProcessor extends QueryProcessor {
	File uploadDir = new File("/Users/olegivanov/Desktop/logs/uploads/");
	private Logger logger = new Logger();
	private String loggingMessage = "";

	public UploadedFileProcessor() {
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		UploadedFileProcessor processor = new UploadedFileProcessor();
		String fileName = "Test.doc";
		System.out.println(processor.getExtention(fileName));

	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		
		String result = "";
		
		try {
			String filepath = getFile(req);
			provider.getDisplay().displayLog(loggingMessage);
			if (fileIsDoc(filepath)) {
				result = readFileByApache(new File(filepath));
			} else if (fileIsTxt(filepath)) {
				result = this.readFile(new File(filepath));
			} else if (fileIsVtt(filepath)) {
				result = this.readFile(new File(filepath));
			} else {
				throw new UnsupportedExtentionException("File type not supported");
			}
		} catch (IOException | ServletException | UnsupportedExtentionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private String getExtention(String filename) {
		return filename.substring(filename.indexOf(".") + 1, filename.length());
	}

	private boolean fileIsDoc(String filename) {
		if (getExtention(filename).equals("doc") | getExtention(filename).equals("docx")) {
			return true;
		}
		return false;
	}

	private boolean fileIsTxt(String filename) {
		if (getExtention(filename).equals("txt")) {
			return true;
		}
		return false;
	}

	private boolean fileIsVtt(String filename) {
		if (getExtention(filename).equals("vtt")) {
			return true;
		}
		return false;
	}

	@Override
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		String result = "";
		try (InputStreamReader bs = new InputStreamReader(req.getInputStream(), "UTF-8")) {
			int x = 0;
			while (x != -1) {

				x = bs.read();
				if (x != -1) {
					sb.append((char) x);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	private String getFile(HttpServletRequest request) throws IOException, ServletException {

		Part filepart = request.getPart("file");
		String fileName = filepart.getSubmittedFileName();
		for (Part part : request.getParts()) {
			part.write(uploadDir + File.separator + fileName);
		}
		loggingMessage = "File downloaded on " + request.getHeader("referer") + ": " + fileName; 
		logger.log(loggingMessage);
		return uploadDir + File.separator + fileName;
	}

//	private String getFileName(String requestBody) {
//		StringBuilder sb = new StringBuilder();
//		if (requestBody.contains("filename")) {
//			sb.append(requestBody.substring(requestBody.indexOf("filename=\"") + 10,
//					requestBody.indexOf("Content-Type:") - 3));
//		}
//
//		return sb.toString().trim();
//	}

	private String readFile(File file) {
		StringBuilder sb = new StringBuilder();
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				sb.append(sc.nextLine());
				sb.append(System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	private String readFileByApache(File file) throws FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder();
		XWPFDocument doc = new XWPFDocument(new FileInputStream(file.getAbsolutePath()));
		List<XWPFParagraph> list = doc.getParagraphs();

		for (XWPFParagraph i : list) {
			sb.append(i.getText() + System.lineSeparator());
		}
		return sb.toString();

	}

	public Parsed parseQuery(String query) {
		StringBuilder sb = new StringBuilder();
		String[] qLines = query.split(System.lineSeparator());
		int start = getIndex(query, "Content-Type:");
		int finish = qLines.length - 1;

		for (int i = start; i < finish; i++) {
			sb.append(qLines[i]);
			sb.append(System.lineSeparator());
		}
//		getFileName(query);
		return new ParsedFile(sb.toString().trim());
	}

	private int getIndex(String query, String mark) {
		int x = 0;
		for (String i : query.split(System.lineSeparator())) {
			x++;
			if (i.startsWith(mark)) {
				break;
			}
		}

		return x;
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Operation not supported");
	}

}
