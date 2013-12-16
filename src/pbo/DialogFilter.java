package pbo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JSplitPane;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DialogFilter extends JDialog {
	private javax.swing.JFrame parent;

	private final JPanel contentPanel = new JPanel();
	private String query, id, name, sex, birthday, birthplace, email, address,
			job, company, phone;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogFilter dialog = new DialogFilter(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogFilter(JFrame parent) {

		super(parent, false);
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
			txtID.setEnabled(false);
			GridBagConstraints gbc_txtID = new GridBagConstraints();
			gbc_txtID.insets = new Insets(0, 0, 5, 0);
			gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtID.gridx = 1;
			gbc_txtID.gridy = 0;
			contentPanel.add(txtID, gbc_txtID);
			txtID.setColumns(10);
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
			txtName.setColumns(10);
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
			dateChooser.setDateFormatString("dd-MM-yy");
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
			txtAge.setColumns(10);
		}
		{
			rdbtnId.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnName.setSelected(false);
						rdbtnBirthday.setSelected(false);
						rdbtnAge.setSelected(false);
					}
				}
			});
			rdbtnName.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnId.setSelected(false);
						rdbtnBirthday.setSelected(false);
						rdbtnAge.setSelected(false);
					}
				}
			});
			rdbtnBirthday.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnName.setSelected(false);
						rdbtnId.setSelected(false);
						rdbtnAge.setSelected(false);
					}
				}
			});
			rdbtnAge.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rdbtnName.setSelected(false);
						rdbtnBirthday.setSelected(false);
						rdbtnId.setSelected(false);
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
				okButton.setActionCommand("OK");
				// okButton.addActionListener(okListener);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnClear = new JButton("Clear Filter");
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
		query = "select * from phonebook where";
	}
	private final ActionListener radioClick = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			((JRadioButton) e.getSource()).setSelected(true);
		}
	};
}
