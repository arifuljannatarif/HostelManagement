package accountssection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_project.Global;
import java_project.V;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import studensection.Student_Payment;

public class Food_expense extends JPanel implements ActionListener, MouseListener {

    Connection con;
    Statement st;
    PreparedStatement pr;
    ResultSet re;
    DefaultTableModel dm;
    JTable table_food_expense;
    JButton btn_addentry;
    JPanel panel_table, panel_entry, panel_combo, main;
    JTextField amount;
    JTextArea purpose;
    int today;
    JLabel label, warn_label, total, text;
    JComboBox combo_day, combo_month, combo_year;
    String tablecolom[] = {"PayID", "Date", "Purpose", "Amount"};
    String monthname[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Food_expense() {
        super();
        setLayout(new BorderLayout());
        main = new JPanel(new GridBagLayout());
        panel_table = new JPanel(new GridLayout());
        panel_combo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel_combo.setBackground(new Color(90, 56, 148));
        today = Integer.parseInt(Global.getDay());
        combo_day = new JComboBox();
        for (int d = 1; d <= 31; d++) {
            combo_day.addItem(Integer.toString(d));
        }
        combo_day.setPreferredSize(new Dimension(100, 30));
        combo_month = new JComboBox(monthname);
        combo_month.setPreferredSize(new Dimension(100, 30));
        combo_year = new JComboBox();
        for (int year = 2016; year <= 2050; year++) {
            combo_year.addItem(Integer.toString(year));
        }
        combo_year.setPreferredSize(new Dimension(100, 30));
        combo_day.setSelectedIndex(Integer.parseInt(Global.getDay()) - 1);
        combo_month.setSelectedIndex(Integer.parseInt(Global.getMonth()));
        combo_year.setSelectedItem(Global.getYear());
        label = new JLabel("Food Expense Entry Page");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tahoma", Font.BOLD, 25));
        panel_combo.add(label);
        combo_day.addActionListener(this);
        combo_month.addActionListener(this);
        combo_year.addActionListener(this);
        //end
        //panel table
        table_food_expense = new JTable(new DefaultTableModel(new String[][]{{}}, tablecolom));
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table_food_expense.getColumn(tablecolom[0]).setCellRenderer(tableCellRenderer);
        table_food_expense.getColumn(tablecolom[1]).setCellRenderer(tableCellRenderer);
        table_food_expense.getColumn(tablecolom[2]).setCellRenderer(tableCellRenderer);
        table_food_expense.getColumn(tablecolom[3]).setCellRenderer(tableCellRenderer);
        table_food_expense.setFont(new Font("Serif", Font.PLAIN, 22));
        table_food_expense.setRowHeight(22);
        table_food_expense.addMouseListener(this);
        JScrollPane jcc = new JScrollPane(table_food_expense);
        jcc.setPreferredSize(new Dimension(600, 450));
        jcc.setViewportView(table_food_expense);
        jcc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(jcc, BorderLayout.WEST);
        //end
        /// panel entry
        panel_entry = new JPanel(null);

        warn_label = new JLabel("Giving Entry In a Different Date");
        warn_label.setFont(new Font("Tahoma", Font.BOLD, 16));
        warn_label.setForeground(Color.red);
        warn_label.setVisible(false);
        warn_label.setBounds(60, 100, 280, 30);
        text = new JLabel("Entry Date :");
        text.setBounds(20, 125, 70, 30);
        panel_entry.add(text);
        total = new JLabel("Total : ");
        total.setFont(new Font("Tahoma", Font.BOLD, 16));
        total.setForeground(Color.BLACK);
        total.setBounds(40, 40, 280, 30);
        panel_entry.add(total);
        //-------------- 
        combo_month.setBounds(40, 70, 150, 30);
        combo_year.setBounds(190, 70, 150, 30);
        combo_day.setBounds(90, 125, 190, 30);
        panel_entry.add(combo_month);
        panel_entry.add(combo_year);
        panel_entry.add(combo_day);
        //---------------
        panel_entry.add(warn_label);
        purpose = new JTextArea();
        purpose.setBorder(BorderFactory.createTitledBorder(null, "Purposer of the entry", 4, 1, new Font("Tahoma", Font.BOLD, 16)));
        purpose.setMargin(new Insets(5, 5, 5, 5));
        purpose.setLineWrap(true);
        purpose.setBounds(90, 160, 190, 90);
        purpose.setFont(V.inputfnt);
        panel_entry.add(purpose);
        amount = new JTextField("0");
        amount.setBorder(BorderFactory.createTitledBorder(null, "Amount", 4, 1, new Font("Tahoma", Font.BOLD, 16)));
        amount.setBounds(90, 260, 190, 60);
        amount.setAlignmentX(JTextField.CENTER);
        amount.setFont(V.inputfnt);
        amount.setForeground(Color.red);
        panel_entry.add(amount);
        btn_addentry = new JButton("Save");
        //btn_addentry.setIcon(new ImageIcon(getClass().getResource("/icons/icon_payment_small.png")));
        btn_addentry.setBounds(140, 330, 140, 25);
        btn_addentry.addActionListener(this);
        panel_entry.add(btn_addentry);
        //END OF PANEL ENTRY
        add(panel_combo, BorderLayout.NORTH);
        main.add(panel_entry);
        add(main);
        setBackground(new Color(201, 203, 248));
        panel_table.setOpaque(false);
        main.setOpaque(false);
        panel_entry.setPreferredSize(new Dimension(350, 400));
        panel_entry.setSize(400, 550);
        panel_entry.setBackground(new Color(76, 81, 124, 90));
        jcc.setOpaque(false);
        jcc.getViewport().setOpaque(false);
        table_food_expense.setOpaque(false);
        loadinfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(combo_month) || e.getSource().equals(combo_year)) {
            loadinfo();
        } else if (e.getSource().equals(combo_day)) {
            if (combo_day.getSelectedIndex() != today - 1) {
                warn_label.setVisible(true);
            } else {
                warn_label.setVisible(false);
            }
        } else if (e.getSource().equals(btn_addentry)) {
            addpayment();
        }
    }

    private void loadinfo() {
        String query, tr, ss = "", d = (String) combo_day.getSelectedItem(), y = (String) combo_year.getSelectedItem();
        int m = combo_month.getSelectedIndex(), sum = 0;
        if (m < 10) {
            tr = "0" + Integer.toString(m);
        } else {
            tr = Integer.toString(m);
        }
        if (m >= 1) {
            ss += "DATE like '%-" + tr + "-%' and";
        }
        if (combo_year.getSelectedIndex() >= 0) {
            ss += " Date like '" + y + "%'";
        } else {
            return;
        }
        try {
            con = Global.getConnection(con);
            query = "SELECT * FROM " + Global.getFoodcosttablename() + " WHERE " + ss + "ORDER BY DATE DESC";
            pr = con.prepareStatement(query);
            re = pr.executeQuery();
            dm = (DefaultTableModel) table_food_expense.getModel();
            dm.setRowCount(1);
            while (re.next()) {
                sum += re.getInt("AMOUNT");
                dm.addRow(new Object[]{Integer.toString(re.getInt("PAYID")), re.getDate("DATE"), re.getString("PURPOSE"), re.getInt("AMOUNT")});
            }
            total.setText("Total : " + Integer.toString(sum));
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    private void addpayment() {

        if (combo_day.getSelectedIndex() == -1 || combo_month.getSelectedIndex() <= 0) {
            return;
        }
        int amnt = 0;
        try {
            amnt = Integer.parseInt(amount.getText());
        } catch (NumberFormatException nr) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered ", "Enter a valid amount ", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String prs = purpose.getText(), year = combo_year.getSelectedItem().toString();
        String day = (String) combo_day.getSelectedItem();
        int month = combo_month.getSelectedIndex();
        String mn = combo_month.getSelectedItem().toString();
        Date dt = Date.valueOf(year + "-" + month + "-" + day);
        con = Global.getConnection(con);
        String query = "INSERT INTO " + Global.getFoodcosttablename() + " ( DATE,MONTH,PURPOSE,AMOUNT) VALUES(?,?,?,?)";
        try {
            pr = con.prepareStatement(query);
            pr.setDate(1, dt);
            pr.setString(2, mn);
            pr.setString(3, prs);
            pr.setInt(4, amnt);
            pr.executeUpdate();
            JOptionPane.showMessageDialog(this, "Succesful ", "Entry", JOptionPane.INFORMATION_MESSAGE);
            loadinfo();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error ", "Entry", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Student_Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int second;
    boolean holding = false;

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
                            int r = table_food_expense.getSelectedRow();
                           // int amnt = Integer.parseInt((String) table_food_expense.getValueAt(r, 3));
                            int pid = Integer.parseInt((String) table_food_expense.getValueAt(r, 0));
                            con = Global.getConnection(con);
                            System.out.println("aise");
                            String query = "UPDATE " + Global.getFoodcosttablename() + ""
                                    + " SET AMOUNT = ? WHERE PAYID = ? ";
                            PreparedStatement pr1 = con.prepareStatement(query);
                            pr1.setInt(1, val);
                            pr1.setInt(2, pid);
                            pr1.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Update Succesfull ", "Update", JOptionPane.INFORMATION_MESSAGE);
                            loadinfo();
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
    public void mouseClicked(MouseEvent e) {
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
