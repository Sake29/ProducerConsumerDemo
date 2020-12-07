import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        //建立线程池
        ExecutorService es = Executors.newCachedThreadPool();
        //运行生产者
        es.execute(producer1);
        es.execute(producer2);
        es.execute(producer3);
        //运行消费者
        es.execute(consumer1);
        es.execute(consumer2);
        es.execute(consumer3);
        Thread.sleep(10 * 1000);
        //停止生产者
        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3000);
        es.shutdown();
    }

}
