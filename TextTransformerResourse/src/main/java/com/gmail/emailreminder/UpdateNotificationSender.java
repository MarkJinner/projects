package com.gmail.emailreminder;

import com.gmail.database.Email;

public interface UpdateNotificationSender {

	public void sendSuccessUpdateNotification(String address);

	public void sendSuccessUpdateNotification(Email address);

	public void sendFailedUpdateNotification(Email address);

	public void sendFailedUpdateNotification(String address);
}
