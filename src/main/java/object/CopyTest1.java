package com.watone.monitor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author baltan
 * @date 2024/11/27 09:09
 */
public class CopyTest1 {
    public static void main(String[] args) {
        X1 x1 = new X1()
                .setI(6)
                .setY(new Y1()
                        .setN(1)
                        .setZs(Arrays.asList(
                                new Z1().setM(new int[]{0, 2, 4}),
                                new Z1().setM(new int[]{1, 3, 5}))));
        /**
         * org.springframework.beans.BeanUtils
         */
        X2 x2 = new X2();
        BeanUtils.copyProperties(x1, x2);
        X1 x3 = new X1();
        BeanUtils.copyProperties(x1, x3);
        /**
         * {"i":6,"y":{"n":1,"zs":[{"m":[0,2,4]},{"m":[1,3,5]}]}}
         */
        System.out.println(JSON.toJSONString(x1));
        /**
         * {"i":6}
         */
        System.out.println(JSON.toJSONString(x2));
        /**
         * {"i":6,"y":{"n":1,"zs":[{"m":[0,2,4]},{"m":[1,3,5]}]}}
         */
        System.out.println(JSON.toJSONString(x3));
        /**
         * cn.hutool.core.bean.BeanUtil
         */
        X2 x4 = new X2();
        BeanUtil.copyProperties(x1, x4);
        X2 x5 = BeanUtil.toBean(x1, X2.class);
        /**
         * {"i":6,"y":{"n":1,"zs":[{"m":[0,2,4]},{"m":[1,3,5]}]}}
         */
        System.out.println(JSON.toJSONString(x4));
        /**
         * {"i":6,"y":{"n":1,"zs":[{"m":[0,2,4]},{"m":[1,3,5]}]}}
         */
        System.out.println(JSON.toJSONString(x5));
    }

    @Data
    @Accessors(chain = true)
    public static class X1 {
        private int i;
        private Y1 y;
    }

    @Data
    @Accessors(chain = true)
    public static class Y1 {
        private int n;
        private List<Z1> zs;
    }

    @Data
    @Accessors(chain = true)
    public static class Z1 {
        private int[] m;
    }

    @Data
    @Accessors(chain = true)
    public static class X2 {
        private int i;
        private Y2 y;
    }

    @Data
    @Accessors(chain = true)
    public static class Y2 {
        private int n;
        private List<Z2> zs;
    }

    @Data
    @Accessors(chain = true)
    public static class Z2 {
        private int[] m;
    }
}
