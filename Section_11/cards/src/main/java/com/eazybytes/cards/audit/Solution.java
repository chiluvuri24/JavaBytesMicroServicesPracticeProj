package com.eazybytes.cards.audit;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Solution.compress(new char[]{'a','a','b','b','c','c','c'}));

    }


    public static int compress(char[] chars) {
        String s = "";
        Map<String,Integer> result = new HashMap<>();
        for(char c:chars){
            String val = String.valueOf(c);
            if(result.containsKey(val)){
                Integer count = result.get(val);
                result.put(val,count+1);
            }else{
                result.put(val,1);
            }
        }

        for(Map.Entry<String,Integer> val : result.entrySet()){

            if(val.getValue() == 1){
                s = s+val.getKey();
            }else{
                s = s+val.getKey()+val.getValue();
            }

        }
chars = s.toCharArray();
        System.out.println(chars);

        return result.size();
    }
    }
