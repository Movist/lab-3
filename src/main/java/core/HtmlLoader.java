package core;

import core.habr.HabrParser;
import core.habr.HabrSettings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HtmlLoader {

    // HttpClient client;
    String url;

    public HtmlLoader(ParserSettings settings)
    {
        // Document doc = Jsoup.connect(LOGIN_FORM_URL).get();
        //client = new HttpClient();
        url = HabrSettings.BASE_URL+"/"+HabrSettings.PREFIX;//"{settings.BaseUrl}/{settings.Prefix}/";
    }

    public Document GetSourceByPageId(int id) throws IOException {
        String currentUrl = url.replace("{CurrentId}", Integer.toString(id)); //url.Replace("{CurrentId}", id.ToString());
        /*var response = await client.GetAsync(currentUrl);
        String source = null;

        if (response != null && response.StatusCode == HttpStatusCode.OK)
        {
            source = await response.Content.ReadAsStringAsync();
        }*/
        return Jsoup.connect(currentUrl).get();
    }
}
