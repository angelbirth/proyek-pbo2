package pbo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.toedter.calendar.JDateChooser;

public class DialogAdd extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -632760980212410777L;
	private JPanel contentPanel;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtBirthplace;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtJob;
	private JTextField txtCompany;
	private JTextArea txtAddress;
	private final JFrame parent;
	private JButton okButton;
	private JButton cancelButton;
	private JRadioButton rdoMale;
	private JRadioButton rdoFemale;
	private JDateChooser dateChooser;

	/**
	 * Create the dialog.
	 */
	public DialogAdd(JFrame parent) {
		super(parent, "Add Data", true);
		this.parent = parent;
		setResizable(false);
		setBounds(100, 100, 434, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblId = new JLabel("ID");
			GridBagConstraints gbc_lblId = new GridBagConstraints();
			gbc_lblId.anchor = GridBagConstraints.WEST;
			gbc_lblId.insets = new Insets(0, 0, 5, 5);
			gbc_lblId.gridx = 0;
			gbc_lblId.gridy = 0;
			contentPanel.add(lblId, gbc_lblId);
		}
		{
			txtID = new JTextField();
			// txtID.addKeyListener(escapePressed);
			GridBagConstraints gbc_txtID = new GridBagConstraints();
			gbc_txtID.insets = new Insets(0, 0, 5, 5);
			gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtID.gridx = 1;
			gbc_txtID.gridy = 0;
			contentPanel.add(txtID, gbc_txtID);
			// txtID.setColumns(10);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.VERTICAL;
			gbc_separator.gridheight = 6;
			gbc_separator.insets = new Insets(0, 0, 0, 5);
			gbc_separator.gridx = 2;
			gbc_separator.gridy = 0;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblPhoneNo = new JLabel("Phone No");
			GridBagConstraints gbc_lblPhoneNo = new GridBagConstraints();
			gbc_lblPhoneNo.anchor = GridBagConstraints.WEST;
			gbc_lblPhoneNo.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhoneNo.gridx = 3;
			gbc_lblPhoneNo.gridy = 0;
			contentPanel.add(lblPhoneNo, gbc_lblPhoneNo);
		}
		{
			txtPhone = new JTextField();
			// txtPhone.addKeyListener(escapePressed);
			GridBagConstraints gbc_txtPhone = new GridBagConstraints();
			gbc_txtPhone.insets = new Insets(0, 0, 5, 0);
			gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPhone.gridx = 4;
			gbc_txtPhone.gridy = 0;
			contentPanel.add(txtPhone, gbc_txtPhone);
			txtPhone.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.WEST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			txtName = new JTextField();
			// txtName.addKeyListener(escapePressed);
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.insets = new Insets(0, 0, 5, 5);
			gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtName.gridx = 1;
			gbc_txtName.gridy = 1;
			contentPanel.add(txtName, gbc_txtName);
			txtName.setColumns(10);
		}
		{
			JLabel lblEmailAddress = new JLabel("E-Mail Address");
			GridBagConstraints gbc_lblEmailAddress = new GridBagConstraints();
			gbc_lblEmailAddress.anchor = GridBagConstraints.WEST;
			gbc_lblEmailAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmailAddress.gridx = 3;
			gbc_lblEmailAddress.gridy = 1;
			contentPanel.add(lblEmailAddress, gbc_lblEmailAddress);
		}
		{
			txtEmail = new JTextField();
			// txtEmail.addKeyListener(escapePressed);
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridx = 4;
			gbc_txtEmail.gridy = 1;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JLabel lblSex = new JLabel("Sex");
			GridBagConstraints gbc_lblSex = new GridBagConstraints();
			gbc_lblSex.anchor = GridBagConstraints.WEST;
			gbc_lblSex.insets = new Insets(0, 0, 5, 5);
			gbc_lblSex.gridx = 0;
			gbc_lblSex.gridy = 2;
			contentPanel.add(lblSex, gbc_lblSex);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.NORTHWEST;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			{
				rdoMale = new JRadioButton("Male");
				rdoMale.setSelected(true);
				rdoMale.addActionListener(radioClick);
				panel.add(rdoMale);
			}
			{
				rdoFemale = new JRadioButton("Female");
				rdoFemale.addActionListener(radioClick);
				panel.add(rdoFemale);
			}
			rdoFemale.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED)
						rdoMale.setSelected(false);
				}
			});
			rdoMale.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED)
						rdoFemale.setSelected(false);
				}
			});
		}
		{
			JLabel lblJob = new JLabel("Job");
			GridBagConstraints gbc_lblJob = new GridBagConstraints();
			gbc_lblJob.anchor = GridBagConstraints.WEST;
			gbc_lblJob.insets = new Insets(0, 0, 5, 5);
			gbc_lblJob.gridx = 3;
			gbc_lblJob.gridy = 2;
			contentPanel.add(lblJob, gbc_lblJob);
		}
		{
			txtJob = new JTextField();
			GridBagConstraints gbc_txtJob = new GridBagConstraints();
			gbc_txtJob.insets = new Insets(0, 0, 5, 0);
			gbc_txtJob.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtJob.gridx = 4;
			gbc_txtJob.gridy = 2;
			contentPanel.add(txtJob, gbc_txtJob);
			txtJob.setColumns(10);
		}
		{
			JLabel lblBirthplace = new JLabel("Birthplace");
			GridBagConstraints gbc_lblBirthplace = new GridBagConstraints();
			gbc_lblBirthplace.anchor = GridBagConstraints.WEST;
			gbc_lblBirthplace.insets = new Insets(0, 0, 5, 5);
			gbc_lblBirthplace.gridx = 0;
			gbc_lblBirthplace.gridy = 3;
			contentPanel.add(lblBirthplace, gbc_lblBirthplace);
		}
		{
			txtBirthplace = new JTextField();
			GridBagConstraints gbc_txtBirthplace = new GridBagConstraints();
			gbc_txtBirthplace.insets = new Insets(0, 0, 5, 5);
			gbc_txtBirthplace.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtBirthplace.gridx = 1;
			gbc_txtBirthplace.gridy = 3;
			contentPanel.add(txtBirthplace, gbc_txtBirthplace);
			txtBirthplace.setColumns(10);
		}
		{
			JLabel lblCompany = new JLabel("Company");
			GridBagConstraints gbc_lblCompany = new GridBagConstraints();
			gbc_lblCompany.anchor = GridBagConstraints.WEST;
			gbc_lblCompany.insets = new Insets(0, 0, 5, 5);
			gbc_lblCompany.gridx = 3;
			gbc_lblCompany.gridy = 3;
			contentPanel.add(lblCompany, gbc_lblCompany);
		}
		{
			txtCompany = new JTextField();
			GridBagConstraints gbc_txtCompany = new GridBagConstraints();
			gbc_txtCompany.insets = new Insets(0, 0, 5, 0);
			gbc_txtCompany.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCompany.gridx = 4;
			gbc_txtCompany.gridy = 3;
			contentPanel.add(txtCompany, gbc_txtCompany);
			txtCompany.setColumns(10);
		}
		{
			JLabel lblBirthday = new JLabel("Birthday");
			GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
			gbc_lblBirthday.anchor = GridBagConstraints.WEST;
			gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
			gbc_lblBirthday.gridx = 0;
			gbc_lblBirthday.gridy = 4;
			contentPanel.add(lblBirthday, gbc_lblBirthday);
		}
		{
			dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("dd-MM-yy");
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
			gbc_dateChooser.gridx = 1;
			gbc_dateChooser.gridy = 4;
			contentPanel.add(dateChooser, gbc_dateChooser);
		}
		{
			JLabel lblAddress = new JLabel("Address");
			GridBagConstraints gbc_lblAddress = new GridBagConstraints();
			gbc_lblAddress.anchor = GridBagConstraints.WEST;
			gbc_lblAddress.insets = new Insets(0, 0, 0, 5);
			gbc_lblAddress.gridx = 0;
			gbc_lblAddress.gridy = 5;
			contentPanel.add(lblAddress, gbc_lblAddress);
		}
		{
			txtAddress = new JTextArea();
			txtAddress.setLineWrap(true);
			GridBagConstraints gbc_txtAddress = new GridBagConstraints();
			gbc_txtAddress.insets = new Insets(0, 0, 0, 5);
			gbc_txtAddress.fill = GridBagConstraints.BOTH;
			gbc_txtAddress.gridx = 1;
			gbc_txtAddress.gridy = 5;
			contentPanel.add(txtAddress, gbc_txtAddress);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnOK_Click();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DialogAdd.this.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{
				txtID, txtName, rdoMale, rdoFemale, txtBirthplace, dateChooser,
				dateChooser.getCalendarButton(), txtAddress, txtPhone,
				txtEmail, txtJob, txtCompany, okButton, cancelButton}));
		this.getRootPane().registerKeyboardAction(escapePressed,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	private void btnOK_Click() {
		if (!isValid(0, txtID.getText())) {
			JOptionPane.showMessageDialog(this, "Invalid input for field: ID",
					"Invalid Input", JOptionPane.ERROR_MESSAGE);
			txtID.requestFocusInWindow();
			txtID.selectAll();
			return;
		}
		if (!isValid(1, txtName.getText())) {
			JOptionPane.showMessageDialog(this,
					"Invalid input for field: Name", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);
			txtName.requestFocusInWindow();
			txtName.selectAll();
			return;
		}
		if (!isValid(6, txtPhone.getText())) {
			JOptionPane.showMessageDialog(this,
					"Invalid input for field: Phone Number", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);
			txtPhone.requestFocusInWindow();
			txtPhone.selectAll();
			return;
		}
		if (!isValid(7, txtEmail.getText())) {
			JOptionPane.showMessageDialog(this,
					"Invalid input for field: E-Mail Address", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);
			txtEmail.requestFocusInWindow();
			txtEmail.selectAll();
			return;
		}
		try {
			addData();
			DialogAdd.this.dispose();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(DialogAdd.this,
					"Error when trying to add data\n: " + e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean isValid(int col, String data) {
		String pattern;
		switch (col) {
			case 0 :
				pattern = "\\d{1,11}";
				break;
			case 1 :
			case 3 :
				pattern = "[A-Za-z,. ]{1,45}";
				break;
			case 5 :
				pattern = "[A-Za-z0-9,./ \\-]{1,60}";
				break;
			case 6 :
				pattern = "[\\d\\-]{0,12}";
				break;
			case 7 :
				pattern = "[\\da-z._]*@?[\\da-z._]*";
				break;
			case 9 :
			case 8 :
				pattern = "[A-Za-z,. ]{0,45}";
				break;
			default :
				return true;
		}
		if (data.matches(pattern))
			return true;
		return false;
	}

	private void addData() throws SQLException {
		if (!((MainForm) parent).conn.isClosed()) {
			java.sql.PreparedStatement s = ((MainForm) parent).conn
					.prepareStatement("insert into phonebook values(?,?,?,?,?,?,?,?,?,?)");
			s.setObject(1, txtID.getText());
			s.setObject(2, txtName.getText().toUpperCase());
			s.setObject(3, rdoMale.isSelected() ? "M" : "F");
			s.setObject(4, txtBirthplace.getText());
			s.setObject(5, dateChooser.getDate());
			s.setObject(6, txtAddress.getText());
			s.setObject(7, txtPhone.getText());
			s.setObject(8, txtEmail.getText());
			s.setObject(9, txtJob.getText());
			s.setObject(10, txtCompany.getText());
			s.execute();
			s.close();
			((MainForm) parent).loadData("select * from phonebook;");
		}
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
				DialogAdd.this.dispose();
			// else if (e.getKeyCode() == KeyEvent.VK_ENTER)
			// btnOK_Click();;
		}
	};
}
