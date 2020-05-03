package JVMGC;

public class helloGc {
    public static void main(String[] args) throws Exception{
        System.out.println("********GC");
        byte[]  byteArray = new byte[50*1024*1024];
    Object.class.wait();

        Thread.sleep(Integer.MAX_VALUE);
    }
}
