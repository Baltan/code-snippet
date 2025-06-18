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

public class Test {

    public static int count = 0;

    public static int test() {
        try {
            count++;
            return count;
        } finally {
            count++;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
