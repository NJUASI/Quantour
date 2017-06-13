package com.edu.nju.asi.task;

/**
 * Created by cuihua on 2017/6/9.
 */
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws Exception {

        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        //提交5个任务
        for (int i = 0; i < 5; i++) {
            //创建任务
            Task task = new Task();
            //包装Callable任务
            FutureTask<String> aa = new FutureTask<String>(task);


//            MyFutureTask mTask = new MyFutureTask(task);
            //提交任务
//            executor.submit(mTask);
            executor.submit(aa);
        }

        //关闭线程池
        executor.shutdown();

        System.out.println("-----------------------主线程运行到此------------------------");
    }

}

class MyFutureTask extends FutureTask<String> {

    /**
     * 传入要包装的任务
     *
     * @param callable
     *            要包装的任务
     */
    public MyFutureTask(Callable<String> callable) {
        super(callable);
    }

    /**
     * 任务完成后自动执行
     */
    @Override
    protected void done() {
        try {
            // 利用get方法获取结果
            System.out.println(get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Task implements Callable<String> {

    static int anInt = 3000;

    @Override
    public String call() throws Exception {
        System.out.println(anInt);
//        Thread.sleep(anInt);
        anInt+=3000;
        for (long i = 0; i < 100; i++){
            System.out.println(i);
        }
        return "result string";
    }

}
