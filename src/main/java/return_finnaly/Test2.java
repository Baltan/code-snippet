/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package return_finnaly_test;

public class Test2 {
    public static String str = "呵呵";

    public static String test() {
        try {
            return str;
        } finally {
//			str = new String("哈哈");
            str = "哈哈";
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
