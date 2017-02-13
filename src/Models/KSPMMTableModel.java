package Models;

import Constants.Defaults;

import java.awt.*;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMMTableModel extends KSPMMAbstractTableModel {
    private KSPModManager modManagerModel;
    private Object[][] data;


    public KSPMMTableModel(KSPModManager model) {
        setModManagerModel(model);
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void setData(Object[][] data) {
        this.data = data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }

    public Object[][] getData() {
        return data == null && modManagerModel.getData() == null ? Defaults.EMPTY_DATA_TABLE : data;
    }

    private KSPModManager generateMMModel() {
        return setModManagerModel(new KSPModManager());
    }

    private KSPModManager setModManagerModel(KSPModManager model) {
        return this.modManagerModel = model == null ? generateMMModel() : model;
    }

    public KSPModManager getModManagerModel() {
        return modManagerModel == null ? generateMMModel() : modManagerModel;
    }

    @Override
    Color getBackgroundColor() {
        return null;
    }

    @Override
    String getTitle() {
        return null;
    }

    @Override
    void setSize(int size) {

    }

    @Override
    boolean clear() {
        return false;
    }

    @Override
    boolean deleteTableData() {
        return false;
    }

    @Override
    boolean isNull() {
        return false;
    }
}
