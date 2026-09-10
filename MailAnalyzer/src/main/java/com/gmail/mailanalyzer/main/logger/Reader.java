package com.gmail.mailanalyzer.main.logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	private File file;

	public Reader(File file) {
		this.file = file;
	}

	public Reader() {

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<String> read(File file) throws FileNotFoundException, IOException {
		List<String> lines = new ArrayList<>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				lines.add(line);

			}
		}

		return lines;
	}
}
