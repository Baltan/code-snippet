package junit.test;

import junit.Test1;
import org.junit.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Test1 Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 15, 2018</pre>
 */
public class Test1Test {

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    /**
     * Method: add(double a, double b)
     */
    @Test
    public void testAdd() {
        int a = new Test1().add(1, 2);
        assertThat(a, is(3));
    }

    /**
     * Method: substract(double a, double b)
     */
    @Test
    public void testSubstract() {
        int a = new Test1().substract(1, 2);
        assertThat(a, allOf(greaterThan(-2), lessThan(0)));
    }

    /**
     * Method: multiply(double a, double b)
     */
    @Test
    public void testMultiply() {
        int a = new Test1().multiply(1, 2);
        assertThat(a, anyOf(greaterThan(-2), lessThan(0)));
    }

    /**
     * Method: divide(double a, double b)
     */
    @Test
    public void testDivide() {
        int a = new Test1().divide(2, 1);
        assertThat(a, equalTo(2));
    }

    @Test(expected = ArithmeticException.class)
    public void testException() {
        System.out.println(1 / 0);
    }

    @Test(timeout = 10)
    public void testTimeout() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

} 
