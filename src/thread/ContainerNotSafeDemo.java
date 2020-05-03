package thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 集合类不安全的问题
 * ArrayList
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        Lock lock = new ReentrantLock();
        listNotSafe();

    }

    private static void mapNoSafe() {
        //        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i=0;i<=30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }

    private static void setNotSafe() {
        //        Set<String> set = new HashSet<>();
//        Set<String> set =Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i =0 ;i<=30;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
        new HashSet<>();
    }

    private static void listNotSafe() {
               List<String> list= new ArrayList<>();//不安全，并发性提高
//       List<String> list= new Vector<>();//加锁，安全，并发性下降
//       List<String> list = Collections.synchronizedList(new ArrayList<>());

//        List<String> list = new CopyOnWriteArrayList<>();
//        Collection;//接口
//        Collections;//接口的辅助类
       list.add("a");
       list.add("b");
       list.add("c");
       for (String element :list){
           System.out.println(element);
       }

        for(int i=1;i<=30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException

        /**
         * 1 故障现象
         *     java.util.ConcurrentModificationException
         *
         * 2 导致原因
         *    并发争抢修改导致，参考我们的花名册的签名情况
         *    一个人在写入，另外一个同学过来抢夺，导致数据不一致异常，并发修改异常
         *
         * 3 解决方案
         *    3.1  List<String> list= new Vector<>();//加锁，安全，并发性下降
         *    3.2  List<String> list = Collections.synchronizedList(new ArrayList<>());
         *    3.3  List<String> list = new CopyOnWriteArrayList<>();
         *
         * 4 优化建议
         */

        /**
         * 写时复制
         * CopyOnWrite容器即写时复制的容器。添加时，先copy，复制一个newElements，然后往里面添加元素，之后，指向新的容器setArray(newElements)。好处：并发的读，不需要加锁
         * 因为当前容器不会添加任何元素
         * 读写分离思想，读和写不同的容器
         * public boolean add(E e) {
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         try {
         *             Object[] elements = getArray();
         *             int len = elements.length;
         *             Object[] newElements = Arrays.copyOf(elements, len + 1);
         *             newElements[len] = e;
         *             setArray(newElements);
         *             return true;
         *         } finally {
         *             lock.unlock();
         *         }
         *     }
         */}
}
