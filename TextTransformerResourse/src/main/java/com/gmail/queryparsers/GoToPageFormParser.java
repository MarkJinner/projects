package com.gmail.queryparsers;

import java.util.Optional;

public class GoToPageFormParser extends QueryParser {

	public static void main(String[] args) {
		String exmpl = "{action:Transition from page http://localhost:8080/Text transformer main page.jsp to page http://localhost:8080/toUpperCase.jsp}";
		GoToPageFormParser jp = new GoToPageFormParser();
		System.out.println(jp.parseQuery(exmpl));
	}

	@Override
	public Parsed parseQuery(String query) {
		ParsedJson parsed = new ParsedJson();
		substringText(query).ifPresentOrElse((s) -> {
			parsed.setText(s);
		}, () -> {
			parsed.setText("");
		});
		return parsed;
	}

	private Optional<String> substringText(String query) {
		System.out.println("query " + query);
		String result = query.substring(query.indexOf("{action:")+8, query.length()-3);
		return Optional.ofNullable(result);
	}

}
