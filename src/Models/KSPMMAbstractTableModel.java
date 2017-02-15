package Models;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.AbstractQueue;

/**
 * <p>
 *     Creates an instance of the abstract table model that contains relevant methods
 *     to inherit for the table model for mods, as well as leaves out any unnecessary
 *     methods.
 * </p>
 * <p>
 *     This class is necessary in order for the child classes to implement specific methods
 *     that are not in the predefined {@link AbstractTableModel} in the
 *     {@link javax.swing.table.AbstractTableModel} package.
 * </p>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
abstract class KSPMMAbstractTableModel extends AbstractTableModel {

    //region PRIVATE MUTABLE VARIABLES
    private AbstractQueue<Object[][]> dataQueue; //    <-- In case the user wishes to undo a data change
    private Object[][] data;
    //endregion

    //region CONSTRUCTORS
    /**
     * <p>
     *     Constructs a new instance of an abstract table model with additional accessor and setter methods
     *     to be implemented through inheritance.
     * </p>
     * <p>
     *     This instance will give child classes the ability to access specific, customized methods of this
     *     implementation of {@link AbstractTableModel}, which allows this class to be reused in different
     *     views, thereby removing the need to recreate specific classes that have these methods duplicated.
     * </p>
     *
     * @see KSPMMTableModel for implementation
     */
    KSPMMAbstractTableModel() {
        super();
        generateDataQueue();
    }
    //endregion

    private AbstractQueue<Object[][]> generateDataQueue() {
        AbstractQueue<Object[][]> tmp = null;

        /*
        TODO
         */

        return tmp;
    }

    //region ABSTRACT INTERFACE METHODS TO BE OVERRIDDEN BY CHILD CLASSES
    /**
     * <p>Retrieves the background color of the table</p>
     * @return  The background {@link Color} object assigned to the table
     */
    abstract Color getBackgroundColor();

    /**
     * <p>Retrieves the title of the table</p>
     * <u>
     *     <b>Note:</b>
     *     &nbsp;
     *     The title may be set by the following:
     * </u>
     * <ul>
     *     <li>Child class which extends this class</li>
     *     <li>The default value held by the {@link AbstractTableModel} class</li>
     *     <li>The assigned value assigned to the implemented abstract table model</li>
     * </ul>
     *
     * @return  The title {@link String} object that was assigned
     * @see     KSPMMTableModel serialized implementation
     */
    abstract String getTitle();

    /**
     * <p>Assigns the size of the table instance</p>
     * @param size  The specified size to be assigned
     */
    abstract void setSize(int size);

    /**
     * <p>Wipes out any of the table's data/cells</p>
     * <p>
     *     <b>Note:</b>
     *     &nbsp;
     *     Although this will work for clearing the table, the table data
     *     should be set and retrieved by the model's data being changed/deleted.
     *     This will delete the physical cells and prevent them from being seen, but does
     *     not modify the actual data that is stored for the table.
     * </p>
     *
     * @return True if the table has been cleared successfully
     */
    abstract boolean clear();

    /**
     * <p>Returns the boolean value of whether or not the table object is null/has been initialized.</p>
     * @return  True if the data is null.
     */
    abstract boolean isNull();

    /**
     * <p>Deletes the data assigned to the table</p>
     * <p>
     *     <b>Note:</b>
     *     &nbsp;
     *     Although this deletes the data associated with the table, this does not delete the data
     *     that corresponds to the table data in the {@link KSPMMTableModel} object. This is used
     *     in order to clear out the view data in order to replace it with other data, without the view being
     *     cleared out beforehand
     * </p>
     *
     * @return  True if the data has been deleted successfully.
     */
    abstract boolean deleteTableData();
    //endregion
}
