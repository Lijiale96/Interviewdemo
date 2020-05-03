package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MYThread2());
//        FutureTask<Integer> futureTask2 = new FutureTask<>(new MYThread2());
       new Thread(futureTask,"AA").start();
       new Thread(futureTask,"BB").start();

//        int result02=futureTask.get();

        System.out.println(Thread.currentThread().getName()+"*************");

        int result01=100;

//        while(!futureTask.isDone()){
//
//        }
        int result02=futureTask.get();//要求获得callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，值得计算完成

        System.out.println("********result:"+(result01+result02));
    }
}

//class MyThread implements Runnable{
//
//    @Override
//    public void run() {
//
//    }
//}

class MYThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("*********come in Callable");
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return 1024;
    }
}