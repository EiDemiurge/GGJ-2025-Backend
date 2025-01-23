import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Runner implements Runnable {

    static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5423);
    private static boolean finished;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Java started!");
        new Thread(new Runner()).start();
        while (true) {
            Integer take = queue.take();
            if (take >= 25) {
                System.out.println("Limit reached: " + take);
                break;
            } else {
                System.err.println("Sending back: " + take);
            }
        }
        System.out.println("Java quit!");
        finished = true;
        System.exit(0);
    }

    @Override
    public void run() {

        while (!finished) {
            String s = scanner.next();
            try {
                Integer i = Integer.valueOf(s);
                try {
                    System.out.println("Got: " + i);
                    queue.put(++i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
