package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PayByDebitCardPage {
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerCardField = $x("/html/body/div/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvcCodeField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $(byText("Успешно"));
    private SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement invalidFormatNotification = $(byText("Неверный формат"));
    private SelenideElement blankFieldNotification = $(byText("Поле обязательно для заполнения"));
    private SelenideElement invalidDateNotification = $(byText("Неверно указан срок действия карты"));
    private SelenideElement expiredDateNotification = $(byText("Истёк срок действия карты"));

    public void enterDataInForm(DataHelper.CardNumber cardNumber, DataHelper.MonthInfo monthInfo, DataHelper.YearInfo yearInfo, DataHelper.CardName cardName, DataHelper.CvcCode cvcCode) {
        cardNumberField.setValue(cardNumber.getNumber());
        monthField.setValue(monthInfo.getMonth());
        yearField.setValue(yearInfo.getYear());
        ownerCardField.setValue(cardName.getName());
        cvcCodeField.setValue(cvcCode.getCvc());
        continueButton.click();
    }

    public void successNotificationVisibility() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorNotificationVisibility() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void invalidFormatNotificationVisibility() {
        invalidFormatNotification.shouldBe(visible);
    }

    public void blankFieldNotificationVisibility() {
        blankFieldNotification.shouldBe(visible);
    }

    public void invalidDateNotificationVisibility() {
        invalidDateNotification.shouldBe(visible);
    }

    public void expiredDateNotificationVisibility() {
        expiredDateNotification.shouldBe(visible);
    }

}
