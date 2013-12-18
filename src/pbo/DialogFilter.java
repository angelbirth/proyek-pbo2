package pbo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class DialogFilter extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -746489930968730372L;

	private javax.swing.JFrame parent;

	private final JPanel contentPanel = new JPanel();
	private String query;
	private JTextField txtID;
	private JTextField txtAge;
	private JButton okButton;
	private JButton btnClear;
	private JButton cancelButton;
	private JDateChooser dateChooser;
	private JTextField txtName;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnBirthday;
	private JRadioButton rdbtnAge;

	/**
	 * Create the dialog.
	 */
	public DialogFilter(JFrame parent) {
		super(parent, false);
		this.parent = parent;
		setTitle("Apply Filter");
		setBounds(100, 100, 314, 219);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0,
				Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			rdbtnId = new JRadioButton("ID");
			rdbtnId.addActionListener(radioClick);
			rdbtnId.setSelected(true);
			GridBagConstraints gbc_rdbtnId = new GridBagConstraints();
			gbc_rdbtnId.anchor = GridBagConstraints.WEST;
			gbc_rdbtnId.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnId.gridx = 0;
			gbc_rdbtnId.gridy = 0;
			contentPanel.add(rdbtnId, gbc_rdbtnId);
		}
		{
			txtID = new JTextField();
			txtID.setToolTipText("Use '%' or '_' for wildcard characters");
			GridBagConstraints gbc_txtID = new GridBagConstraints();
			gbc_txtID.insets = new Insets(0, 0, 5, 0);
			gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtID.gridx = 1;
			gbc_txtID.gridy = 0;
			contentPanel.add(txtID, gbc_txtID);
		}
		{
			rdbtnName = new JRadioButton("Name");
			rdbtnName.addActionListener(radioClick);
			GridBagConstraints gbc_rdbtnName = new GridBagConstraints();
			gbc_rdbtnName.anchor = GridBagConstraints.WEST;
			gbc_rdbtnName.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnName.gridx = 0;
			gbc_rdbtnName.gridy = 1;
			contentPanel.add(rdbtnName, gbc_rdbtnName);
		}
		{
			txtName = new JTextField();
			txtName.setEnabled(false);
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.insets = new Insets(0, 0, 5, 0);
			gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtName.gridx = 1;
			gbc_txtName.gridy = 1;
			contentPanel.add(txtName, gbc_txtName);
		}
		{
			rdbtnBirthday = new JRadioButton("Birthday");
			rdbtnBirthday.addActionListener(radioClick);
			GridBagConstraints gbc_rdbtnBirthday = new GridBagConstraints();
			gbc_rdbtnBirthday.anchor = GridBagConstraints.WEST;
			gbc_rdbtnBirthday.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnBirthday.gridx = 0;
			gbc_rdbtnBirthday.gridy = 2;
			contentPanel.add(rdbtnBirthday, gbc_rdbtnBirthday);
		}
		{
			dateChooser = new JDateChooser();
			dateChooser.setEnabled(false);
			dateChooser.getCalendarButton().setEnabled(false);
			dateChooser.setDateFormatString("yyyy-MM-dd");
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
			gbc_dateChooser.fill = GridBagConstraints.BOTH;
			gbc_dateChooser.gridx = 1;
			gbc_dateChooser.gridy = 2;
			contentPanel.add(dateChooser, gbc_dateChooser);
		}
		{
			rdbtnAge = new JRadioButton("Age");
			rdbtnAge.addActionListener(radioClick);
			GridBagConstraints gbc_rdbtnAge = new GridBagConstraints();
			gbc_rdbtnAge.anchor = GridBagConstraints.WEST;
			gbc_rdbtnAge.insets = new Insets(0, 0, 0, 5);
			gbc_rdbtnAge.gridx = 0;
			gbc_rdbtnAge.gridy = 3;
			contentPanel.add(rdbtnAge, gbc_rdbtnAge);
		}
		{
			txtAge = new JTextField();
			txtAge.setEnabled(false);
			GridBagConstraints gbc_txtAge = new GridBagConstraints();
			gbc_txtAge.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAge.gridx = 1;
			gbc_txtAge.gridy = 3;
			contentPanel.add(txtAge, gbc_txtAge);
		}
		{
			rdbtnId.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnName.setSelected(false);
						rdbtnBirthday.setSelected(false);
						rdbtnAge.setSelected(false);
						txtID.setEnabled(true);
						txtName.setEnabled(false);
						dateChooser.setEnabled(false);
						txtAge.setEnabled(false);
					}
				}
			});
			rdbtnName.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnId.setSelected(false);
						rdbtnBirthday.setSelected(false);
						rdbtnAge.setSelected(false);
						txtName.setEnabled(true);
						txtID.setEnabled(false);
						dateChooser.setEnabled(false);
						txtAge.setEnabled(false);
					}
				}
			});
			rdbtnBirthday.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnName.setSelected(false);
						rdbtnId.setSelected(false);
						rdbtnAge.setSelected(false);
						txtName.setEnabled(false);
						txtID.setEnabled(false);
						dateChooser.setEnabled(true);
						txtAge.setEnabled(false);
					}
				}
			});
			rdbtnAge.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnName.setSelected(false);
						rdbtnBirthday.setSelected(false);
						rdbtnId.setSelected(false);
						txtName.setEnabled(false);
						txtID.setEnabled(false);
						dateChooser.setEnabled(false);
						txtAge.setEnabled(true);
					}
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Apply Filter");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						if (rdbtnId.isSelected()) {
							if (!txtID.getText().matches("[\\d%_]{1,11}")) {
								JOptionPane.showMessageDialog(
										DialogFilter.this,
										"Invalid input for field ID",
										"Invalid Input",
										JOptionPane.ERROR_MESSAGE);
								txtID.requestFocusInWindow();
								txtID.selectAll();
								return;
							}
							query = "select * from phonebook where id like '"
									+ txtID.getText() + "';";
						} else if (rdbtnName.isSelected()) {
							if (!txtName.getText().matches("[\\w,. *?]+")) {
								JOptionPane.showMessageDialog(
										DialogFilter.this,
										"Invalid input for field Name",
										"Invalid Input",
										JOptionPane.ERROR_MESSAGE);
								txtName.requestFocusInWindow();
								txtName.selectAll();
								return;
							}
							query = "select * from phonebook where name like '%"
									+ txtName.getText().toUpperCase()
											.replace('*', '%')
											.replace('?', '_') + "%';";
						} else if (rdbtnBirthday.isSelected()) {
							if (dateChooser.getDate() == null) {
								JOptionPane.showMessageDialog(
										DialogFilter.this,
										"Invalid input for field Birthday",
										"Invalid Input",
										JOptionPane.ERROR_MESSAGE);
								dateChooser.requestFocusInWindow();
								return;
							}

							Date date = dateChooser.getDate();
							String d = String.valueOf(date.getDate());
							String m = String.valueOf(date.getMonth() + 1);
							String y = String.valueOf(date.getYear() + 1900);
							query = String
									.format("select * from phonebook where birthday='%s-%s-%s'",
											y, m, d);
							System.err.println(String.format("%s-%s-%s", y, m,
									d));
						} else {
							if (!txtAge.getText().matches("\\d{1,2}")) {
								JOptionPane.showMessageDialog(
										DialogFilter.this,
										"Invalid input for field Age",
										"Invalid Input",
										JOptionPane.ERROR_MESSAGE);
								txtAge.requestFocusInWindow();
								txtAge.selectAll();
								return;
							}
							query = "select * from phonebook where year(sysdate())- year(birthday)="
									+ txtAge.getText() + ";";
						}

						try {
							((MainForm) DialogFilter.this.parent)
									.loadData(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				okButton.setActionCommand("OK");
				// okButton.addActionListener(okListener);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnClear = new JButton("Clear Filter");
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							((MainForm) DialogFilter.this.parent)
									.loadData("select * from phonebook;");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(btnClear);
			}
			{
				cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DialogFilter.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.getRootPane().registerKeyboardAction(escapePressed,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		// query = "select * from phonebook where";
	}
	private final ActionListener radioClick = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			((JRadioButton) e.getSource()).setSelected(true);
		}
	};
	private final ActionListener escapePressed = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			DialogFilter.this.dispose();
			// else if (e.getKeyCode() == KeyEvent.VK_ENTER)
			// btnOK_Click();;
		}
	};
}
