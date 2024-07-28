import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlListParser {

    private static final Pattern UNORDERED_LIST_PATTERN = Pattern.compile("<ul>(.*?)</ul>", Pattern.DOTALL);
    private static final Pattern LIST_ITEM_PATTERN = Pattern.compile("<li>(.*?)</li>", Pattern.DOTALL);

    public static Map<Integer, List<String>> parseUnorderedLists(String htmlContent)
    {
        int counter = 1;
        Map<Integer, List<String>> unordererdMap = new HashMap<>();
        Matcher unorderedListMatcher = UNORDERED_LIST_PATTERN.matcher(htmlContent);

        while (unorderedListMatcher.find()) {
            List<String> listItems = new ArrayList<>();
            Matcher listItemMatcher = LIST_ITEM_PATTERN.matcher(unorderedListMatcher.group(1));
            while (listItemMatcher.find()) {
                listItems.add(listItemMatcher.group(1));
            }
            unordererdMap.put(counter++, listItems);
        }
        return unordererdMap;
    }

    public static List<String> getListNumberWithMostItems(Map<Integer, List<String>> unorderedLists)
    {
        int max = 0;
        int key = 0;
        for (Map.Entry<Integer, List<String>> entry : unorderedLists.entrySet()) {
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
                key = entry.getKey();
            }
        }
        return unorderedLists.get(key);
    }

    public static String getLastItemFromList(List<String> listWithMostItems)
    {
        return listWithMostItems.get(listWithMostItems.size() - 1);
    }
}
