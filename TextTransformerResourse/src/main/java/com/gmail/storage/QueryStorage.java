package com.gmail.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

public class QueryStorage extends Storage<Integer, String>{
	private Map<Integer, String> queries;
	private File storage = new File("/Users/olegivanov/Desktop/query_storage.txt");
	private int storageNumber;

	public QueryStorage() throws ClassNotFoundException, IOException {
		queries = this.load();
		if (this.queries == null) {
			storageNumber = 0;
		} else {
			storageNumber = queries.size();
		}
	}

	public int getStorageNumber() {
		return storageNumber;
	}

	public void setStorageNumber(int storageNumber) {
		this.storageNumber = storageNumber;
	}

	public Map<Integer, String> load() throws IOException, ClassNotFoundException {
//		
		if (storage.length() == 0) {
			queries = new TreeMap<>();
			storage.createNewFile();
		} else {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storage))) {
				queries = (Map<Integer, String>) ois.readObject();
			}
		}

		return queries;

	}

	public void store() throws FileNotFoundException, IOException {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(storage))) {
			os.writeObject(queries);
		}
	}

	public void add(String date, String transformer, String query) throws ClassNotFoundException, IOException {
		queries.put(queries.size() + 1, formRecord(date, transformer, query));
		this.store();
	}

	private String formRecord(String date, String transformer, String query) {
		return date + "|" + transformer + "|" + query;
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
//		Frame frame = new Frame();
//		frame.setVisible(true);
//		frame.setSize(300, 300);
//		frame.setBackground(Color.black);
//		while(Thread.currentThread().isAlive()) {
//			frame.paint(Graphics.class.ins);
//		}

		QueryStorage storage = null;
		try {
			storage = new QueryStorage();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			storage.load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		int x = 

//		storage.queries.put(storage.queries.size() + 1, "one");
//		storage.queries.put(storage.queries.size() + 1, "one");
		try {
			storage.store();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		storage.add("aaa", "bbb");
		System.out.println(storage);

	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(queries.size()>0) {
			queries.entrySet().stream().forEach(s->sb.append(s+System.lineSeparator()));
		}
		return sb.toString();
	}


}
