package com.gmail.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public abstract class Storage <T,K>{

	
	public Storage() {
		
	}
	
	public abstract Map<T, K> load() throws FileNotFoundException, IOException, ClassNotFoundException;
	
	public abstract void store() throws FileNotFoundException, IOException;
	
}
