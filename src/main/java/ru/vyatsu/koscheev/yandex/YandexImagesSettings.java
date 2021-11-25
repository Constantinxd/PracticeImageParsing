package ru.vyatsu.koscheev.yandex;

import ru.vyatsu.koscheev.ParserSettings;

public class YandexImagesSettings extends ParserSettings {
    public YandexImagesSettings(String request, String folder) {
        this.baseUrl = "https://yandex.ru/images/search?from=tabbar&text=" + request;
        this.request = request;
        this.folder = folder;
    }
}
