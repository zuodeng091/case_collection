package com.casecollection.backend.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CommonUtils {

    public static List<Integer> getYears() {
        List<Integer> years = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //从2000年开始，到今年
        for(int year = c.get(Calendar.YEAR); year > 2010; year--) {
            years.add(year);
        }
        return years;
    }
    /**
     * 随机获取字符串
     * 
     * @param length
     *            随机字符串长度
     * 
     * @return 随机字符串
     */
    public static String getRandomNumStr(int length) {
        if (length <= 0) {
            return "";
        }
        char[] randomChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        return stringBuffer.toString();
    }

    /**
     * 半角转为全角
     * @param input
     * @return
     */
    public static String toSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);

            }
        }
        return new String(c);
    }

    /**
     * 全角转为半角
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);
        return returnString;
    }

    public static void main(String[] args){
        String as = CommonUtils.toSBC("sdf, ，（）是打发斯蒂芬“”");
        System.out.println(as);
        String us = CommonUtils.toDBC(as);
        System.out.println(us);
    }

}
