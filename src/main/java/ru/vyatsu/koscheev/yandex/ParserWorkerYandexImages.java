package ru.vyatsu.koscheev.yandex;

import org.openqa.selenium.WebDriver;
import ru.vyatsu.koscheev.*;

import java.util.ArrayList;

public class ParserWorkerYandexImages<T> {
    private Parser<T> parser;
    private ParserSettings parserSettings;
    private HtmlLoader loader;
    private boolean isActive;
    private WebDriver driver;

    public final ArrayList<OnNewDataHandler<T>> onNewDataList;
    public final ArrayList<OnCompletedHandler> onCompletedList;

    public ParserWorkerYandexImages(Parser<T> parser) {
        setParser(parser);
        setDriver();
        this.onNewDataList = new ArrayList<>();
        this.onCompletedList = new ArrayList<>();
    }

    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
        loader = new YandexImagesHtmlLoader(this.parserSettings);
    }

    public void setParser(Parser<T> parser) { this.parser = parser; }

    private void setDriver() {
        DriverLoader driverLoader = new YandexImagesDriverLoader();
        driver = driverLoader.getDriver();
    }

    public void Start() {
        isActive = true;
        Worker();
    }

    public void Abort() {
        isActive = false;
    }

    protected void Worker() {
        driver.get(loader.getUrl());
        loader.uploadContent(driver);

        T result = parser.Parse(driver);
        onNewDataList.get(0).OnNewData(this, result);
        isActive = false;
        driver.quit();
        onCompletedList.get(0).OnCompleted(this);
    }

    public static void main(String[] args) {
        ParserWorkerYandexImages<ArrayList<String>> parser = new ParserWorkerYandexImages<>(new YandexImagesParser());

        String request = "цвет индиго";
        String folder = "images";

        parser.setParserSettings(new YandexImagesSettings(request, folder));
        parser.onNewDataList.add(new DownloadImages(parser.parserSettings));
        parser.onCompletedList.add(new Completed());

        try {
            parser.Start();
            Thread.sleep(5000);
            parser.Abort();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
