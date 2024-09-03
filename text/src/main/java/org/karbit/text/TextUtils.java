package org.karbit.text;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

public abstract class TextUtils {

	private static final int LOG_MAX_LENGTH = 500;

	private static final Character[] PERSIAN_VOWELS_NEEDED_ZWNJ = { 'ة', 'ه' };

	private static final Character[] PERSIAN_VOWELS_NOT_NEEDED_ZWNJ = { 'و', 'ؤ', 'ا', 'آ', 'إ' };

	public static Map<Character, Character> characterMapper(char[] asKey, char[] asValue) {
		if (asKey.length != asValue.length) {
			throw new IllegalArgumentException("parameter length is not equal!");
		}

		return IntStream.range(0, asKey.length).boxed().collect(Collectors.toMap(i -> asKey[i], i -> asValue[i]));
	}

	public static String summarize(String message) {
		return summarize(message, LOG_MAX_LENGTH);
	}

	public static String summarize(String message, int limit) {
		if (message == null || message.length() <= limit) {
			return message;
		}

		StringBuilder builder = new StringBuilder(message);
		return builder.delete(limit + 1, builder.length()).append("...").toString();
	}

	public static String addressingSomeone(String firstName) {
		if (StringUtils.isEmpty(firstName)) {
			firstName = firstName.trim();
			char lastCharacter = getLastCharacter(firstName);
			if (Arrays.asList(PERSIAN_VOWELS_NEEDED_ZWNJ).contains(lastCharacter)) {
				return firstName + getFriendlyNameSuffixWithSemiSpace() + getNameSuffix();
			} else if (Arrays.asList(PERSIAN_VOWELS_NOT_NEEDED_ZWNJ).contains(lastCharacter)) {
				return firstName + getFriendlyNameSuffixWithoutSemiSpace() + getNameSuffix();
			}
			return firstName + getNameSuffix();
		} else {
			return getUserDefaultName();
		}
	}

	private static String getFriendlyNameSuffixWithSemiSpace() {
		return "\u200cی";
	}

	private static String getFriendlyNameSuffixWithoutSemiSpace() {
		return "ی";
	}

	private static String getNameSuffix() {
		return "\u0020عزیز";
	}

	private static String getUserDefaultName() {
		return "کاربر گرامی";
	}

	private static Character getLastCharacter(String name) {
		return name.charAt(name.length() - 1);
	}

}
