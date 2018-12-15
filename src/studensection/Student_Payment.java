package studensection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Student_Payment extends JPanel implements ActionListener {

    Connection con;
    PreparedStatement pr;
    ResultSet re;
    Statement st;
    DefaultTableModel dm;
    JLabel name, fname, mname, mobile, photo;
    ImageIcon img, img1 = new ImageIcon(new ImageIcon(getClass()
            .getResource("/images/lg_avatar.png")).getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH));
    Border br = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 2), BorderFactory.createRaisedBevelBorder());
    Color bc = new Color(190, 204, 255);
    JTable payment_show_table;
    JButton btn_addentry, refresh_combo;
    JPanel panel_table, panel_entry, panel_combo, main, box;
    JTextField amount;
    JTextArea purpose;
    JLabel label;
    JComboBox combo_payment, combo_day, combo_month, combo_year, combo_purpose;
    String tablecolom[] = {"Date", "Name", "About", "Amount"};
    String monthname[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    GridBagConstraints gbc = new GridBagConstraints();
    int x = 0, y = -20;

    public Student_Payment() {
        super();
        setLayout(new BorderLayout());
        panel_entry = new JPanel(null);
        main = new JPanel(new GridBagLayout());
        panel_table = new JPanel(new GridLayout());
        panel_combo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        refresh_combo = new JButton(new ImageIcon(getClass().getResource("/icons/refresh_icon_small.png")));
        refresh_combo.setSize(25, 25);
        refresh_combo.addActionListener(this);
        combo_payment = new JComboBox(new String[]{""});
        combo_payment.setPreferredSize(new Dimension(600, 30));
        combo_payment.setFont(V.inputfnt);
        combo_payment.addActionListener(this);
        panel_combo.add(combo_payment);
        panel_combo.add(refresh_combo);
        //end
        //panel table
        String ss[][] = new String[][]{{"  ", " ", " ", " ", " "},};
        payment_show_table = new JTable((new DefaultTableModel(ss, tablecolom)));
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        //make the cell value centenred
        payment_show_table.getColumn(tablecolom[0]).setCellRenderer(tableCellRenderer);
        payment_show_table.getColumn(tablecolom[1]).setCellRenderer(tableCellRenderer);
        payment_show_table.getColumn(tablecolom[2]).setCellRenderer(tableCellRenderer);
        payment_show_table.getColumn(tablecolom[3]).setCellRenderer(tableCellRenderer);
        payment_show_table.setRowHeight(35);
        //center value

        JScrollPane jcc = new JScrollPane(payment_show_table);
        jcc.setPreferredSize(new Dimension(600, 450));
        jcc.setViewportView(payment_show_table);
        jcc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(jcc, BorderLayout.WEST);
        //end
        /// panel entry
        previewpan();

        combo_day = new JComboBox();
        for (int d = 1; d <= 31; d++) {
            combo_day.addItem(Integer.toString(d));
        }
        combo_day.setBounds(x + 20, y + 130, 80, 25);
        panel_entry.add(combo_day);
        combo_month = new JComboBox(monthname);
        combo_month.setBounds(x + 120, y + 130, 100, 25);
        combo_month.addActionListener(this);
        panel_entry.add(combo_month);
        combo_year = new JComboBox();
        for (int year = 2016; year <= 2050; year++) {
            combo_year.addItem(Integer.toString(year));
        }
        combo_year.addActionListener(this);
        combo_year.setBounds(x + 230, y + 130, 80, 25);
        panel_entry.add(combo_year);
        purpose = new JTextArea("Currrent");
        purpose.setMargin(new Insets(5, 5, 5, 5));
        purpose.setLineWrap(true);
        purpose.setBounds(x + 90, y + 160, 190, 60);
        purpose.setFont(V.inputfnt);
        panel_entry.add(purpose);
        amount = new JTextField("0");
        amount.setBounds(x + 90, y + 230, 220, 25);
        amount.setAlignmentX(JTextField.CENTER);
        amount.setFont(V.inputfnt);
        amount.setForeground(Color.red);
        panel_entry.add(amount);
        btn_addentry = new JButton("Save");
        btn_addentry.setIcon(new ImageIcon(getClass().getResource("/icons/icon_payment_small.png")));
        btn_addentry.setBounds(x + 160, y + 260, 95, 35);
        btn_addentry.setEnabled(false);
        btn_addentry.addActionListener(this);
        panel_entry.add(btn_addentry);
        add(panel_combo, BorderLayout.NORTH);
        //panel_entry.add(box);
        panel_entry.setBackground(new Color(62, 255, 95, 90));
        panel_entry.setBorder(br);
        panel_entry.setPreferredSize(new Dimension(350, 370));
        gbc.gridx = 0;
        gbc.gridy = 0;
        main.add(box, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        main.add(panel_entry, gbc);
        add(main);
        loadpaymentCombo();
    }

    public void previewpan() {
        box = new JPanel();
        box.setLayout(null);
        box.setBorder(br);
        box.setBackground(new Color(226, 220, 255));
        box.setSize(350, 120);
        box.setPreferredSize(new Dimension(350, 120));
        this.name = new JLabel("");
        fname = new JLabel("");
        mname = new JLabel("");
        mobile = new JLabel("");
        photo = new JLabel();
        name.setBounds(8, 6, 220, 25);
        name.setFont(V.stdrow);
        fname.setBounds(8, 30, 220, 25);
        fname.setFont(V.stdrow);
        mname.setBounds(8, 58, 220, 25);
        mname.setFont(V.stdrow);
        mobile.setBounds(8, 87, 220, 25);
        mobile.setFont(V.stdrow);
        photo.setBounds(230, 4, 110, 120);
        box.add(name);
        box.add(fname);
        box.add(mname);
        box.add(mobile);
        box.add(photo);
    }

    public void reload(String nam) throws SQLException, IOException, ClassNotFoundException {
        try {
            con = Global.getConnection(con);
            st = con.createStatement();
            String query = "SELECT NAME,FNAME,MNAME,MOBILE,PHOTO FROM " + Global.getStudentinfotable() + " WHERE NAME = " + "'" + nam + "'";
            re = st.executeQuery(query);
            if (re.next()) {
                changedata(re.getString("NAME"), re.getString("FNAME"), re.getString("MNAME"), re.getString("MOBILE"), re.getBlob("PHOTO"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void loadtable() {
        if (combo_payment.getSelectedIndex() == -1) {
            return;
        }
        if (combo_payment.getSelectedItem().equals("View All")) {
            loadspecificinfo();
            return;
        }
        amount.setText("0");
        String query;
        dm = (DefaultTableModel) payment_show_table.getModel();
        dm.setRowCount(1);

        try {
            String ss = combo_payment.getSelectedItem().toString();
            query = "SELECT * FROM " + Global.getStudentpaymentinfotable() + " WHERE NAME = " + "'" + ss + "'ORDER BY DATE ASC";

            con = Global.getConnection(con);
            st = con.createStatement();
            ResultSet re = st.executeQuery(query);
            while (re.next()) {
                dm.addRow(new Object[]{
                    re.getString("DATE"),
                    re.getString("NAME"),
                    re.getString("PURPOSE"),
                    re.getString("AMOUNT")
                });
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void loadspecificinfo() {
        String query, tr = "", ss = "", d = (String) combo_day.getSelectedItem(), y = (String) combo_year.getSelectedItem();
        int m = combo_month.getSelectedIndex(), sum = 0;
        if (m < 10) {
            tr = "0" + Integer.toString(m);
        } else {
            tr = Integer.toString(m);
        }
        if (m >= 1) {
            ss += " DATE like '%-" + tr + "-%' and";
        }
        if (combo_year.getSelectedIndex() >= 0) {
            ss += " Date like '" + y + "-%' ";
        } else {
            return;
        }
        try {
            con = Global.getConnection(con);
            query = "SELECT * FROM " + Global.getStudentpaymentinfotable() + " WHERE " + ss + "ORDER BY DATE DESC";

            pr = con.prepareStatement(query);
            re = pr.executeQuery();
            dm = (DefaultTableModel) payment_show_table.getModel();
            dm.setRowCount(1);
            while (re.next()) {
                sum += re.getInt("AMOUNT");
                dm.addRow(new Object[]{re.getDate("DATE"), re.getString("NAME"), re.getString("PURPOSE"), re.getInt("AMOUNT")});
            }
            amount.setText("Total : " + Integer.toString(sum));
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public void changedata(String name, String fname, String mname, String mobile, Blob bl)
            throws SQLException, IOException, ClassNotFoundException {

        this.photo.setIcon(img1);
        this.name.setText(name);
        this.fname.setText(fname);
        this.mname.setText(mname);
        this.mobile.setText(mobile);
        Blob blob = bl;
        if (blob == null) {
            photo.setIcon(img1);
            return;
        }
        ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
        ImageIcon imageIcon = (ImageIcon) ois.readObject();
        if (imageIcon != null) {
            this.photo.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(110, 120, Image.SCALE_SMOOTH)));
        }
        ois.close();
    }

    public void loadpaymentCombo() {
        DefaultComboBoxModel dm = (DefaultComboBoxModel) combo_payment.getModel();
        combo_payment.removeAllItems();
        combo_payment.setFont(new Font("Tahoma", 0, 16));
        combo_payment.addItem("View All");
        try {
            con = Global.getConnection(con);
            st = con.createStatement();
            String query = "SELECT NAME FROM " + Global.getStudentinfotable() + "";
            re = st.executeQuery(query);
            while (re.next()) {

                dm.addElement(re.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(combo_payment)) {
            if (combo_payment.getSelectedIndex() != -1) {
                if (combo_payment.getSelectedIndex() == 0) {
                    try {
                        box.setVisible(false);
                    } catch (Exception ex) {
                        Logger.getLogger(Student_Payment.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btn_addentry.setEnabled(false);
                    combo_year.setSelectedItem(Global.getYear());
                    loadtable();
                } else {
                    try {
                        box.setVisible(true);
                        btn_addentry.setEnabled(true);
                        changedata("", "", "", "", null);
                    } catch (Exception ed) {
                        System.out.println(ed);
                    }

                }
                try {

                    reload(combo_payment.getSelectedItem().toString());
                    loadtable();
                } catch (Exception ex) {
                    Logger.getLogger(Student_Payment.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                } catch (Exception er) {
                }

            } else {
                btn_addentry.setEnabled(false);
            }

        } else if (e.getSource().equals(refresh_combo)) {
            loadpaymentCombo();

        } else if (e.getSource().equals(btn_addentry)) {
            addpayment();
        } else if (e.getSource().equals(combo_month) || e.getSource().equals(combo_year)) {
            if (combo_payment.getSelectedIndex() == 0) {
                loadspecificinfo();
            }
        }
    }

    private void addpayment() {
        int amnt = 0;
        if (combo_month.getSelectedIndex() < 1) {
            JOptionPane.showMessageDialog(null, "Select valid a Month name");
            return;
        }
        try {
            amnt = Integer.parseInt(amount.getText());
        } catch (NumberFormatException nr) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered ", "Enter a valid amount ", JOptionPane.ERROR_MESSAGE);
        }
        String nam = (String) combo_payment.getSelectedItem(), prs = purpose.getText(), year = combo_year.getSelectedItem().toString();
        int day = combo_day.getSelectedIndex() + 1, month = combo_month.getSelectedIndex()+1;
        String ss = year + "-" + month + "-" + day;
        Date dt = Date.valueOf(ss);
        con = Global.getConnection(con);
        String query = "INSERT INTO " + Global.getStudentpaymentinfotable() + " ( DATE,NAME,PURPOSE,AMOUNT) VALUES(?,?,?,?)";
        try {
            pr = con.prepareStatement(query);
            pr.setDate(1, dt);
            pr.setString(2, nam);
            pr.setString(3, prs);
            pr.setInt(4, amnt);
            pr.executeUpdate();
            JOptionPane.showMessageDialog(this, "Succesful ", "Payment", JOptionPane.INFORMATION_MESSAGE);
            loadtable();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
