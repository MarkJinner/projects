package com.gmail.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.exceptions.UsedCredentialException;
import com.gmail.queryparsers.ParsedCredentials;
import com.gmail.storage.Storage;

import logdisplayerprovider.LogDisplayProvider;

public class UsersDatabase extends Storage<Email, User> implements Properties, Serializable {
	private static UsersDatabase instance;
	private File users = new File("/Users/olegivanov/Desktop/TextTransformerApp/DataBase/users_database");
	private File usersFolder = new File("/Users/olegivanov/Desktop/TextTransformerApp/DataBase/users/");
	private volatile Map<Email, User> database;
	private boolean loginNotUsed = true;
	private String eMessage = "";

	public UsersDatabase() throws FileNotFoundException, ClassNotFoundException, IOException {
		database = load();
	}

	public String geteMessage() {
		return eMessage;
	}

	public void seteMessage(String eMessage) {
		this.eMessage = eMessage;
	}
	
	

	public static UsersDatabase getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		if (instance == null) {
			instance = new UsersDatabase();

		}
		return instance;
	}

	public synchronized File getUsers() {
		return users;
	}

	public synchronized void setUsers(File users) {
		this.users = users;
	}

	public File getUsersFolder() {
		return usersFolder;
	}

	public void setUsersFolder(File usersFolder) {
		this.usersFolder = usersFolder;
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException,
			InappropriateEmailFormatException, InapropriateCredentialsException, NoSuchUserException {
		User user1 = new User();
//		try {
//			user1.setEmail("me@s21.com");
//		} catch (InappropriateEmailFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		user1.setEmail("user11");
//		user1.setLogin("user11");
//		user1.setPassword("user1Pass");
		User user2 = new User("me2@s.com", "user2", "pass2");
		UsersDatabase base = new UsersDatabase();
//		base.add(user1);
//		System.out.println(base);
		User user3;

		user3 = new User("me333@s.com", "user3334", "pass2");
//		base.add(user3);

		// TODO Auto-generated catch block

//		base.add(user1);
//		base.add(user2);

		User user4 = new User("me4@s.com", "user4", "pass2");
		base.add(user4);

		User user5 = new User("me5@s.com", "user4", "pass2");
		base.add(user5);
//		System.out.println(base.loginNotUsed("user5"));
//		User user6 = new User("user7@s.com", "user7", "pass2");
//		System.out.println(base.loginNotUsed("user818"));
//		base.add(user6);
//		base.remove(user2);
//		base.remove(user3);
//		base.findByLogin("user21").ifPresentOrElse((s)->System.out.println(s), ()->System.out.println("No such user"));
//		System.out.println(base.checkLogin("21"));
		System.out.println("Base:"+base);
//		System.out.println(base.findByEmail("me3@s.com"));
//		System.out.println(base.findByLogin("user891"));
//		base.checkCredentials(user2);
//		base.emailNotUsed(new Email("q@q"));
//		base.emailNotUsed(new Email("user12"));
		User us = new User();
		us.setLogin("user1");
		us.setEmail("user1@gmail.com");
		us.setPassword("qwer");
		base.add(us);
//		System.out.println(base.getUsersFolderPath(us));
//		System.out.println(base.findByLogin("user1"));
		

	}

	@Override
	public Map<Email, User> load() throws FileNotFoundException, IOException, ClassNotFoundException {
		if (users == null || users.length() == 0) {
			if (!users.canExecute()) {
				File directory = new File(users.getParent() + "");
				directory.mkdirs();
				users.createNewFile();
				database = new HashMap<>();
			}

		} else {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(users))) {
				database = (Map<Email, User>) ois.readObject();
			}
		}

		return database;

	}

	@Override
	public void store() throws FileNotFoundException, IOException {
		if (users == null) {
			users.createNewFile();
		} else {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(users))) {
				oos.writeObject(database);
			}
		}
		System.out.println("Database updated");

	}

	@Override
	public boolean add(User user) {
		ParsedCredentials credentials = new ParsedCredentials();
		user.getEmail().getAddress().ifPresent((s) -> credentials.setEmail(user.getEmail().getAddress().get()));
		credentials.setLogin(user.getLogin());
		credentials.setPassword(user.getPassword());
		try {
			return this.add(credentials);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsedCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InappropriateEmailFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean add(ParsedCredentials credentials)
			throws UsedCredentialException, FileNotFoundException, IOException, InappropriateEmailFormatException {
		try {
			if (emailIsCorrect(credentials.getEmail().getAddress().get())) {
				if (this.emailNotUsed(credentials.getEmail())) {
					if (this.loginNotUsed(credentials.getLogin())) {
						this.database.put(credentials.getEmail(), new User(credentials));
						createUsersFolder(credentials);
						this.store();
						try {
							database = this.load();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					}

				}
			}

		} catch (InappropriateEmailFormatException e) {
			// TODO Auto-generated catch block
			eMessage = e.getMessage();
			e.printStackTrace();
		} catch (InapropriateCredentialsException e) {
			eMessage = e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			eMessage = e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			eMessage = e.getMessage();
			e.printStackTrace();
		}

		return false;

	}

	private void createUsersFolder(ParsedCredentials credentials) {
		if (!usersFolder.exists()) {
			usersFolder.mkdirs();

		}

		File userFolder = new File(usersFolder, credentials.getLogin());
		userFolder.mkdirs();
		System.out.println("User's folder created");
//		try {
//			this.database = load();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private boolean emailNotUsed(Email email) throws InapropriateCredentialsException {
		if (!this.database.containsKey(email)) {
			return true;
		}
		throw new InapropriateCredentialsException("Email used");
	}

	private boolean loginNotUsed(String login) throws InapropriateCredentialsException {

		Stream<User> str = database.values().stream();
		str.forEach((a) -> {
			if (login.equals(a.getLogin())) {
				loginNotUsed = false;
			}

		});

		if (loginNotUsed == false) {
			throw new InapropriateCredentialsException("Login used");
		}
		return true;

	}

	private boolean emailIsCorrect(String address) throws InappropriateEmailFormatException {
		if (Email.ifEmailIsproper(address)) {
			return true;
		}

		throw new InappropriateEmailFormatException("Email format is wrong");
	}

	@Override
	public void remove(User user) throws FileNotFoundException, IOException, NoSuchUserException {
		if (database.containsKey(user.getEmail())) {
			database.remove(user.getEmail());
			System.out.println(database.size());
			store();
			System.out.println("User removed and database updated");
		} else {
			throw new NoSuchUserException("No such user in database");
		}

	}

	@Override
	public Optional<User> findByEmail(Email email) throws NoSuchUserException {
		if (database.containsKey(email)) {
			return Optional.ofNullable(database.get(email));
		}
		throw new NoSuchUserException("No such user");
	}

	@Override
	public Optional<User> findByLogin(String login) throws NoSuchUserException {

		Iterator<java.util.Map.Entry<Email, User>> iter = database.entrySet().iterator();
		while (iter.hasNext()) {
			User temp = iter.next().getValue();
			if (temp.getLogin().equals(login)) {
				return Optional.ofNullable(temp);
			}
		}

		return Optional.empty();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		this.database.entrySet().forEach(s -> sb.append(s + System.lineSeparator()));

		return sb.toString();
	}

	@Override
	public Optional<User> findByEmail(String email) throws InappropriateEmailFormatException, NoSuchUserException {

		return this.findByEmail(new Email(email));
	}

	@Override
	public boolean checkCredentials(ParsedCredentials credentials)
			throws InapropriateCredentialsException, NoSuchUserException {
		try {
			database = load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (credentials.getEmail().getAddress().isPresent()) {
				this.verifyEmail(credentials.getEmail().getAddress().get());
			}
			User temp = verifyLogin(credentials.getLogin()).get();
			verifyPassword(temp, credentials.getPassword());
			return true;
		} catch (InapropriateCredentialsException e) {
			eMessage = e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchUserException e) {
			eMessage = e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InappropriateEmailFormatException e) {
			eMessage = e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean verifyEmail(String address)
			throws InapropriateCredentialsException, InappropriateEmailFormatException, NoSuchUserException {
		if (this.findByEmail(address).isPresent()) {
			return false;
		}
		throw new InapropriateCredentialsException("Email already used");

	}

	public Optional<User> verifyLogin(String login) throws InapropriateCredentialsException, NoSuchUserException {
		if (findByLogin(login).isPresent()) {
			return Optional.ofNullable(findByLogin(login).get());
		}
		throw new InapropriateCredentialsException("Login incorrect");
	}

	public boolean verifyPassword(User user, String password) throws InapropriateCredentialsException {
		if (user.getPassword().equals(password)) {
			return true;
		}
		throw new InapropriateCredentialsException("Password incorrect");

	}

	public boolean checkCredentials(User user)
			throws InapropriateCredentialsException, NoSuchUserException, InappropriateEmailFormatException {
		ParsedCredentials crds = new ParsedCredentials();
		crds.setEmail(user.getEmail().getAddress().get());
		crds.setLogin(user.getLogin());
		crds.setPassword(user.getPassword());
		return this.checkCredentials(crds);
	}
	
	public Optional<Path> getUsersFolderPath(User user) throws InapropriateCredentialsException, NoSuchUserException, InappropriateEmailFormatException {
		if(this.usersFolder.isDirectory()) {
			File [] files = usersFolder.listFiles();
			for(File i: files) {
				if((i.getName().equals(user.getLogin()))) {
					return Optional.ofNullable(Paths.get(usersFolder.getAbsolutePath()+"/"+user.getLogin()));		
				}
			}
		}
		throw new  NoSuchUserException("No such user's folder in users folder");
	}

}
