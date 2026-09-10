package com.gmail.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import com.gmail.exceptions.EmailNotFoundException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UsedCredentialException;
import com.gmail.exceptions.UserNotFoundException;
import com.gmail.queryparsers.ParsedCredentials;

public interface Properties {

	public boolean add(User user)
			throws UsedCredentialException, FileNotFoundException, IOException, InappropriateEmailFormatException;

	public boolean add(ParsedCredentials credentials)
			throws UsedCredentialException, FileNotFoundException, IOException, InappropriateEmailFormatException;

	public void remove(User user) throws FileNotFoundException, IOException, UserNotFoundException;

	public Optional<User> findByLogin(String login) throws UserNotFoundException;

	public Optional<User> findByEmail(Email email) throws UserNotFoundException;

	public Optional<User> findByEmail(String email) throws InappropriateEmailFormatException, UserNotFoundException;

	public boolean checkCredentials(ParsedCredentials credentials)
			throws InapropriateCredentialsException, UserNotFoundException;

	public boolean emailIsInDatabase(Email email) throws EmailNotFoundException, InappropriateEmailFormatException;

	public boolean emailIsInDatabase(String address) throws EmailNotFoundException, InappropriateEmailFormatException;

}
