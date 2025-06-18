package pattern_test.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 15:25
 */
public class LanguageRepository implements Container<String> {
    private List<String> languages;

    {
        languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("JavaScript");
        languages.add("C");
        languages.add("C++");
        languages.add("C#");
        languages.add("TypeScript");
        languages.add("Ruby");
        languages.add("Swift");
    }

    @Override
    public Iterator<String> iterator() {
        return new LanguageIterator();
    }

    private class LanguageIterator implements Iterator<String> {

        private int index1 = -1;
        private int index2 = languages.size();

        @Override
        public boolean hasNext() {
            return index1 + 1 < languages.size();
        }

        @Override
        public String next() {
            return languages.get(++index1);
        }

        @Override
        public boolean hasPrevious() {
            return index2 - 1 >= 0;
        }

        @Override
        public String previous() {
            return languages.get(--index2);
        }
    }
}
