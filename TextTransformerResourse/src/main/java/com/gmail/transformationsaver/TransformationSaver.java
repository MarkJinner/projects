package com.gmail.transformationsaver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.gmail.database.User;
import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.FileNotSavedException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UserNotFoundException;

public class TransformationSaver implements Saver {
	private SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd HH-mm", Locale.US);
	private UsersDatabase base;
	private String text = "";
	private User user;

	
	
	public TransformationSaver(User user, String text)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		this.user = user;
		this.text = text;
		base = new UsersDatabase();
	} 

	public TransformationSaver(User user) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.user = user;
		base = new UsersDatabase();

	}

	public TransformationSaver(String text) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.text = text;
		base = new UsersDatabase();
	}

	public TransformationSaver() throws FileNotFoundException, ClassNotFoundException, IOException {
		base = new UsersDatabase();
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, InapropriateCredentialsException, UserNotFoundException, InappropriateEmailFormatException, FileNotSavedException {


		TransformationSaver saver = new TransformationSaver();
		System.out.println(saver.sdf.format(new Date()));
		

		User user = new User();
		user.setLogin("user1");
		saver.saveTextIntoFile(user, "toUpperCase", "this is test");

	}

	@Override
	public boolean saveTextIntoFile(User user, String transformer, String text) throws InapropriateCredentialsException,
	UserNotFoundException, InappropriateEmailFormatException, IOException, FileNotSavedException {
		if (base.getUsersFolderPath(user).isPresent()) {
			return save(base.getUsersFolderPath(user).get(), transformer, text);
		}
		throw new FileNotSavedException("File wasn't saved");
	}

	private boolean save(Path path, String transformer, String text) throws IOException {
		path = Paths.get(path.toString() +"/"+ sdf.format(new Date())+" "+transformer);
		try (ByteChannel bc = Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			ByteBuffer bb = ByteBuffer.allocate(128);
			bb.put(text.getBytes());		
			bb.flip();
			bc.write(bb);
			return true;
		}
	}

}
