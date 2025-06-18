package string_test;

public class Test1 {
	public static void main(String[] args) {
		String str = "张三|李四|王五|赵六";
		String[] arr = str.split("\\|");
		System.out.println(arr.length);
	}
}
