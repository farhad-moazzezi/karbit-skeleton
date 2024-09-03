package org.karbit.text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public abstract class NumericTextUtils {

	// '٫' or Shift+3 in farsi keyboard layout
	private static final char PERSIAN_AMOUNT_SEPARATOR_CHARACTER = '\u066c';

	private static final char ENGLISH_AMOUNT_SEPARATOR_CHARACTER = ',';

	private static final char[] ZERO_TO_TEN_PERSIAN_NUMBER_CHARACTER = { '۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸',
			'۹' };

	private static final char[] ZERO_TO_TEN_ARABIC_NUMBER_CHARACTER = { '٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨',
			'٩' };

	private static final char[] ZERO_TO_TEN_ENGLISH_NUMBER_CHARACTER = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };

	private static final Map<Character, Character> IDENTICAL_NUMBER_IN_ENGLISH = getIdenticalNumberInEnglish();

	private static final List<List<String>> PERSIAN_NUMBER_NAME_CONTEXT = getPersianNumberNameContext();

	private static Map<Character, Character> getIdenticalNumberInEnglish() {
		Map<Character, Character> result = new HashMap<>();
		result.putAll(
				TextUtils.characterMapper(ZERO_TO_TEN_PERSIAN_NUMBER_CHARACTER, ZERO_TO_TEN_ENGLISH_NUMBER_CHARACTER));
		result.putAll(
				TextUtils.characterMapper(ZERO_TO_TEN_ARABIC_NUMBER_CHARACTER, ZERO_TO_TEN_ENGLISH_NUMBER_CHARACTER));
		return result;
	}

	public static String toPersianAmount(Long amount) {
		String amountStr = String.format("%,d", amount);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < amountStr.length(); i++) {
			if (Character.isDigit(amountStr.charAt(i))) {
				builder.append(ZERO_TO_TEN_PERSIAN_NUMBER_CHARACTER[(int) (amountStr.charAt(i)) - 48]);
			} else if (amountStr.charAt(i) == ENGLISH_AMOUNT_SEPARATOR_CHARACTER) {
				builder.append(PERSIAN_AMOUNT_SEPARATOR_CHARACTER);
			}
		}
		return builder.toString();
	}

	public static String withPersianNumerals(String numberStr) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numberStr.length(); i++) {
			if (numberStr.charAt(i) >= '0' && numberStr.charAt(i) <= '9') {
				builder.append(ZERO_TO_TEN_PERSIAN_NUMBER_CHARACTER[(int) (numberStr.charAt(i)) - 48]);
			} else {
				builder.append(numberStr.charAt(i));
			}
		}
		return builder.toString();
	}

	public static String withEnglishNumerals(String text) {
		return text.chars().mapToObj(number -> IDENTICAL_NUMBER_IN_ENGLISH.getOrDefault((char) number, (char) number))
				.map(Objects::toString).collect(Collectors.joining());
	}

	public static String toPersianWord(String amount) {
		try {
			String zero = "صفر";
			if ("0".equals(amount)) {
				return zero;
			}
			if (amount.length() > 66) {
				return "";
			}
			// using big decimal in order to convert persian numbers .
			// replace all part is used to remove all the non-numeric characters
			String processedInput = (new BigDecimal(amount.replaceAll("[^\\d.]", ""))).toString();

			ArrayList<String> splitNumber = prepareNumber(processedInput);
			ArrayList<String> result = new ArrayList<>();
			int splitLength = splitNumber.size();
			for (int i = 0; i < splitLength; ++i) {
				String sectionTitle = PERSIAN_NUMBER_NAME_CONTEXT.get(4).get((splitLength - (i + 1)));
				String converted = threeNumbersToLetter(splitNumber.get(i));
				if (!"".equals(converted)) {
					result.add(converted + " " + sectionTitle);
				}
			}

			String splitter = " و ";

			return StringUtils.joinWith(splitter, result.toArray());
		} catch (Exception e) {
			return "";
		}
	}

	private static List<List<String>> getPersianNumberNameContext() {
		List<List<String>> words = new ArrayList<>();

		List<String> temp = new ArrayList<>();

		temp.add("");
		temp.add("یک");
		temp.add("دو");
		temp.add("سه");
		temp.add("چهار");
		temp.add("پنج");
		temp.add("شش");
		temp.add("هفت");
		temp.add("هشت");
		temp.add("نه");
		words.add(temp);

		temp = new ArrayList<>();
		temp.add("ده");
		temp.add("یازده");
		temp.add("دوازده");
		temp.add("سیزده");
		temp.add("چهارده");
		temp.add("پانزده");
		temp.add("شانزده");
		temp.add("هفده");
		temp.add("هجده");
		temp.add("نوزده");
		words.add(temp);

		temp = new ArrayList<>();
		temp.add("");
		temp.add("");
		temp.add("بیست");
		temp.add("سی");
		temp.add("چهل");
		temp.add("پنجاه");
		temp.add("شصت");
		temp.add("هفتاد");
		temp.add("هشتاد");
		temp.add("نود");
		words.add(temp);

		temp = new ArrayList<>();
		temp.add("");
		temp.add("یکصد");
		temp.add("دویست");
		temp.add("سیصد");
		temp.add("چهارصد");
		temp.add("پانصد");
		temp.add("ششصد");
		temp.add("هفتصد");
		temp.add("هشتصد");
		temp.add("نهصد");
		words.add(temp);

		temp = new ArrayList<>();
		temp.add("");
		temp.add("هزار");
		temp.add("میلیون");
		temp.add("میلیارد");
		temp.add("بیلیون");
		temp.add("بیلیارد");
		temp.add("تریلیون");
		temp.add("تریلیارد");
		temp.add("کوآدریلیون");
		temp.add("کادریلیارد");
		temp.add("کوینتیلیون");
		temp.add("کوانتینیارد");
		temp.add("سکستیلیون");
		temp.add("سکستیلیارد");
		temp.add("سپتیلیون");
		temp.add("سپتیلیارد");
		temp.add("اکتیلیون");
		temp.add("اکتیلیارد");
		temp.add("نانیلیون");
		temp.add("نانیلیارد");
		temp.add("دسیلیون");
		words.add(temp);

		return words;
	}

	private static Collection<String> splitStringBySize(String str, int size) {
		ArrayList<String> split = new ArrayList<>();
		for (int i = 0; i < str.length() / size; i++) {
			split.add(str.substring(i * size, Math.min((i + 1) * size, str.length())));
		}
		return split;
	}

	private static String threeNumbersToLetter(String num) {

		String splitter = " و ";

		if ("".equals(num)) {
			return "";
		}
		int parsedInt = Integer.parseInt(num);
		if (parsedInt < 10) {
			return PERSIAN_NUMBER_NAME_CONTEXT.get(0).get(parsedInt);
		}
		if (parsedInt < 20) {
			return PERSIAN_NUMBER_NAME_CONTEXT.get(1).get(parsedInt - 10);
		}
		if (parsedInt < 100) {
			int one = parsedInt % 10;
			int ten = (parsedInt - one) / 10;
			if (one > 0) {
				return PERSIAN_NUMBER_NAME_CONTEXT.get(2).get(ten) + splitter
						+ PERSIAN_NUMBER_NAME_CONTEXT.get(0).get(one);
			}
			return PERSIAN_NUMBER_NAME_CONTEXT.get(2).get(ten);
		}
		int one = parsedInt % 10;
		int hundreds = (parsedInt - (parsedInt % 100)) / 100;
		int ten = (parsedInt - ((hundreds * 100) + one)) / 10;
		ArrayList<String> out = new ArrayList<>();
		out.add(PERSIAN_NUMBER_NAME_CONTEXT.get(3).get(hundreds));
		int secondPart = ((ten * 10) + one);
		if (secondPart > 0) {
			if (secondPart < 10) {
				out.add(PERSIAN_NUMBER_NAME_CONTEXT.get(0).get(secondPart));
			} else if (secondPart < 20) {
				out.add(PERSIAN_NUMBER_NAME_CONTEXT.get(1).get(secondPart - 10));
			} else {
				out.add(PERSIAN_NUMBER_NAME_CONTEXT.get(2).get(ten));
				if (one > 0) {
					out.add(PERSIAN_NUMBER_NAME_CONTEXT.get(0).get(one));
				}
			}
		}
		return StringUtils.joinWith(splitter, out.toArray());
	}

	private static ArrayList<String> prepareNumber(String number) {
		int length = number.length() % 3;
		String resultNumber = number;
		if (length == 1) {
			resultNumber = "00" + number;
		} else if (length == 2) {
			resultNumber = "0" + number;
		}
		return (ArrayList<String>) splitStringBySize(resultNumber, 3);
	}


	/**
	 * use {@link this#toPersianAmount}
	 */
	@Deprecated
	public static String convertAmountToPersian(Long amount) {
		return toPersianAmount(amount);
	}

	/**
	 * use {@link this#withPersianNumerals}
	 */
	@Deprecated
	public static String convertNumberToPersian(String numberStr) {
		return withPersianNumerals(numberStr);
	}

	/**
	 * use {@link this#withEnglishNumerals}
	 */
	@Deprecated
	public static String convertNumbersToEnglish(String text) {
		return withEnglishNumerals(text);
	}

	/**
	 * use {@link this#toPersianWord}
	 */
	@Deprecated
	public static String convertNumberToWord(String amount) {
		return toPersianWord(amount);
	}

}