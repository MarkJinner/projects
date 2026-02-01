package com.gmail.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.exceptions.UsedCredentialException;
import com.gmail.queryparsers.ParsedCredentials;

public interface Properties {

	public boolean add(User user) throws UsedCredentialException, FileNotFoundException, IOException, InappropriateEmailFormatException;
	
	public boolean add(ParsedCredentials credentials) throws UsedCredentialException, FileNotFoundException, IOException, InappropriateEmailFormatException;

	public void remove(User user) throws FileNotFoundException, IOException, NoSuchUserException;

	public Optional<User> findByLogin(String login) throws NoSuchUserException;

	public Optional<User> findByEmail(Email email) throws NoSuchUserException;

	public Optional<User> findByEmail(String email) throws InappropriateEmailFormatException, NoSuchUserException;
	
	public boolean checkCredentials(ParsedCredentials credentials) throws InapropriateCredentialsException, NoSuchUserException;
	

}
