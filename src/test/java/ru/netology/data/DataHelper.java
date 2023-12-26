package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;



public class DataHelper {
    private static final Faker enFaker = new Faker(new Locale("en"));
    private static final Faker ruFaker = new Faker(new Locale("ru"));
    private static final Faker arFaker = new Faker(new Locale("ar"));

    private DataHelper() {
    }

    public static CardNumber getApprovedCardNumber() {
        return new CardNumber("4444_4444_4444_4441");
    }

    public static CardNumber getDeclinedCardNumber() {
        return new CardNumber("4444_4444_4444_4442");
    }

    public static MonthInfo getValidMonth() {
        return new MonthInfo(generateRandomMonth(1, 13));
    }

    public static MonthInfo getInvalidMonth() {
        return new MonthInfo(generateRandomMonth(13, 99));
    }

    public static MonthInfo getInvalidMonthZero() {
        return new MonthInfo("00");
    }

    public static MonthInfo getInvalidMonthOneSymbol() {
        return new MonthInfo(enFaker.numerify("#"));
    }

    public static YearInfo getValidYear() {
        return new YearInfo(generateRandomYear(24, 28));
    }

    public static YearInfo getInvalidYear() {
        return new YearInfo(generateRandomYear(29, 99));
    }

    public static YearInfo getInValidYearBeforeCurrent() {
        return new YearInfo(generateRandomYear(10, 23));
    }

    public static YearInfo getInvalidYearZero() {
        return new YearInfo("00");
    }

    public static YearInfo getInvalidYearOneSymbol() {
        return new YearInfo(enFaker.numerify("#"));
    }

    public static CardName getValidName() {
        return new CardName(enFaker.name().fullName());
    }

    public static CardName getNameInCyrillic() {
        return new CardName(ruFaker.name().fullName());
    }

    public static CardName getNameInArabic() {
        return new CardName(arFaker.name().fullName());
    }

    public static CardName getNameInNumbers() {
        return new CardName(enFaker.numerify("##########"));
    }

    public static CardName getNameInSpecSymbols() {
        return new CardName("$>[%&@<}^");
    }

    public static CvcCode getValidCvcCode() {
        return new CvcCode(enFaker.numerify("###"));
    }

    public static CvcCode getInvalidCvcCode() {
        return new CvcCode(enFaker.numerify("##"));
    }

    public static CardNumber getCardNumberBlank() {
        return new CardNumber("");
    }

    public static MonthInfo getMonthBlank() {
        return new MonthInfo("");
    }

    public static YearInfo getYearBlank() {
        return new YearInfo("");
    }

    public static CardName getCardNameBlank() {
        return new CardName("");
    }

    public static CvcCode getCvcCodeBlank() {
        return new CvcCode("");
    }

    public static String generateRandomMonth(int min, int max) {
        Random randomMonth = new Random();
        int randomMon = randomMonth.nextInt(max - min + 1) + min;
        return String.format("%02d", randomMon);
    }

    public static String generateRandomYear(int min, int max) {
        Random randomYear = new Random();
        int randomY = randomYear.nextInt(max - min + 1) + min;
        return String.format("%02d", randomY);
    }

    @Value
    public static class CardNumber {
        String number;
    }

    @Value
    public static class MonthInfo {
        String month;
    }

    @Value
    public static class YearInfo {
        String year;
    }

    @Value
    public static class CardName {
        String name;
    }

    @Value
    public static class CvcCode {
        String cvc;
    }
}

