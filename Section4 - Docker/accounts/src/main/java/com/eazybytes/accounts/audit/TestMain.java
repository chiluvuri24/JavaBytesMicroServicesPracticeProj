package com.eazybytes.accounts.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestMain {

    public static void main(String[] args) {

        String str = "abcdefghijklmnopqrstuvwxyz";

        IntStream strStr = str.chars();

        Stream<String> str33 =  strStr.mapToObj(c->String.valueOf( (char)c));

        String revStr = str33.reduce((a,b)->b+a).orElse("");

        System.out.println(revStr);


        //IntStream intStream = "rama".chars();

        Stream<String> StreamString = "rama".chars().mapToObj(c->String.valueOf((char)c));

        String revStr1 = StreamString.reduce((a,b)->b+a).orElse("");

        System.out.println(revStr1);


        List<Integer> al = new ArrayList<>();

        for(int i=1;i<=100;i++){
            al.add(i);
        }

        System.out.println(al.stream().reduce((a,b)->a+b));





    }

}
