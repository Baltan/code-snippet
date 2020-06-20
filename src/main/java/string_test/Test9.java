package string_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 10:02
 */
public class Test9 {
    public static void main(String[] args) {
        /**
         * JDK1.8开始字符串拼接操作JVM底层会优化为StringBuilder类的操作
         *
         * <pre>
         *     /Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/bin/javap -c string_test.Test9
         * Compiled from "Test9.java"
         * public class string_test.Test9 {
         *   public string_test.Test9();
         *     Code:
         *        0: aload_0
         *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         *        4: return
         *
         *   public static void main(java.lang.String[]);
         *     Code:
         *        0: ldc           #2                  // String a
         *        2: astore_1
         *        3: new           #3                  // class java/lang/StringBuilder
         *        6: dup
         *        7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *       10: aload_1
         *       11: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       14: ldc           #6                  // String b
         *       16: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       19: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()
         *       Ljava/lang/String;
         *       22: astore_2
         *       23: new           #3                  // class java/lang/StringBuilder
         *       26: dup
         *       27: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *       30: aload_2
         *       31: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       34: ldc           #8                  // String c
         *       36: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       39: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()
         *       Ljava/lang/String;
         *       42: astore_3
         *       43: new           #3                  // class java/lang/StringBuilder
         *       46: dup
         *       47: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *       50: aload_3
         *       51: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       54: ldc           #9                  // String d
         *       56: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       59: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()
         *       Ljava/lang/String;
         *       62: astore        4
         *       64: new           #3                  // class java/lang/StringBuilder
         *       67: dup
         *       68: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *       71: aload         4
         *       73: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       76: ldc           #10                 // String e
         *       78: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       81: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()
         *       Ljava/lang/String;
         *       84: astore        5
         *       86: new           #3                  // class java/lang/StringBuilder
         *       89: dup
         *       90: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *       93: aload         5
         *       95: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *       (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *       98: ldc           #11                 // String f
         *      100: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *      (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *      103: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()
         *      Ljava/lang/String;
         *      106: astore        6
         *      108: new           #3                  // class java/lang/StringBuilder
         *      111: dup
         *      112: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *      115: aload         6
         *      117: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *      (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *      120: ldc           #12                 // String g
         *      122: invokevirtual #5                  // Method java/lang/StringBuilder.append:
         *      (Ljava/lang/String;)Ljava/lang/StringBuilder;
         *      125: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()
         *      Ljava/lang/String;
         *      128: astore        7
         *      130: getstatic     #13                 // Field java/lang/System.out:Ljava/io/PrintStream;
         *      133: aload         7
         *      135: invokevirtual #14                 // Method java/io/PrintStream.println:
         *      (Ljava/lang/String;)V
         *      138: return
         * }
         * </pre>
         */
        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = s2 + "c";
        String s4 = s3 + "d";
        String s5 = s4 + "e";
        String s6 = s5 + "f";
        String s7 = s6 + "g";
        System.out.println(s7);
    }
}
