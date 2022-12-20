package core.habr;

import core.ParserSettings;

public class HabrSettings extends ParserSettings {

    public static String BASE_URL  = "https://habrahabr.ru"; // "https://habr.com/ru/all/"
    public static String PREFIX  = "page{CurrentId}";

    public HabrSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
    }
}

