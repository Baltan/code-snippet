/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package overload_test;

public class Test {

	public static void main(String[] args) {
		Print p1 = new Print();

		p1.print(4, "a");
		p1.print("a", 4);
		Print.println(4, "a");
		Print.println("a", 4);
	}

}
