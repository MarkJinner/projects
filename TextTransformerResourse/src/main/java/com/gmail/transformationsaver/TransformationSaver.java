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
import com.gmail.exceptions.NoSuchUserException;

public class TransformationSaver implements Saver {
	private SimpleDateFormat sdf = new SimpleDateFormat("YYY-MMM-DD HH:mm:ss", Locale.US);
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

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, InapropriateCredentialsException, NoSuchUserException, InappropriateEmailFormatException, FileNotSavedException {
//		UsersDatabase base = new UsersDatabase();
//		base.getUsersFolder();

		TransformationSaver saver = new TransformationSaver();
//		System.out.println(saver.sdf.format(new Date()));
		
//		saver.save(Path.of("/Users/olegivanov/Desktop/TextTransformerApp/"), "This is test@");
		User user = new User();
		user.setLogin("user1");
		saver.saveTextIntoFile(user, "this is test");

	}

	@Override
	public boolean saveTextIntoFile(User user, String text) throws InapropriateCredentialsException,
			NoSuchUserException, InappropriateEmailFormatException, IOException, FileNotSavedException {
		if (base.getUsersFolderPath(user).isPresent()) {
			return save(base.getUsersFolderPath(user).get(), text);
		}
		throw new FileNotSavedException("File wasn't saved");
	}

	private boolean save(Path path, String text) throws IOException {
		path = Paths.get(path.toString() +"/"+ sdf.format(new Date()));
		try (ByteChannel bc = Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			ByteBuffer bb = ByteBuffer.allocate(128);
			bb.put(text.getBytes());		
			bb.flip();
			bc.write(bb);
			return true;
		}
	}

}
