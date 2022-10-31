package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.makeAmount;

class MoneyTransferTest {

  @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
      var loginPage = open("http://localhost:9999", LoginPageV3.class);
      var authInfo = DataHelper.getAuthInfo();
      var verificationPage = loginPage.validLogin(authInfo);
      var verificationCode = DataHelper.getVerificationCodeFor(authInfo);

      var dashboardPage = verificationPage.validVerify(verificationCode);
      var firstCard = DataHelper.firstCard();
      var secondCard = DataHelper.secondCard();
      var firstCardBalance = dashboardPage.getBalance(firstCard);
      var secondCardBalance = dashboardPage.getBalance(secondCard);
      var amount = makeAmount(firstCardBalance);
      var expectedBalanceFirstCard = firstCardBalance - amount;
      var expectedBalanceSecondCard = secondCardBalance + amount;
      var transferPage = dashboardPage.selectCard(secondCard);
      dashboardPage = transferPage.validTransfer(String.valueOf(amount), firstCard);
      var actualBalanceFirstCard = dashboardPage.getBalance(firstCard);
      var actualBalanceSecondCard = dashboardPage.getBalance(secondCard);
      assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
      assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
      verificationPage.validVerify(verificationCode);
    }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV2() {
    var loginPage = open("http://localhost:9999", LoginPageV3.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV3() {
    var loginPage = open("http://localhost:9999", LoginPageV3.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }
}

