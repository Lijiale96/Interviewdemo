package thread;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

        /**
         * linux  ps -eflgrep xxxx  ls -l
         * window 下的java运行程序 也有类的似 jps的查看进程命令，但是目前我们需要查看的是java
         *      jps = java ps    jps -l
         *      jstack 进程号
         */
    }
}


class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 直接持有："+lockA+"\t 尝试获得："+lockB);

            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {

            }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 直接持有："+lockB+"\t 尝试获得："+lockA);
            }
        }
    }
}