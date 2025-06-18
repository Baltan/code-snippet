/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package return_finnaly;

import java.util.ArrayList;

public class Test1 {
	public static ArrayList<Integer> al = new ArrayList<Integer>();

	public static int test() {
		try {
			al.add(0, 1);
			return al.get(0);
		} finally {
			al.set(0, 2);
		}
	}

	public static void main(String[] args) {
		System.out.println(test());
	}
}
