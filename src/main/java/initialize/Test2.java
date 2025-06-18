package initialize_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/20 20:04
 */
public class Test2{

    public static int k = 0;
    public static Test2 t1 = new Test2("t1");
    public static Test2 t2 = new Test2("t2");
    public static int i = print("i");
    public static int n = 99;
    private int a = 0;
    public int j = print("j");

    {
        print("构造代码块");
    }

    static{
        print("静态代码块");
    }

    public Test2(String str){
        System.out.println((++k)+":"+str+"  i="+i+"  n="+n);
        ++i;
        ++n;
    }

    private static int print(String str){
        System.out.println((++k)+":"+str+"  i="+i+"  n="+n);
        ++n;
        return++i;
    }

    public static void main(String[] args) {
        Test2 t = new Test2("init");
    }

}
