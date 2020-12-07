import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟生产者
 * 构建PCData对象，并放入阻塞队列中
 *
 * @author WSY
 * @date 2020/12/07
 */
public class Producer implements Runnable {

    /**
     * 运行标志
     */
    private volatile boolean isRunning = true;
    /**
     * 内存缓冲区
     */
    private BlockingQueue<PCData> queue;
    /**
     * 总数，原子操作
     */
    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    private static final int SLEEPTIME = 1000;

    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println("PRODUCER:" + "start producer id=" + Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());//构造任务数据
                System.out.println("PRODUCER:" + data+"is put into queue");
                if ( !queue.offer(data,2, TimeUnit.SECONDS)){ //提交数据到缓冲区中
                    System.out.println("PRODUCER:" + "fail to put data:" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); //中断线程
        }
    }

    public void stop(){
        isRunning = false;
    }
}
