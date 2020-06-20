package io_test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserialTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("/Users/Baltan/Desktop/test.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Person p = (Person) ois.readObject();
		ois.close();
		System.out.println(p);
	}

}
