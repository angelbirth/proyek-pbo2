package pbo;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;

import javax.swing.JSeparator;

public class MainForm extends JFrame {
	private static final String LOAD_ALL="select * from phonebook;";
ButtonGroup grp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	com.mysql.jdbc.Connection conn;
	private JPanel contentPane;
	JTable dataTable;
	private JMenuBar menuBar;
	private JMenuItem mntmAddData, mntmDeleteSelectedRow, mntmEditSelectedRow,
			mntmRefresh;
	private final ActionListener mnuAdd_Click = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			addData();
		}
	};
	private JMenuItem mnuAddData;
	private final ActionListener mnuDelete_Click = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int res = JOptionPane.showConfirmDialog(MainForm.this,
					"Are you sure you want to delete selected row?"
							+ "\nNote that this operation cannot be undone.",
					"Confirm Delete", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (res == JOptionPane.YES_OPTION) {
				try {
					com.mysql.jdbc.Statement s = (com.mysql.jdbc.Statement) conn
							.createStatement();
					s.execute("delete from phonebook where id="
							+ dataTable.getValueAt(dataTable.getSelectedRow(),
									0));
					loadData(LOAD_ALL);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(MainForm.this,
							"Error while deleting row:\n" + e1.getMessage(),
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	};
	private final ActionListener mnuEdit_Click = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			editData();
		}
	};
	private JMenu mnuFile;

	private JMenuItem mnuRefresh, mnuDelete, mntmExit, mnuEdit;
	private final ActionListener mnuRefresh_Click = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				loadData(LOAD_ALL);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	};

	MyTableModel model;
	private JPopupMenu popupMenu;
	// private String[] columnHeaders = { "ID", "Name", "Birthplace",
	// "Birthday",
	// "Address", "Phone Number", "E-mail Address", "Job", "Company" };
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public MainForm() {
		setTitle("Phonebook");
		// supaya ukuran tabel mengikuti ukuran Frame
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				scrollPane.setSize(MainForm.this.getWidth() - 28,
						MainForm.this.getHeight() - 70);
			}
		});
		try { // memanggil driver mysql dan membuat koneksi
			Class.forName("com.mysql.jdbc.Driver");
			connect();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(this,
					"Error while connecting to database", "SQL Error",
					JOptionPane.ERROR_MESSAGE);
		}

		model = new MyTableModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 461);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnuFile = new JMenu("File");
		menuBar.add(mnuFile);

		mntmAddData = new JMenuItem("Add Data...");
		mntmAddData.addActionListener(mnuAdd_Click);
		mnuFile.add(mntmAddData);

		mntmRefresh = new JMenuItem("Refresh");
		mnuFile.add(mntmRefresh);

		JSeparator separator = new JSeparator();
		mnuFile.add(separator);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnuFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mntmEditSelectedRow = new JMenuItem("Edit selected row...");
		mntmEditSelectedRow.setEnabled(false);
		mntmEditSelectedRow.addActionListener(mnuEdit_Click);
		mnEdit.add(mntmEditSelectedRow);

		mntmDeleteSelectedRow = new JMenuItem("Delete selected row...");
		mntmDeleteSelectedRow.setEnabled(false);
		mntmDeleteSelectedRow.addActionListener(mnuDelete_Click);
		mnEdit.add(mntmDeleteSelectedRow);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 653, 391);
		contentPane.add(scrollPane);

		dataTable = new JTable(model);
		dataTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (dataTable.getSelectedRow() != -1
								&& dataTable.getSelectedRowCount() == 1) {
							mntmDeleteSelectedRow.setEnabled(true);
							mntmEditSelectedRow.setEnabled(true);
						} else {
							mntmDeleteSelectedRow.setEnabled(false);
							mntmEditSelectedRow.setEnabled(false);
						}
					}
				});
		dataTable.setFillsViewportHeight(true);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dataTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger())
					showMenu(e);
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger())
					showMenu(e);
			}

			private void showMenu(MouseEvent e) {
				if (dataTable.getSelectedRow() != -1 && dataTable.getSelectedRowCount()==1) {
					mnuDelete.setEnabled(true);
					mnuEdit.setEnabled(true);
				} else {
					mnuDelete.setEnabled(false);
					mnuEdit.setEnabled(false);
				}
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});

		dataTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dataTable.clearSelection();
				}
			}
		});
		scrollPane.setViewportView(dataTable);

		popupMenu = new JPopupMenu();
		{
			mnuAddData = new JMenuItem("Add new data...");
			mnuAddData.addActionListener(mnuAdd_Click);
			popupMenu.add(mnuAddData);

			mnuEdit = new JMenuItem("Edit selected row...");
			mnuEdit.setEnabled(false);
			mnuEdit.addActionListener(mnuEdit_Click);
			popupMenu.add(mnuEdit);

			mnuDelete = new JMenuItem("Delete selected row...");
			mnuDelete.setEnabled(false);
			mnuDelete.addActionListener(mnuDelete_Click);
			popupMenu.add(mnuDelete);

			mnuRefresh = new JMenuItem("Refresh");
			mnuRefresh.addActionListener(mnuRefresh_Click);
			popupMenu.add(mnuRefresh);
		}
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					loadData(LOAD_ALL);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(
							MainForm.this,
							"Error while connecting to SQL Server:\n"
									+ e1.getMessage(), "SQL Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		grp=new ButtonGroup();
	}
	public void addData() {
		DialogAdd dlg = new DialogAdd(this);
		dlg.setVisible(true);
	}
	private void connect() throws SQLException {
		if (conn == null || conn.isClosed()) {
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/pbo", "pbo", "pbo");
		}
	}
	public void editData() {
		DialogEdit dlg = new DialogEdit(this);
		dlg.setVisible(true);
	}
	public void loadData(String query) throws SQLException {
		if (!conn.isClosed()) {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			ArrayList<Entry> data = new ArrayList<>();
			while (rs.next()) {
				Entry e = new Entry();
				e.setId(rs.getString(1));
				e.setName(rs.getString(2));
				e.setSex(rs.getString(3).charAt(0));
				e.setBirthplace(rs.getString(4));
				e.setBirthday(rs.getDate(5));
				e.setAddress(rs.getString(6));
				e.setPhoneNumber(rs.getString(7));
				e.setEmail(rs.getString(8));
				e.setJob(rs.getString(9));
				e.setCompany(rs.getString(10));
				data.add(e);
			}
			model.setData(data);
			model.fireTableDataChanged();
		} else {
			connect();
			loadData(LOAD_ALL);
		}
	}

}