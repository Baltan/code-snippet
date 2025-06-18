package pattern.iterator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 15:32
 */
public class Test1 {
    public static void main(String[] args) {
        LanguageRepository languageRepository = new LanguageRepository();
        Iterator<String> iterator1 = languageRepository.iterator();

        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        System.out.println("------------------");

        while (iterator1.hasPrevious()) {
            System.out.println(iterator1.previous());
        }

    }
}
