package by.stormnet.levkovets.utils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String s1 ="+375(29)805 66-37";
        String s2 = s1.replaceAll("\\D+", "");
        int num = s2.length();
        String s3 = s2.substring(num-9, num);
        System.out.println(s3);

    }
}
