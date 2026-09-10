package com.gmail.database.downloads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gmail.database.FileAddresses;
import com.gmail.database.User;
import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.EmailNotFoundException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UserNotFoundException;

public class DownloadsList {
	private UsersDatabase users;
	private File usersFolder = FileAddresses.getUsersFolder();
	private Downloads downloads = new Downloads();
	private DownloadParser parser = new DownloadParser();
	private List<String> links = new ArrayList<>();
	private User user;

	public DownloadsList() throws FileNotFoundException, ClassNotFoundException, IOException {
		users = UsersDatabase.getInstance();
	}

	public DownloadsList(User user) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.user = user;
		users = UsersDatabase.getInstance();
	}

	private List<String> getUsersDownloadsLinks(User user) throws EmailNotFoundException,
			InappropriateEmailFormatException, InapropriateCredentialsException, UserNotFoundException {

		if (users.userIsInDatabase(user)) {
			getUsersFolder(user).ifPresent(s -> {
				if (s.isDirectory()) {
					File[] files = s.listFiles();
					for (File i : files) {
						links.add(i.getAbsolutePath());
					}
				}

			});
		} else {
			throw new UserNotFoundException("No such user in database");
		}
		return links;
	}

	private Optional<File> getUsersFolder(User user) {
		File[] folder = usersFolder.listFiles();
		File result = null;
		for (File i : folder) {
			if (i.getName().contains(user.getLogin())) {
				result = i;
			}
		}
		return Optional.ofNullable(result);
	}

	public static void main(String[] args)
			throws FileNotFoundException, ClassNotFoundException, IOException, UserNotFoundException,
			EmailNotFoundException, InappropriateEmailFormatException, InapropriateCredentialsException {
		DownloadsList list = new DownloadsList();
		User user = UsersDatabase.getInstance().findByLogin("user21").get();
//		System.out.println(user);
//		list.getUsersDownloads(user);
//		User temp = new User("Oleg_ua@n21.com","user909","111");

//		List<String> downloads = list.getUsersDownloadsLinks(user);

//		downloads.forEach(s->System.out.println(s));
		System.out.println(list.convertDownloadsIntoTable(user));

	}

	public String getFormattedDownloadsList(User user)
			throws EmailNotFoundException, InappropriateEmailFormatException, InapropriateCredentialsException {
		return convertDownloadsIntoTable(user);
	}

	public String convertDownloadsIntoTable(User user)
			throws EmailNotFoundException, InappropriateEmailFormatException, InapropriateCredentialsException {
		StringBuilder sb = new StringBuilder();
		sb.append("<tbody>");

		if (users.userIsInDatabase(user)) {
			getUsersFolder(user).ifPresent(s -> {
				if (s.isDirectory()) {
					File[] files = s.listFiles();
					for (File i : files) {
						System.out.println(i);
						if(i.getName().contains("Case")) {
							Download download = parser.getDownload(i.getAbsolutePath());
							sb.append("<tr>");
							sb.append("<td>" + download.getDate() + "</td>");
							sb.append("<td>" + download.getTime() + "</td>");
							sb.append("<td>" + download.getTransformer() + "</td>");
							sb.append("<td>" + download.getLength() + "</td>");
							sb.append("<td>" + download.getLink().getLink() + "</td>");
							sb.append("</tr>"); 
						}

					}
				}
			});
		}

		sb.append("</tbody>");

		return sb.toString();
	}

//	public Downloads getUsersDownloads(User user) {
//		
//	}
}
