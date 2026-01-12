package com.gmail.servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.gmail.queryprocessors.UploadedFileProcessor;

import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
public class ServletUploadProcessor extends BasicServlet {

	private UploadedFileProcessor uploadProcessor;

	public ServletUploadProcessor() {
		uploadProcessor = new UploadedFileProcessor();
	}

	public static void main(String[] args) {
		ServletUploadProcessor pr = new ServletUploadProcessor();

//		System.out.println(pr.readFileByApache(new File("/Users/olegivanov/Desktop/logs/Destination Wellbeing (Ruth Greeff).docx")));
//		System.out.println(pr.readFileByApache(pr.file2));
//		System.out.println(pr.readFile(pr.file4));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You got your GET ServletUploadProcessor request");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String res = "";
		System.out.println("You got your POST ServletUploadProcessor request");
//		super.getInstance().getDisplay().displayLog(new Date()+": This is string from uploader");
		try {
			Writer wr = resp.getWriter();
				res = uploadProcessor.processRequest(req,super.getInstance());
				if(res=="") {
					res = "Uploaded file not supported";
				}
				wr.write(res);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
	
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		String line = "";
		try (Reader reader = req.getReader(); BufferedReader bf = new BufferedReader(reader)) {

			while ((line = bf.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		return sb.toString();
	}

}
