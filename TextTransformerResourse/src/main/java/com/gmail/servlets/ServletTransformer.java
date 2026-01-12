package com.gmail.servlets;

import java.io.IOException;
import java.io.Writer;

import com.gmail.logger.Logger;
import com.gmail.queryprocessors.TransformQueryProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletTransformer extends BasicServlet {
	private TransformQueryProcessor transformer;
	private Logger logger = super.getLogger();

	public ServletTransformer() throws ClassNotFoundException, IOException {
		transformer = new TransformQueryProcessor();
	}

	public static void main(String[] args) {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You got GET UpperCaseTransformerRequest");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You got POST UpperCaseTransformerRequest");
		String response = "";
		if (req.getRequestURI().toString().equals("/transform")) {
			response = transformer.processRequest(req,super.getInstance());
			
//			super.getInstance().getDisplay().displayLog(new Date()+": This is string from transformer");
		}
		try {
			Writer wr = resp.getWriter();
			wr.write(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}