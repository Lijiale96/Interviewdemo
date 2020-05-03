package JVMGC;

/**
 * Java可以作为GC Roots的对象
 * - 虚拟机栈（栈的局部变量区，局部变量表）引用的对象
 * - 方法区类静态属性引用对象
 * - 方法区常量引用的对象
 * - 本地方法栈（Native方法）引用的对象:线程中 start方法
 */
public class GCRootsDemo {
    private byte[] byteArray = new byte[100*1024*1024];

    //private static GCRootDemo2 t2;
    //private static final GCRootDemo3 t3 = new GCRootDemo3(8);
    public static void main(String[] args) {
        m1();
    }
    public static void m1(){
        GCRootsDemo t1 = new GCRootsDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }
}
