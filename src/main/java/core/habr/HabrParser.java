package core.habr;

import core.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HabrParser implements Parser<ArrayList<String>> {

    @Override
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<>();

        // получение текста списка статей (постов)
        Elements elements = document.getElementsByTag("a");// select("a[class^=tm-article-snippet]");
        for (Element elem : elements) {
            if (elem.className().contains("tm-article-snippet"))
                list.add(elem.text());
        }

        /*var items = document.QuerySelectorAll("a").
            Where(item => item.ClassName != null && item.ClassName.Contains("tm-article-snippet"));
        foreach(var item in items)
        {
            list.Add(item.TextContent);
        }*/
        return list;
    }
}
