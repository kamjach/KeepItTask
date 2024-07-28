import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class Main {

    public static void main(String[] args) throws BadConnectionException, IOException {

        try {
            checkArguments(args);
        } catch (WrongUrlArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String url = args[0];
        String htmlContent = HtmlUtils.extractHtmlContent(url);
        Map<Integer, List<String>> unorderedLists = HtmlListParser.parseUnorderedLists(htmlContent);
        List<String> listWithMostItems = HtmlListParser.getListNumberWithMostItems(unorderedLists);
        if (isNull(listWithMostItems) || listWithMostItems.isEmpty()) {
            System.out.println("No unordered lists found");
            System.exit(1);
        }
        String lastItem = HtmlListParser.getLastItemFromList(listWithMostItems);

        System.out.println(lastItem);
    }

    private static void checkArguments(String[] args) throws WrongUrlArgumentException {

        if (args.length != 1) {
            throw new WrongUrlArgumentException("No URL provided");
        } else if (!args[0].startsWith("http")) {
            throw new WrongUrlArgumentException("Url should start with http/https");
        } else if (args[0].contains(" ")) {
            throw new WrongUrlArgumentException("Url should not contain spaces");
        }
    }
}
