package com.gmail.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.exceptions.UsedCredentialException;
import com.gmail.queryparsers.ParsedCredentials;
import com.gmail.storage.Storage;

public class UsersDatabase extends Storage<Email, User> implements Properties, Serializable {
	private File users = new File("/Users/olegivanov/Desktop/TextTransformerApp/DataBase/users_database");
	private Map<Email, User> database;
	private boolean loginUsed;
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



	public static void main(String[] args)
			throws FileNotFoundException, ClassNotFoundException, IOException, UsedCredentialException,
			NoSuchUserException, InappropriateEmailFormatException, InapropriateCredentialsException {
		User user1 = new User();
		user1.setEmail("me@s.com");
		user1.setLogin("user1");
		user1.setPassword("user1Pass");
		User user2 = new User("me2@s.com", "user2", "pass2");
		UsersDatabase base = new UsersDatabase();
//		System.out.println(base);
		User user3 = new User("me3@s.com", "user3", "pass2");
//		base.add(user1);
//		base.add(user2);
//		base.add(user3);
//		base.remove(user2);
//		base.remove(user3);
//		base.findByLogin("user21").ifPresentOrElse((s)->System.out.println(s), ()->System.out.println("No such user"));
		System.out.println(base.checkLogin("21"));
//		System.out.println(base);
//		System.out.println(base.findByEmail("me3@s.com"));
//		System.out.println(base.findByLogin("user3"));
//		base.checkCredentials(user1);

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
	public void add(User user) throws UsedCredentialException, FileNotFoundException, IOException {

		if (database.containsKey(user.getEmail())) {
			throw new UsedCredentialException("User can't be added. Email already used");

		} else {
			if (loginUsed(user.getLogin())) {
				throw new UsedCredentialException("User can't be added. Login already used");
			} else {
				database.put(user.getEmail(), user);
				System.out.println("User added");
				store();

			}
		}

	}

	private boolean loginUsed(String login) {
		database.forEach((a, b) -> {
			if (b.getLogin().equals(login)) {
				loginUsed = true;
			}
		});
		return loginUsed;
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
			User temp = checkLogin(credentials.getLogin()).get();
			checkPassword(temp, credentials.getPassword());
			return true;
		} catch (InapropriateCredentialsException e) {
			eMessage = e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchUserException e) {
			eMessage = e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public Optional<User> checkLogin(String login) throws InapropriateCredentialsException, NoSuchUserException {
		if (findByLogin(login).isPresent()) {
			return Optional.ofNullable(findByLogin(login).get());
		}
		throw new InapropriateCredentialsException("Login incorrect");
	}

	public boolean checkPassword(User user, String password) throws InapropriateCredentialsException {
		if (user.getPassword().equals(password)) {
			return true;
		}
		throw new InapropriateCredentialsException("Password incorrect");

	}

	public boolean checkCredentials(User user) throws InapropriateCredentialsException, NoSuchUserException {
		if (findByLogin(user.getLogin()).isPresent()) {
			System.out.println("Login found");
			User temp = findByLogin(user.getLogin()).get();
			if (temp.getPassword().equals(user.getPassword())) {
				System.out.println("Password correct");
				return true;
			} else {
				throw new InapropriateCredentialsException("Password incorrect");
			}
		} else {
			throw new InapropriateCredentialsException("Login incorrect");
		}
	}

}
