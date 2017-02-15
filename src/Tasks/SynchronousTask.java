package Tasks;

import javax.annotation.Nonnull;

/**
 * @author Tyler Hostager
 * @version 2/13/17
 */
public class SynchronousTask {
    public static void execute(@Nonnull Runnable completionHandler) {
        completionHandler.run();
    }
}
