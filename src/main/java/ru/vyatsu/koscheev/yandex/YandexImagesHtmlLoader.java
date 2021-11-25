package ru.vyatsu.koscheev.yandex;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.vyatsu.koscheev.HtmlLoader;
import ru.vyatsu.koscheev.ParserSettings;

public class YandexImagesHtmlLoader extends HtmlLoader {
    public YandexImagesHtmlLoader(ParserSettings parserSettings) {
        this.url = parserSettings.baseUrl;
    }

    public void uploadContent(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        int last_height = 0;
        int new_height = 10;

        try {
            while (last_height != new_height) {
                last_height = Integer.parseInt(jse.executeScript("return window.pageYOffset").toString());
                jse.executeScript("window.scrollBy(0, window.innerHeight * 2)");
                new_height = Integer.parseInt(jse.executeScript("return window.pageYOffset").toString());

                Thread.sleep(2000);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
