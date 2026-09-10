package com.gmail.database;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UsedCredentialException;

public interface UpdateProperties {
	
	public boolean updateLogin(User user, String newLogin) throws FileNotFoundException, IOException, InapropriateCredentialsException, UsedCredentialException;
	
	public boolean updatePassword(User user, String newPassword) throws FileNotFoundException, IOException, InapropriateCredentialsException;
	
	public boolean updateEmail(User user, String newAddress) throws InapropriateCredentialsException, FileNotFoundException, IOException, UsedCredentialException, InappropriateEmailFormatException;
}
