package com.gmail.mailanalyzer.main.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
	private File file;

	public Writer(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File write(String json) throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
			pw.print((json+System.lineSeparator()));
		}
		return file;
	}
}
