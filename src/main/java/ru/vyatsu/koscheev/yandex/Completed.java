package ru.vyatsu.koscheev.yandex;

import ru.vyatsu.koscheev.OnCompletedHandler;

public class Completed implements OnCompletedHandler {
    @Override
    public void OnCompleted(Object sender) {
        System.out.println("Загрузка завершена");
    }
}
