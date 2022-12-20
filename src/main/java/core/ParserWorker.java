package core;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class ParserWorker<T> {
    Parser<T> parser;
    ParserSettings parserSettings;
    HtmlLoader loader;
    boolean isActive;

    public ParserSettings getParserSettings() {
        return parserSettings;
    }

    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
        loader = new HtmlLoader(parserSettings);
    }

    public Parser<T> getParser() {
        return parser;
    }

    public ParserWorker(Parser<T> parser) {
        this.parser = parser;
    }

    /*public ParserWorker(Parser<T> parser, ParserSettings parserSettings) {
        this(parser);
        this.parserSettings = parserSettings;
    }*/

    public void Start() throws IOException {
        isActive = true;
        Worker();
    }

    public void Abort() {
        isActive = false;
    }

    private void Worker() throws IOException {
        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                // OnCompleted?.Invoke(this);
                return;
            }
            Document document = loader.GetSourceByPageId(i);
            // var domParser = new HtmlParser();
            // var document = await domParser.ParseDocumentAsync(source);
            T result = parser.Parse(document);
            onNewDataList.get(0).OnNewData(this,result);
            //OnNewData?.Invoke(this, result);
        }
        onCompletedList.get(0).OnCompleted(this);
        // OnCompleted?.Invoke(this);
        isActive = false;
    }
    ArrayList<OnNewDataHandler> onNewDataList = new ArrayList<>();
    ArrayList<OnCompleted> onCompletedList = new ArrayList<>();
    /*
    public void OnNewData(EventHandler handler);
    public void OnCompleted(EventHandler handler);
    */

    /*
    public event Action<object, T> OnNewData;
    public event Action<object> OnCompleted;
    */

    /*
    private async void Worker()
        {
            for (int i = parserSettings.StartPoint; i <= parserSettings.EndPoint; i++)
            {
                if (!isActive)
                {
                    OnCompleted?.Invoke(this);
                    return;
                }
                var source = await loader.GetSourceByPageId(i);
                var domParser = new HtmlParser();

                var document = await domParser.ParseDocumentAsync(source);
                var result = parser.Parse(document);
                OnNewData?.Invoke(this, result);
            }
            OnCompleted?.Invoke(this);
            isActive = false;
        }*/

    public interface OnNewDataHandler<T> {
        // Change signature as appropriate of course
        void OnNewData(Object sender, T e);
    }
    public interface OnCompleted {
        // Change signature as appropriate of course
        void OnCompleted(Object sender);
    }

}
