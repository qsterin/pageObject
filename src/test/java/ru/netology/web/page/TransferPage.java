package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement fromInput = $("[data-test-id=from input");
    private SelenideElement transferPageTitle = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id=error-message]");
    private SelenideElement amountInput = $("[data-test-id=amount]");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage() {
        transferPageTitle.shouldBe(Condition.visible);

    }
    public DashboardPage validTransfer(String transferAmount, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(transferAmount);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void transfer(String transferAmount, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(transferAmount);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findError(String expectedError){
        errorMessage.shouldHave(Condition.exactText(expectedError), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public void validLogin(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void errorMessage(String expectedError) {
        errorMessage.shouldHave(Condition.exactText(expectedError), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
