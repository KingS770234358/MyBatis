package com.wq.utils;

import java.util.UUID;
import org.junit.Test;

@SuppressWarnings("all") //抑制警告 消除下划线
public class IDutil {
    public static String getUUID(){
        // × replaceAll(".","-") ====> .是正则表达式 把所有字符都转成了-
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    @Test
    public void test_getUUID(){
        System.out.println(IDutil.getUUID());
    }

}
