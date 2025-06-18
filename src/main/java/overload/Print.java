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

class Print {

	public void print(int a, String b) {
		System.out.println(a + b);
	}

	public void print(String a, int b) {
		System.out.println(a + b);
	}

	public static void println(int a, String b) {
		System.out.println(a + b);
	}

	public static void println(String a, int b) {
		System.out.println(a + b);
	}
}
