package Models;

import Constants.Defaults;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPModManager {

    private Object[][] data;

    /**
     * Default constructor
     */
    public KSPModManager() {

    }


    public void setData(Object[][] data) {
        this.data = data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }


    public Object[][] getData() {
        return data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }
}
