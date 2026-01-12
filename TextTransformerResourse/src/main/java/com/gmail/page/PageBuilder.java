package com.gmail.page;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class PageBuilder {
	private String home = "http://localhost:8080/";
	private File folder = new File("/Users/olegivanov/eclipse-workspace2025/TextTransformerNew/src/main/webapp");
	
	public PageBuilder() {
		
	}
	
	public static void main(String [] args) throws FileNotFoundException {
		PageBuilder builder = new PageBuilder();
		try {
			
//			Page defaultPage = builder.getPage("default");
			System.out.println(PageBuilder.Page("defaultteerr"));
			System.out.println(PageBuilder.Page("default"));
			System.out.println(PageBuilder.Page("UpperCaseTransformer"));
			System.out.println(PageBuilder.Page("error404"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		builder.pageInFolder("default");
	}
	
	
	private String readFile(File file) throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		try(Scanner sc = new Scanner(file)){
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine());
			}
		}
		
		return sb.toString();
	}
	
	public static Page Page(String title) throws FileNotFoundException {
		Page result = new Page(); 
		new PageBuilder().pageInFolder(title).ifPresentOrElse((s)->{
			try {
				result.setAddress(new PageBuilder().pageInFolder(title).get());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, ()->{
			try {
				result.setAddress(new PageBuilder().pageInFolder("default").get());
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			}
		});
		
		return result;
	}
	
	private Optional<String> pageInFolder(String title) throws FileNotFoundException {
		File [] files = folder.listFiles();
		for(File i: files) {
			if((i.getName()).equalsIgnoreCase(title+".jsp")) {
				return Optional.ofNullable(home+title+".jsp");
			}
		}
		return Optional.empty();
	}
}
