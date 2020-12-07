import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 模拟消费者
 * 从阻塞队列中取出PCData对象，并进行相应的计算
 *
 * @author WSY
 * @date 2020/12/07
 */
public class Consumer implements Runnable {
    /**
     * 缓冲区
     */
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("CONSUMER:" + "start Consumer id="+Thread.currentThread().getId());
        Random r = new Random();
        try {
            while (true){
                PCData data = queue.take();
                if (null != data){
                    int re = data.getData() * data.getData();//计算平方
                    System.out.println("CONSUMER:" +data + "is taken from queue,calculating " +data.getData()+"*"+data.getData()+"="+re);
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
