package com.gmail.transformationsaver;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gmail.database.User;
import com.gmail.exceptions.FileNotSavedException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UserNotFoundException;

public interface Saver {
	
	public boolean saveTextIntoFile(User user, String transformer, String text) throws FileNotFoundException, InapropriateCredentialsException, UserNotFoundException, InappropriateEmailFormatException, IOException, FileNotSavedException;
}
