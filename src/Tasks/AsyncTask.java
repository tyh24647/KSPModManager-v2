package Tasks;

import javax.annotation.Nonnull;

/**
 * @author Tyler Hostager
 * @version 2/13/17
 */
public class AsyncTask {

    /*
    TODO: Consider having async await methods

    * EXAMPLE: *
        private CountDownLatch doneSignal = new CountDownLatch(1);

        void main() throws InterruptedException{
            asyncDoSomething();
            //wait until doneSignal.countDown() is called
            doneSignal.await();
        }

        void onFinishDoSomething(){
            //do something ...
            //then signal the end of work
             doneSignal.countDown();
        }
     */

    public static void execute(@Nonnull Runnable completionHandler) {
        new Thread(completionHandler).run();
    }
}
