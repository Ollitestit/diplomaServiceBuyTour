package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement buttonBuy = $(byText("Купить"));
    private SelenideElement buttonBuyOnCredit = $(byText("Купить в кредит"));

    public PayByDebitCardPage payByDebitCard() {
        buttonBuy.click();
        return new PayByDebitCardPage();
    }

    public PayOnCreditPage payOnCredit() {
        buttonBuyOnCredit.click();
        return new PayOnCreditPage();
    }

}
