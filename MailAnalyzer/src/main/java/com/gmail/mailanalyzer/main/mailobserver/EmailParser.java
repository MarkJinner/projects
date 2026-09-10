package com.gmail.mailanalyzer.main.mailobserver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmailParser {
	private Order temp = new Order();
	private List<String> extentions = new ArrayList<>(List.of("net", "com"));

	public EmailParser() {

	}

	public Order parseEmail(Email email) {

		if (email.getContent() != null | !email.getContent().isEmpty()) {
			String[] emailLines = email.getContent().split(System.lineSeparator());
			Arrays.stream(emailLines).forEach(s -> {
				setEmailFields(emailLines, s);
			});

		}

		return temp;
	}

	private void setEmailFields(String[] emailLines, String s) {
		if (s.startsWith("Номер замовлення:")) {
			temp.setTransactionId(s.substring("Номер замовлення: ".length(), s.length()).trim());
		} else if (s.startsWith("Атрибут2:")) {
			temp.setAddress(s.substring("Атрибут2: ".length(), s.length()).trim());
		} else if (s.startsWith("Коментар:")) {
			parseCommentField(emailLines, s);
		}
	}

	private void parseCommentField(String[] emailLines, String s) {
		substringAndSetQuantity(s);
		String subDate = substringAndSetDate(s);
		substringAndSetCity(subDate);

		if (ifNoEmailField(emailLines)) {
			if (endsWithEmail(s)) {
				String addr = parseAddress(s).trim();
				temp.setAddress(addr);
			}
		}
	}

	private void substringAndSetCity(String subDate) {
		String city = subDate.substring(subDate.indexOf(" ") + 1, subDate.length());
		temp.setCity(city.trim());
	}

	private String substringAndSetDate(String s) {
		String subDate = s.substring(s.indexOf("for the seminar.") + "\"for the seminar.".length(),
				s.indexOf(". Payer"));
		String date = subDate.substring(0, subDate.indexOf(" "));
		temp.setDate(date.trim());
		return subDate;
	}

	private void substringAndSetQuantity(String s) {
		String subQty = s.substring(0, s.indexOf("ticket(s)"));
		String qty = subQty.substring("Коментар: Payment for ".length(), subQty.lastIndexOf(" "));
		temp.setQuantity(qty.trim());
	}

	private boolean ifNoEmailField(String[] lines) {
		return Arrays.stream(lines).noneMatch(s -> s.startsWith("Атрибут2:"));
	}

	private boolean endsWithEmail(String line) {
		String subMail = parseAddress(line);
		if (subMail.contains("net") || subMail.contains("com")) {
			return true;
		}

		return false;
	}

	private String parseAddress(String line) {
		return line.substring(line.lastIndexOf(" "), line.length());
	}

	public static void main(String[] args) {
		EmailParser parser = new EmailParser();
		Arrays.stream(parser.getClass().getDeclaredMethods()).forEach(s->{
			if(s.isAnnotationPresent(Test.class)) {
				try {
					s.invoke(parser, null);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
//		parser.testParseEmail1();
//		parser.testParseEmail2();
	}

	@Test
	private boolean testParseEmail1() {
		String sample = "В системі Portmone.com оплачено замовлення:\n"
				+ "Рахунок на компанію: ТОВ \"НЕТВОРК 21 УКРАЇНА\" (ТОВ \"НЕТВОРК 21 УКРАЇНА\")\n"
				+ "Номер замовлення: P2292647089\n" + "Дата замовлення: 14/03/2026 12:26:18\n"
				+ "Коментар: Payment for 2 ticket(s) for the seminar. 14.05.2026 Lviv. Payer Нагорний Андрій . Emerald Sponsor: Сухар Алоїс і Сісі-Петра.\n"
				+ "Атрибут2: Naga.ua@gmail.com\n" + "\n" + "Сума: 4500\n" + "Валюта: UAH\n"
				+ "Код авторизації: 540537\n"
				+ "Тип авторизації: 3DSECURE  MON_CIR:U MON_STS:N  BANK_RECURRENT_ID:306073375778627 \n"
				+ "ID оплати: 2292647089";

		Email email = new Email(sample);

		if (this.parseEmail(email) != null) {
			System.out.println("Test 1 positive:" + this.parseEmail(email));
			return true;
		} else {
			System.out.println("Test negative");
		}
		return false;

	}

	@Test
	private boolean testParseEmail2() {

		String sample = "В системі Portmone.com оплачено замовлення:\n"
				+ "Рахунок на компанію: ТОВ НЕТВОРК 21 УКРАЇНА (НЕТВОРК 21 УКРАЇНА)\n"
				+ "Номер замовлення: P1635809301\n" + "Дата замовлення: 03/04/2024 22:38:31\n"
				+ "Коментар: Payment for 2 ticket(s) for the seminar. 13.07.2024 Lviv. Payer Миронова Наталія. Emerald Sponsor: Котик Ігорь і Ирина.  natalija_lv@ukr.net\n"
				+ "Сума: 3900\n" + "Валюта: UAH\n" + "Код авторизації: 035079\n"
				+ "Тип авторизації: 3DSECURE  MON_CIR:U MON_STS:U";

		Email email = new Email(sample);

		if (this.parseEmail(email) != null) {
			System.out.println("Test 2 positive:" + this.parseEmail(email));
			return true;
		} else {
			System.out.println("Test negative");
		}
		return false;

	}

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test {

}