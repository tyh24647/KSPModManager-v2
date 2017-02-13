package Objects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class TimeStamp {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_hh:mm:ss_a_zzz");
    public static String currentDateTime() {
        return dateFormat.format(new Date());
    }
}
