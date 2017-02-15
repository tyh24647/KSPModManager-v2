package Tasks;

import Utils.Log;

import javax.annotation.Nonnull;

import static Constants.StrConstants.generateTagForName;

/**
 * @author Tyler Hostager
 * @version 2/13/17
 */
public final class AsyncTask {
    private static final String TAG = generateTagForName(AsyncTask.class.getSimpleName());

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
        Log.DEBUG(TAG, "Executing asynchronous task on a new background thread...");
        new Thread(completionHandler).run();
        Log.DEBUG(TAG, "Task completed successfully");
    }
}
