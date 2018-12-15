package accountssection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_project.Global;
import java_project.V;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import studensection.Student_Payment;

public class Salary_payment extends JPanel implements ActionListener, MouseListener {

    Connection con;
    PreparedStatement pr;
    Statement st;
    ResultSet re;
    DefaultTableModel dm;

    JTable table_salary_expense;
    JButton btn_addentry, refresh_combo;
    JPanel panel_table, panel_entry, panel_combo, main;
    JTextField amount;
    JTextArea purpose;
    JLabel label, warn_label;
    JComboBox combo_employee, combo_month, combo_year;
    String tablecolom[] = {"PayID", "Date", "Note", "Amount"};
    String monthname[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    //----prevpan
    ImageIcon defimfg = new ImageIcon(new ImageIcon(getClass().getResource("/images/student_avatar.png")).getImage().
            getScaledInstance(140, 140, Image.SCALE_SMOOTH));
    JLabel[] label1 = new JLabel[7];
    JPanel epan;
    JTextField[] tf = new JTextField[7];

    public Salary_payment() {
        super();
        setLayout(new BorderLayout());
        main = new JPanel(new GridLayout());
        panel_table = new JPanel(new GridLayout());
        panel_combo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel_combo.setBackground(new Color(15, 104, 36));
        combo_employee = new JComboBox(new DefaultComboBoxModel(new String[]{"Select Name"}));
        combo_employee.setPreferredSize(new Dimension(300, 30));
        combo_employee.addActionListener(this);
        panel_combo.add(combo_employee);
        refresh_combo = new JButton(new ImageIcon(getClass().getResource("/icons/refresh_icon_small.png")));
        refresh_combo.setPreferredSize(new Dimension(30, 30));
        refresh_combo.addActionListener(this);
        panel_combo.add(refresh_combo);
        label = new JLabel(" Salary Payment Entry Form");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tahoma", Font.BOLD, 25));
        panel_combo.add(label);
        //end
        //panel table
        table_salary_expense = new JTable(new DefaultTableModel(new String[][]{{"2018-12-11", "january", "purpose", "1000"}}, tablecolom));
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table_salary_expense.getColumn(tablecolom[0]).setCellRenderer(tableCellRenderer);
        table_salary_expense.getColumn(tablecolom[1]).setCellRenderer(tableCellRenderer);
        table_salary_expense.getColumn(tablecolom[2]).setCellRenderer(tableCellRenderer);
        table_salary_expense.getColumn(tablecolom[3]).setCellRenderer(tableCellRenderer);
        table_salary_expense.setFont(new Font("Serif", Font.PLAIN, 22));
        table_salary_expense.setRowHeight(22);
        table_salary_expense.setBackground(Color.WHITE);
        table_salary_expense.addMouseListener(this);
        JScrollPane jcc = new JScrollPane(table_salary_expense);
        jcc.setPreferredSize(new Dimension(600, 450));
        jcc.setViewportView(table_salary_expense);
        jcc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(jcc, BorderLayout.WEST);
        //end
        /// panel entry
        panel_entry = new JPanel(null);
        panel_entry.setPreferredSize(new Dimension(350, 500));
        panel_entry.setSize(400, 550);
        purpose = new JTextArea("Salary");
        purpose.setBorder(BorderFactory.createTitledBorder(null, "Purposer of the entry", 4, 1, new Font("Tahoma", Font.BOLD, 16)));
        purpose.setMargin(new Insets(5, 5, 5, 5));
        purpose.setLineWrap(true);
        purpose.setBounds(10, 160, 190, 90);
        purpose.setFont(V.inputfnt);
        panel_entry.add(purpose);
        amount = new JTextField("0");
        amount.setBorder(BorderFactory.createTitledBorder(null, "Amount", 4, 1, new Font("Tahoma", Font.BOLD, 16)));
        amount.setBounds(10, 260, 190, 60);
        amount.setAlignmentX(JTextField.CENTER);
        amount.setFont(V.inputfnt);
        amount.setForeground(Color.red);
        panel_entry.add(amount);
        btn_addentry = new JButton("save");
        btn_addentry.setBounds(115, 325, 80, 25);
        panel_entry.add(btn_addentry);
        btn_addentry.addActionListener(this);
        //END OF PANEL ENTRY
        add(panel_combo, BorderLayout.NORTH);
        main.add(panel_entry);
        add(main);
        setBackground(new Color(0, 105, 128));
        panel_table.setOpaque(false);
        main.setOpaque(false);
        panel_entry.setOpaque(false);
        jcc.setOpaque(false);
        jcc.getViewport().setOpaque(false);
        table_salary_expense.setOpaque(false);
        addvpan();
        load_comboemployee();
    }

    public void addvpan() {
        epan = new JPanel();
        epan.setBounds(250, 50, 400, 340);
        epan.setLayout(null);
        label1[0] = new JLabel("Name : ");
        label1[0].setBounds(10, 0 + 145, 50, 25);
        label1[1] = new JLabel("Mobile : ");
        label1[1].setBounds(10, 30 + 145, 50, 25);
        label1[02] = new JLabel("NID no : ");
        label1[02].setBounds(10, 60 + 145, 50, 25);
        label1[03] = new JLabel("Address : ");
        label1[03].setBounds(10, 95 + 145, 50, 25);
        label1[04] = new JLabel("Salary : ");
        label1[04].setBounds(10, 120 + 145, 50, 25);
        label1[05] = new JLabel(defimfg);
        label1[5].setBounds(130, 5, 140, 140);
        label1[5].setBorder(BorderFactory.createLineBorder(Color.yellow));
        epan.add(label1[0]);
        epan.add(label1[1]);
        epan.add(label1[2]);
        epan.add(label1[3]);
        epan.add(label1[4]);
        epan.add(label1[5]);
        tf[0] = new JTextField();
        tf[0].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[0].setEditable(false);
        tf[0].setBounds(80, 5 + 145, 280, 25);
        tf[1] = new JTextField();
        tf[1].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[1].setEditable(false);
        tf[1].setBounds(80, 35 + 145, 280, 25);
        tf[2] = new JTextField();
        tf[2].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[2].setEditable(false);
        tf[2].setBounds(80, 60 + 145, 280, 25);
        tf[3] = new JTextField();
        tf[3].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[3].setEditable(false);
        tf[3].setBounds(80, 90 + 145, 280, 25);
        tf[4] = new JTextField();
        tf[4].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[4].setEditable(false);
        tf[4].setBounds(80, 120 + 145, 280, 25);
        epan.add(tf[0]);
        epan.add(tf[1]);
        epan.add(tf[2]);
        epan.add(tf[3]);
        epan.add(tf[4]);
        epan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        panel_entry.add(epan);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(refresh_combo)) {

            load_comboemployee();
        }
        if (e.getSource().equals(combo_employee)) {
            if (combo_employee.getSelectedIndex() <= 0) {
                btn_addentry.setEnabled(false);
            } else {
                btn_addentry.setEnabled(true);
            }
            loadinfo();
        }
        if (e.getSource().equals(btn_addentry)) {

            savenewpayment();

        }
    }

    public void savenewpayment() {
        int amnt = 0;
        try {
            amnt = Integer.parseInt(amount.getText());
        } catch (NumberFormatException nr) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered ", "Enter a valid amount ", JOptionPane.ERROR_MESSAGE);
        }
        String nam = (String) combo_employee.getSelectedItem(), prs = purpose.getText();
        java.sql.Date dt = java.sql.Date.valueOf(Global.getDate());
        con = Global.getConnection(con);
        String query = "INSERT INTO " + Global.getSalarytablename() + " ( NAME,DATE,NOTE,AMOUNT) VALUES(?,?,?,?)";
        try {
            pr = con.prepareStatement(query);
            pr.setString(1, nam);
            pr.setDate(2, dt);
            pr.setString(3, prs);
            pr.setInt(4, amnt);
            pr.executeUpdate();
            JOptionPane.showMessageDialog(this, "Succesful ", "Payment", JOptionPane.INFORMATION_MESSAGE);
            loadtable();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
        amount.setText("0");
    }

    private void loadinfo() {
        if (combo_employee.getSelectedIndex() == -1) {
            return;
        }
        if (combo_employee.getSelectedIndex() == 0) {
            tf[0].setText("");
            tf[1].setText("");
            tf[2].setText("");
            tf[3].setText("");
            tf[4].setText("");
            label1[5].setIcon(defimfg);
            loadtable();
            return;
        }
        String name = combo_employee.getSelectedItem().toString();
        try {
            con = Global.getConnection(con);
            st = con.createStatement();
            String query = "Select * from " + Global.getEmployeeinfo() + " where name = '" + name + "'";
            re = st.executeQuery(query);
            if (re.next()) {

                tf[0].setText(re.getString("NAME"));
                tf[1].setText(re.getString("MOBILE"));
                tf[2].setText(re.getString("NIDNO"));
                tf[3].setText(re.getString("ADDRESS"));
                tf[4].setText(Integer.toString(re.getInt("SALARY")));

                try {

                    Blob blob = re.getBlob("PHOTO");
                    if (blob != null) {

                        ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
                        ImageIcon imageIcon = (ImageIcon) ois.readObject();
                        if (imageIcon != null) {
                            label1[5].setIcon(imageIcon);
                        }
                        ois.close();
                    } else {
                        label1[5].setIcon(defimfg);
                    }
                    loadtable();
                } catch (Exception ex) {
                    System.out.println("238" + ex);
                }
            }
        } catch (Exception e) {
            System.out.println("285" + e);
        }
    }

    public void loadtable() {
        String query;
        if (combo_employee.getSelectedIndex() == -1) {
            return;
        }
        if (combo_employee.getSelectedIndex() == 0) {
            query = (" SELECT * FROM " + Global.getSalarytablename() + " WHERE  1 ORDER BY DATE DESC");
        } else {
            String nam = combo_employee.getSelectedItem().toString();
            query = (" SELECT * FROM " + Global.getSalarytablename() + " WHERE NAME = '" + nam + "'ORDER BY PAYID DESC");
        }

        try {
            con = Global.getConnection(con);

            pr = con.prepareStatement(query);
            re = pr.executeQuery();
            dm = (DefaultTableModel) table_salary_expense.getModel();
            dm.setRowCount(0);
            while (re.next()) {
                // sum += re.getInt("AMOUNT");
                dm.addRow(new Object[]{re.getInt("PAYID"), re.getDate("DATE"), re.getString("NOTE"), re.getInt("AMOUNT")});
            }
            //  total.setText("Total : " + Integer.toString(sum));
        } catch (Exception e) {
            System.out.println("254" + e);
        }

    }

    public void load_comboemployee() {
        if (combo_employee.getSelectedIndex() == -1) {
            return;
        }
        DefaultComboBoxModel dm = (DefaultComboBoxModel) combo_employee.getModel();
        combo_employee.removeAllItems();
        combo_employee.setFont(new Font("Tahoma", 0, 16));
        combo_employee.addItem("View All");
        try {
            System.out.println("Attempting to connect for loading salary");
            con = Global.getConnection(con);
            System.out.println("Succesfully connected");
            st = con.createStatement();
            String query = "SELECT NAME FROM " + Global.getEmployeeinfo() + "";
            re = st.executeQuery(query);
            while (re.next()) {
                dm.addElement(re.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    int second = 0;
    boolean holding;

    public void countsecond() {
        second = 0;
        holding = true;
        Thread tr = new Thread(() -> {
            try {
                while (holding) {
                    Thread.sleep(500);
                    second++;
                    if (second >= 3) {
                        holding = false;
                        String k = JOptionPane.showInputDialog(null, "Enter A value to update");
                        if (k.equals(null)) {
                            return;
                        }
                        try {
                            int val = Integer.parseInt(k);
                            int r = table_salary_expense.getSelectedRow();
                            int amnt = (int) table_salary_expense.getValueAt(r, 3);
                            int pid = (int) table_salary_expense.getValueAt(r, 0);
                            con = Global.getConnection(con);
                            String query = "UPDATE " + Global.getSalarytablename() + ""
                                    + " SET AMOUNT = ? WHERE PAYID = ? ";
                            PreparedStatement pr1 = con.prepareStatement(query);
                            pr1.setString(1, k);
                            pr1.setInt(2, pid);
                            pr1.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Update Succesfull ", "Update", JOptionPane.INFORMATION_MESSAGE);
                            loadtable();
                        } catch (Exception ex) {

                            System.out.println("bangla" + ex);
                        }
                    }
                }
            } catch (Exception er) {

            }
        });
        tr.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        countsecond();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        holding = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
