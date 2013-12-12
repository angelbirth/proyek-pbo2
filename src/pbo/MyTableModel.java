package pbo;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private String[] columnHeaders = { "ID", "Name", "Birthplace", "Birthday",
            "Address", "Phone Number", "E-mail Address", "Job", "Company" };
    private String[] record;
    private String[][] data;

    public MyTableModel() {
        for (int i = 0; i < columnHeaders.length; i++) {

        }
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnHeaders.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        super.setValueAt(aValue, rowIndex, columnIndex);
    }
}
