package JVMGC;

import java.util.HashMap;

public class WeakHashMap {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("==========");
        myWeakHashMap();
    }

    private static void myWeakHashMap() {

        java.util.WeakHashMap<Integer,String> map = new java.util.WeakHashMap<>();
        Integer key = new Integer(2);
        String value="HashMap";

        map.put(key,value);
        System.out.println(map);

        key =null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }

    private static void myHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value="HashMap";

        map.put(key,value);
        System.out.println(map);

        key =null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }
}
