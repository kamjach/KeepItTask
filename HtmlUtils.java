import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HtmlUtils {

    public static String extractHtmlContent(String url) throws BadConnectionException, IOException {
        StringBuilder allHtmlPage = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(setUpConnection(url).getInputStream()))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allHtmlPage.append(line);
            }
            return allHtmlPage.toString();
        }
    }

    private static HttpURLConnection setUpConnection(String url) throws BadConnectionException {
        try {
            URL httpUrl = new URI(url).toURL();
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return connection;
        } catch (IOException | URISyntaxException e) {
            throw new BadConnectionException("Cannot establish connection to " + url, e);
        }
    }
}