package 并发编程.Daemon;


import java.io.IOException;

public class _test extends Thread {

    public _test() {
    }
    /** */
    /**
     * 线程的run方法，它将和其他线程同时运行
     */
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        _test test = new _test();
        // 如果不设置daemon，那么线程将输出100后才结束
//        test.setDaemon(true);
        test.start();
        System.out.println("isDaemon = " + test.isDaemon());
        try {
            sleep(1000); // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
            System.out.println("主线程结束。");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
