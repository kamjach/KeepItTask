import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws BadConnectionException, IOException {

        String url = args[0];
        String htmlContent = HtmlUtils.extractHtmlContent(url);
        Map<Integer, List<String>> unorderedLists =  HtmlListParser.parseUnorderedLists(htmlContent);
        List<String> listWithMostItems = HtmlListParser.getListNumberWithMostItems(unorderedLists);
        String lastItem = HtmlListParser.getLastItemFromList(listWithMostItems);

        System.out.println(lastItem);

    }
}
