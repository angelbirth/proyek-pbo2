package pbo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.OperationNotSupportedException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JTable;

import com.mysql.jdbc.Statement;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainForm extends JFrame {

    private JPanel contentPane;
    private JTable dataTable;
    private String[] columnHeaders = { "ID", "Name", "Birthplace", "Birthday",
            "Address", "Phone Number", "E-mail Address", "Job", "Company" };
    private String[][] data;
    private ArrayList<String[]> data1;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private DefaultTableModel model;
    private Connection conn;

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

    /**
     * Create the frame.
     */
    public MainForm() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollPane.setSize(MainForm.this.getWidth() - 28,
                        MainForm.this.getHeight() - 70);
            }
        });
        
        try {
            conn = new Connection(Connection.MYSQL, "localhost/pbo", "pbo",
                    "pbo");
            conn.connect();
        } catch (OperationNotSupportedException e1) {
            JOptionPane.showMessageDialog(this,
                    "Untuk sementara Oracle tidak disupport",
                    "Incompatible server type", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        data = null;
        data1=new ArrayList<>(10);
        model = new DefaultTableModel(data, columnHeaders);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 681, 461);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 653, 391);
        contentPane.add(scrollPane);

        dataTable = new JTable(model);
        dataTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(dataTable);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {

                try {
                    java.sql.Statement statement = conn.getConnection()
                            .createStatement();
                   ResultSet set= statement.executeQuery("Select * from pbo");
                   for(int i=0;i<10;i++) {
                       
                   }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    System.err.println(e1.getMessage());
                }
            }
        });
    }
}
