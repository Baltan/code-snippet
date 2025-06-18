package comparator;

import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2021/8/12 23:24
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> keywords = Arrays.asList("2021年第二季度", "2020年第二季度", "2021年第一季度", "2020年第四季度", "主要行业门类",
                "2021年第三季度", "2020年第三季度");
        Collections.sort(keywords, new MyComparator());
        System.out.println(keywords);
    }

    private static class MyComparator implements Comparator<String> {
        private final String HEAD_STRING = "主要行业门类";
        private final Map<Character, Integer> keywordOrderMap = new HashMap<>();

        {
            keywordOrderMap.put('一', 4);
            keywordOrderMap.put('二', 3);
            keywordOrderMap.put('三', 2);
            keywordOrderMap.put('四', 1);
        }

        @Override
        public int compare(String o1, String o2) {
            if (Objects.equals(o1, HEAD_STRING)) {
                return -1;
            } else if (Objects.equals(o2, HEAD_STRING)) {
                return 1;
            } else {
                int year1 = Integer.valueOf(o1.substring(0, 4));
                int year2 = Integer.valueOf(o2.substring(0, 4));

                if (year1 < year2) {
                    return -1;
                } else if (year1 > year2) {
                    return 1;
                } else {
                    Character quarter1 = o1.charAt(6);
                    Character quarter2 = o2.charAt(6);
                    return keywordOrderMap.get(quarter2) - keywordOrderMap.get(quarter1);
                }
            }
        }
    }
}
