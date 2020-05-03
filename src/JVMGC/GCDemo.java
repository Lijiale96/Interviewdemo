package JVMGC;

import sun.misc.GC;

import java.util.Random;

public class GCDemo {
    public static void main(String[] args) {

        try{
            String str="lijialke";
        while(true){
            str+=str+new Random().nextInt(777777777)+new Random().nextInt(888888);
            str.intern();
        }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
