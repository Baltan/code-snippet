package return_finnaly;

public class Test3 {

	public static void main(String[] args) {
		System.out.println(test().getName());
	}

	public static Person test() {
		Person p = new Person("zhangsan");
		try {
			return p;
		} finally {
			p.setName("lisi");
		}
	}
}

class Person {
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}