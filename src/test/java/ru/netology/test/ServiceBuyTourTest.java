package ru.netology.test;
;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PayByDebitCardPage;
import ru.netology.page.PayOnCreditPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class ServiceBuyTourTest {
    MainPage mainPage = new MainPage();
    PayByDebitCardPage payByDebitCardPage = new PayByDebitCardPage();
    PayOnCreditPage payOnCreditPage = new PayOnCreditPage();

    @BeforeEach
    void setup() {
        open("http://localhost:8080/", MainPage.class);
    }

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }
    //Тестирование формы отправки данных карты при оплате дебетовой картой
    @Test
    void shouldValidCardApprovedForPayDebitCard() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.successNotificationVisibility();
        assertEquals("APPROVED", SQLHelper.getDebitCardPaymentStatus());
    }

    @Test
    void shouldInvalidCardDeclinedForPayDebitCard() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getDeclinedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.errorNotificationVisibility();
        assertEquals("DECLINED", SQLHelper.getDebitCardPaymentStatus());
    }

    @Test
    void shouldErrorMessageForInvalidMonth() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getInvalidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageMonthZero() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getInvalidMonthZero();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageMonthOneSymbol() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getInvalidMonthOneSymbol();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageInvalidYear() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInvalidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageYearBeforeCurrent() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInValidYearBeforeCurrent();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.expiredDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageYearZero() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInvalidYearZero();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.expiredDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageYearOneSymbol() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInvalidYearOneSymbol();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInCyrillic() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInCyrillic();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInArabic() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInArabic();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInNumbers() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInNumbers();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInSymbols() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInSpecSymbols();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageInvalidCvcCode() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getInvalidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldCardNumber() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getCardNumberBlank();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldMonth() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getMonthBlank();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldYear() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getYearBlank();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldOwner() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getCardNameBlank();
        var cvcCode = DataHelper.getValidCvcCode();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankCvcCode() {
        mainPage.payByDebitCard();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getCvcCodeBlank();
        payByDebitCardPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payByDebitCardPage.blankFieldNotificationVisibility();
    }

    //Тестирование формы отправки данных карты при оплате в кредит
    @Test
    void shouldValidCardApprovedForPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.successNotificationVisibility();
        assertEquals("APPROVED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    void shouldInvalidCardDeclinedForPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getDeclinedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.errorNotificationVisibility();
        assertEquals("DECLINED", SQLHelper.getCreditPaymentStatus());
    }


    @Test
    void shouldErrorMessageForInvalidMonthPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getInvalidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageMonthZeroPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getInvalidMonthZero();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageMonthOneSymbolPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getInvalidMonthOneSymbol();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageInvalidYearPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInvalidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageYearBeforeCurrentPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInValidYearBeforeCurrent();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.expiredDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageYearZeroPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInvalidYearZero();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.expiredDateNotificationVisibility();
    }

    @Test
    void shouldErrorMessageYearOneSymbolPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getInvalidYearOneSymbol();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInCyrillicPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInCyrillic();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInArabicPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInArabic();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInNumbersPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInNumbers();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageNameInSymbolsPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getNameInSpecSymbols();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageInvalidCvcCodePayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getInvalidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.invalidFormatNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldCardNumberPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getCardNumberBlank();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldMonthPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getMonthBlank();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldYearPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getYearBlank();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankFieldOwnerPayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getCardNameBlank();
        var cvcCode = DataHelper.getValidCvcCode();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.blankFieldNotificationVisibility();
    }

    @Test
    void shouldErrorMessageWithBlankCvcCodePayOnCredit() {
        mainPage.payOnCredit();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var monthInfo = DataHelper.getValidMonth();
        var yearInfo = DataHelper.getValidYear();
        var cardName = DataHelper.getValidName();
        var cvcCode = DataHelper.getCvcCodeBlank();
        payOnCreditPage.enterDataInForm(cardNumber, monthInfo, yearInfo, cardName, cvcCode);
        payOnCreditPage.blankFieldNotificationVisibility();
    }

}
