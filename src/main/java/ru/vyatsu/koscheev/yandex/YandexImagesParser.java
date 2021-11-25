package ru.vyatsu.koscheev.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.vyatsu.koscheev.Parser;

import java.util.ArrayList;

public class YandexImagesParser implements Parser<ArrayList<String>> {
    public ArrayList<String> Parse(WebDriver driver) {
        ArrayList<String> list = new ArrayList<>();

        var images = driver
                .findElements(By.xpath("//div[contains(@class, 'serp-item_type_search')]//img"));

        for (var img : images)
            list.add(img.getAttribute("src"));

        return list;
    }
}
