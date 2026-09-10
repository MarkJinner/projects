package com.gmail.mailanalyzer.main.jsonconverter;

import com.gmail.mailanalyzer.main.observerframe.message.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter<T> {
	private Gson gson;

	public JsonConverter() {
		gson = new GsonBuilder().create();
	}

	public String toJson(Message message) {
		return gson.toJson(message);
	}

	public Message fromJson(String line, Class<? extends Message> cl) {
		return gson.fromJson(line, cl);
	}
}
