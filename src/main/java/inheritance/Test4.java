package inheritance; 

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/8/10 17:31
 */
public class Test4 {
    public static void main(String[] args) {

        B b = new B();
        b.printNum(1);
        b.printNum(1.0f);
        b.printNum(1.0);


        B[] bArray = new B[10];
        A[] aArray = bArray;

        aArray[0] = new B();
        aArray[1] = new A(); // ArrayStoreException
    }
}
