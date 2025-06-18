package enumeration; 

/**
 * Description:
 *
 * @author Baltan
 * @date 2017/12/28 14:58
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(Size.EXTRA_LARGE.toString());

        Size[] sizes = Size.values();
        for (Size s : sizes) {
            System.out.print(s + "\t");
        }
        System.out.println();

        System.out.println(Size.MEDIUM.ordinal());

        //和toString()类似
        System.out.println(Size.EXTRA_SMALL.name());

        System.out.println(Size.EXTRA_SMALL.compareTo(Size.EXTRA_LARGE));

        Size size1 = Size.MEDIUM;
        switch (size1) {
            case EXTRA_SMALL:
                System.out.println("xs");
                break;
            case MEDIUM:
                System.out.println("m");
                break;
            case EXTRA_LARGE:
                System.out.println("xl");
                break;
        }
    }
}
