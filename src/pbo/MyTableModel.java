package pbo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private String[] columnHeaders = {"ID", "Name", "Sex", "Birthplace",
			"Birthday", "Address", "Phone Number", "E-mail Address", "Job",
			"Company"};
	private ArrayList<Entry> data;

	public MyTableModel(ArrayList<Entry> data) {
		this.data = data;
	}

	public MyTableModel() {
		data = new ArrayList<>();
	}
	public void addData(Entry data) {
		this.data.add(data);
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		return columnHeaders[column];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnHeaders.length;
	}

	void setData(ArrayList<Entry> data) {
		this.data = data;
	}

	public ArrayList<Entry> getData() {
		return data;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Entry e = data.get(rowIndex);
		switch (columnIndex) {
			case 0 :
				return e.getId();
			case 1 :
				return e.getName();
			case 2 :
				return e.getSex();
			case 3 :
				return e.getBirthplace();
			case 4 :
				return e.getBirthday();
			case 5 :
				return e.getAddress();
			case 6 :
				return e.getPhoneNumber();
			case 7 :
				return e.getEmail();
			case 8 :
				return e.getJob();
			case 9 :
				return e.getCompany();
			default :
				throw new IllegalArgumentException(
						new ArrayIndexOutOfBoundsException());
		}
	}

	// @Override
	// public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	//
	// super.setValueAt(aValue, rowIndex, columnIndex);
	// }
}
