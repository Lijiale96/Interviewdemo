package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone =new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

       Thread t3 = new Thread(phone,"t3");
       Thread t4 = new Thread(phone,"t4");


        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        t3.start();
        t4.start();
    }
}

/**
 * t1	 invoked sendSMS()  在同一个线程在外层方法获取锁的时候
 * t1	 #####invoked sendEmail()  在进入内层方法会自动获取锁
 * t2	 invoked sendSMS()
 * t2	 #####invoked sendEmail()
 *
 */
class Phone  implements Runnable{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t #####invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
       lock.lock();
//       lock.lock();
       try{
           System.out.println(Thread.currentThread().getName()+"\t invoked get()");
           set();
       }finally {
           lock.unlock();
//           lock.unlock();
       }
    }

    public void set() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t #####invoked set()");
        }finally {
            lock.unlock();
        }
    }
}