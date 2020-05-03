package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1 验证volatile的可见性
 *   1.1 假如 int number = 0 ；number变量之前根本没有添加volatile关键字修饰，没有可见性
 *   1.2 添加了volatile，可以解决可见性问题
 *
 * 2 验证volatile不保证原子性
 *   2.1 原子性值得是什么意思？
 *       不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者会被分割
 *       要么同时成功，要么同时失败
 *
 *   2.2 是否可以保证原子性的
 *
 *   2.3 why？
 *   时间太快，在putfield时， 后面的线程写覆盖前面的线程
 *
 *   2.4 如何解决原子性
 *   * 加sync
 *   * 使用我们的juc下的AtomicInteger----> CAS
 */
public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkByVolatile();
        MyData myData = new MyData();
        for (int i=1;i<=20;i++)
        {
            new Thread(()->{
                  for (int j=1;j<1000;j++){
                      myData.addPlusPlus();
                      myData.addMyAtomic();
                  }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int type,finally number value: "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type,finally number value: "+myData.atomicInteger);

    }


    //volatile 可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            //暂停一会线程
            try{
                TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e){e.printStackTrace();}
            myData.addT060();
            System.out.println(Thread.currentThread().getName()+"\t updated number value: "+myData.number);
        },"AAA").start();

        while(myData.number==0){

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get number value"+ myData.number);
    }
}

class MyData{//MyData.java===》 MyData.class ===》JVM字节码
    volatile int number=0;
    public void addT060(){
        this.number=60;
    }
    //number前面加了volatile，不保证原子性
    public  void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void  addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

