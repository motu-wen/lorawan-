package com.example.lorawan.until;

public class Hex {
    public static String  HexToString(String s){
        byte[] baKeyword = new byte[s.length()/2];
        for(int i = 0; i < baKeyword.length; i++){
            try{
                baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try{
            s = new String(baKeyword, "utf-8");//UTF-16le:Not

        }catch (Exception e){
            e.printStackTrace();
        }
    return s;
    }

}
