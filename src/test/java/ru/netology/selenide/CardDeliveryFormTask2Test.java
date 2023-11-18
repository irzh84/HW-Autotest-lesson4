package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTask2Test {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldChooseTownDropDownList() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item__control").findBy(text("Москва")).click();
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Иван Петров");
        $("[data-test-id='phone'] input").setValue("+79010010101");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }

    @Test
    void shouldChooseDateCalendarForm() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        if (!generateDate(3, "MM").equals(generateDate(7, "MM"))) {
            $("[data-step='1']").click();
        };
        $$("[data-day]").findBy(text("24")).click();
        $("[data-test-id='name'] input").setValue("Иван Петров");
        $("[data-test-id='phone'] input").setValue("+79010010101");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на 24.11.2023"));
    }

    @Test
    void shouldChooseDateCalendarFormDecember() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        if (!generateDate(7, "MM").equals(generateDate(14, "MM"))) {
            $("[data-step='1']").click();
        };
        $$("[data-day]").findBy(text("2")).click();
        $("[data-test-id='name'] input").setValue("Иван Петров");
        $("[data-test-id='phone'] input").setValue("+79010010101");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на 02.12.2023"));
    }
}

