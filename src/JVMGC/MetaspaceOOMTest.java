package JVMGC;

public class MetaspaceOOMTest {
    static class OOMTest{

    }
    public static void main(String[] args) {
        int i=0;//模拟计数多少次以后发生异常

        try{
            while(true){
                i++;
//                Enhancer enhancer = new Enhancer();

            }
        }catch (Throwable e){
            System.out.println("********多少次后发生了异常："+i);
            e.printStackTrace();
        }
    }
}
