package io_test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerialTest {

	public static void main(String[] args) throws IOException {
		Person p = new Person("zhangsan", 21);
		// Person p = new Person("zhangsan", 21, "male");
		System.out.println(p);

		FileOutputStream fos = new FileOutputStream("/Users/Baltan/Desktop/test.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(p);
		oos.close();
	}

}
