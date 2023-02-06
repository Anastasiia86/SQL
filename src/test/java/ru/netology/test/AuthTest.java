package ru.netology.test;


import org.junit.jupiter.api.*;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataHelper.DataBase.*;
import static ru.netology.data.DataHelper.getInvalidPass;
import static ru.netology.data.DataHelper.getValidPass;

public class AuthTest {

    @BeforeAll
    public static void setUpForSUT() {
        clearingTablesForSUT();
    }

    @BeforeEach
    public void setUp() {
        userGeneration();
    }

    @Test
    @DisplayName("Успешная авторизация")
    public void authTestPositive() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var user = getUserInfo();
        var verificationPage = loginPage.validLogin(user.getLogin(), getValidPass());
        var code = getCode(user);
        verificationPage.validVerify(code);
    }

    @Test
    @DisplayName("Блокировка пользователя после трех попыток ввода невалидного пароля")
    public void authBlocked() {
        open("http://localhost:9999");
        var user = getUserInfo();
        var loginPage = new LoginPage();
        loginPage.tripleInvalidPassword(user.getLogin(), getInvalidPass());
    }

    @AfterEach
    public void tearDown() {
        deletingCreatedUser();
    }
}



