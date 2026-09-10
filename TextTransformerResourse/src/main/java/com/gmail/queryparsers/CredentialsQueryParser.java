package com.gmail.queryparsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CredentialsQueryParser extends QueryParser {
	private List<String> keys = new ArrayList<>(List.of("password", "login", "email"));
	private String key = "";
	private String value = "";

	@Override
	public Parsed parseQuery(String query) {
		ParsedJson parsed = new ParsedJson();
		parsed.setKey(this.getKey(query));
		parsed.setValue(this.getValue(query));
		return parsed;
	}

	private String test() {
		String sample = "{" + "\"password" + "\":" + "\"" + "user7" + "\"" + "}";
		String sample2 = "{" + "\"user7" + "\":" + "\"" + "password" + "\"" + "}";
//		System.out.println(sample);
//		System.out.println(this.parseFirstElement(sample));
//		System.out.println(this.parseSecondElement(sample));
		System.out.println("key: "+this.getKey(sample));
		System.out.println("value: "+this.getValue(sample));

		return sample;
	}

	public static void main(String[] args) {
		CredentialsQueryParser parser = new CredentialsQueryParser();
		parser.test();
	}

	private String getKey(String query) {

		parseFirstElement(query).ifPresent(s -> {
			key = parseFirstElement(query).get();

		});
		if (keys.stream().anyMatch(s -> s.equals(key))) {
			return key;
		}else {
			parseSecondElement(query).ifPresent(s -> {
				key = parseSecondElement(query).get();
			});
		}

		return key;
	}

	private String getValue(String query) {
		parseSecondElement(query).ifPresent(s -> {
			value = parseSecondElement(query).get();

		});
		if (!keys.stream().anyMatch(s -> s.equals(value))) {
			return value;
		}else {
			parseFirstElement(query).ifPresent(s -> {
				value = parseFirstElement(query).get();

			});
		}

		return value;
	}

	private Optional<String> parseFirstElement(String query) {
		return Optional.ofNullable(query.substring(query.indexOf("\"") + 1, query.indexOf(":") - 1));
	}

	private Optional<String> parseSecondElement(String query) {
		return Optional.ofNullable(query.substring(query.indexOf(":") + 2, query.lastIndexOf("\"")));
	}

}
