package enum_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-01 15:18
 */
public class Test2 {
    public static void main(String[] args) {
        Computer.DELL.showEnglishName();
        Computer.DELL.showChineseName();
        Computer.DELL.showOrdinal();

        Computer.HP.showEnglishName();
        Computer.HP.showChineseName();
        Computer.HP.showOrdinal();
    }

    enum Computer {
        DELL("戴尔"),
        HP("惠普");

        private String chineseName;

        Computer(String chineseName) {
            this.chineseName = chineseName;
        }

        public void showEnglishName() {
            System.out.println(name());
//            System.out.println(toString());
        }

        public void showChineseName() {
            System.out.println(chineseName);
        }

        public void showOrdinal() {
            System.out.println(ordinal());
        }
    }
}
