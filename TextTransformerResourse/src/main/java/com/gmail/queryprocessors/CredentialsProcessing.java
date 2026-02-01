package com.gmail.queryprocessors;

import java.io.IOException;

import com.gmail.queryparsers.ParsedCredentials;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CredentialsProcessing {

	public void sendErrorCode(HttpServletResponse resp, ParsedCredentials credentials) throws IOException;

	public void sendSuccessRedirect(HttpServletRequest request, HttpServletResponse resp, ParsedCredentials credentials)
			throws IOException;
}
