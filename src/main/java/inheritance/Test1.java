package inheritance; 

public class Test1 extends TestParent {

	public Test1() {
		super();// 必须为第一句
		System.out.println("Test1构造方法");
	}

	public static void main(String[] args) {
		Test1 test1 = new Test1();
	}
}

class TestParent {

}