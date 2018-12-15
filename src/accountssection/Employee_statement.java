package accountssection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java_project.Global;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Employee_statement extends JPanel implements ActionListener {

    Border photoborder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 2),
            BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    ImageIcon defimfg = new ImageIcon(new ImageIcon(getClass().getResource("/images/student_avatar.png")).getImage().
            getScaledInstance(140, 140, Image.SCALE_SMOOTH));
    JLabel[] label1 = new JLabel[7];
    JPanel epan, main;
    JTextField[] tf = new JTextField[7];
    int x = 10, y = 0;
    JLabel idno, idl;
    JButton btn_add, attach;
    JRadioButton radio_add, radio_update, radio_view;
    JComboBox combo_empview;
    public Employee_statement() {
        super();
        setLayout(new GridBagLayout());
        main = new JPanel(new GridLayout());
        setBackground(new Color(0, 105, 148));
        init();
        main.add(epan);
        main.setPreferredSize(new Dimension(600, 500));
        add(main);
        loadcmbo();
    }

    public void init() {
        epan = new JPanel();
        epan.setBounds(250, 50, 400, 340);
        epan.setLayout(null);
        epan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        //laebls
        idl = new JLabel("ID no: ");
        idl.setBounds(x + 10, y + 0 + 115, 50, 25);
        idno = new JLabel();
        idno.setBounds(x + 90, y + 0 + 115, 50, 25);
        label1[0] = new JLabel("Name : ");
        label1[0].setBounds(x + 10, y + 0 + 145, 50, 25);
        label1[1] = new JLabel("Mobile : ");
        label1[1].setBounds(x + 10, y + 30 + 145, 50, 25);
        label1[02] = new JLabel("NID no : ");
        label1[02].setBounds(x + 10, y + 60 + 145, 50, 25);
        label1[03] = new JLabel("Address : ");
        label1[03].setBounds(x + 10, y + 95 + 145, 50, 25);
        label1[04] = new JLabel("Salary : ");
        label1[04].setBounds(x + 10, y + 120 + 145, 50, 25);
        label1[05] = new JLabel(defimfg);
        label1[5].setBounds(x + 370 + 10, y + 145 - 50, 160, 160);
        label1[5].setBorder(photoborder);
        epan.add(idl);
        epan.add(idno);
        epan.add(label1[0]);
        epan.add(label1[1]);
        epan.add(label1[2]);
        epan.add(label1[3]);
        epan.add(label1[4]);
        epan.add(label1[5]);
        ///textfield
        tf[0] = new JTextField();
        tf[0].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[0].setBounds(x + 80, y + 145, 280, 25);
        tf[1] = new JTextField();
        tf[1].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[1].setBounds(x + 80, y + 30 + 145, 280, 25);
        tf[2] = new JTextField();
        tf[2].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[2].setBounds(x + 80, y + 60 + 145, 280, 25);
        tf[3] = new JTextField();
        tf[3].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[3].setBounds(x + 80, y + 90 + 145, 280, 25);
        tf[4] = new JTextField();
        tf[4].setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf[4].setBounds(x + 80, y + 120 + 145, 280, 25);
        epan.add(tf[0]);
        epan.add(tf[1]);
        epan.add(tf[2]);
        epan.add(tf[3]);
        epan.add(tf[4]);
        ///combobox
        combo_empview = new JComboBox(new DefaultComboBoxModel());
        combo_empview.setBounds(x + 80, y + 90, 280, 25);
        combo_empview.setVisible(false);
        combo_empview.addActionListener(this);
        epan.add(combo_empview);
        //--
        ButtonGroup bg = new ButtonGroup();
        radio_add = new JRadioButton("Add Record ");
        radio_add.setBounds(x + 40, y + 60, 100, 25);
        radio_view = new JRadioButton("view Record ");
        radio_view.setBounds(x + 150, y + 60, 100, 25);
        radio_update = new JRadioButton("update Record ");
        radio_update.setBounds(x + 260, y + 60, 120, 25);
        bg.add(radio_add);
        bg.add(radio_update);
        bg.add(radio_view);
        radio_add.addActionListener(this);
        radio_update.addActionListener(this);
        radio_view.addActionListener(this);
        epan.add(radio_add);
        epan.add(radio_update);
        epan.add(radio_view);

        //---
        attach = new JButton(new ImageIcon(getClass().getResource("/icons/icon_attach_small.png")));
        attach.setBounds(x + 380, y + 145 - 50 + 165, 25, 25);
        attach.setVisible(false);
        attach.addActionListener(this);
        epan.add(attach);
        btn_add = new JButton("Add");
        btn_add.setBounds(x + 80, y + 170 + 145, 280, 25);
        btn_add.addActionListener(this);
        epan.add(btn_add);
        //--
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(combo_empview)) {
            viewinfo();
        } else if (e.getSource().equals(radio_add)) {
            btn_add.setEnabled(true);
            combo_empview.setVisible(false);
            attach.setVisible(true);
            btn_add.setText("Add this Record");
        } else if (e.getSource().equals(radio_update)) {
            loadcmbo();
            combo_empview.setVisible(true);
            attach.setVisible(true);
            btn_add.setEnabled(true);
            btn_add.setText("Update This Record");

        } else if (e.getSource().equals(radio_view)) {
            loadcmbo();
            combo_empview.setVisible(true);
            attach.setVisible(false);
            btn_add.setEnabled(false);
            viewinfo();
        } else if (e.getSource().equals(btn_add)) {
            for (int i = 0; i < 5; i++) {
                if (tf[i].getText().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "All Field is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (radio_add.isSelected()) {
                // addd new record
                addemployee();
            } else if (radio_update.isSelected()) {
                //uipdate record
                updateemployee();
            }

        } else if (e.getSource().equals(attach)) {
            JFileChooser chooser = new JFileChooser("C:?user/desktop");
            int ok = chooser.showOpenDialog(this);
            if (ok == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                ImageIcon img = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
                label1[5].setIcon(img);
                return;
            }
        }

    }

    PreparedStatement pr;
    Connection con;
    Statement st;
    ResultSet re;

    public void updateemployee() {
        if (combo_empview.getSelectedIndex() <= 0) {
            return;
        }
        String name = combo_empview.getSelectedItem().toString();
        try {
            con = Global.getConnection(con);
            String query = "UPDATE  " + Global.getEmployeeinfo() + " SET NAME= ?, MOBILE =? "
                    + ",NIDNO =? ,ADDRESS =?, SALARY = ? ,PHOTO =? where ID = '" + idno.getText().toString() + "'";
            pr = con.prepareStatement(query);
            pr.setString(1, tf[0].getText());
            pr.setString(2, tf[1].getText());
            pr.setString(3, tf[2].getText());
            pr.setString(4, tf[3].getText());
            pr.setInt(5, Integer.parseInt(tf[4].getText()));

            ImageIcon imgi = (ImageIcon) label1[5].getIcon();
            Blob blob = con.createBlob();
            try (ObjectOutputStream oos = new ObjectOutputStream(blob.setBinaryStream(1))) {
                oos.writeObject(imgi);
                oos.close();
            }
            pr.setBlob(6, blob);
            pr.executeUpdate();
            JOptionPane.showMessageDialog(null, "Update Succesful");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update Failed\n" + e);
        }
    }

    public void addemployee() {

        try {
            con = Global.getConnection(con);
            String query = "INSERT INTO  " + Global.getEmployeeinfo() + "( NAME, MOBILE "
                    + ",NIDNO ,ADDRESS , SALARY , PHOTO )VALUES(?,?,?,?,?,?)";
            pr = con.prepareStatement(query);
            pr.setString(1, tf[0].getText());
            pr.setString(2, tf[1].getText());
            pr.setString(3, tf[2].getText());
            pr.setString(4, tf[3].getText());
            pr.setInt(5, Integer.parseInt(tf[4].getText()));
            ImageIcon imgi = (ImageIcon) label1[5].getIcon();
            Blob blob = con.createBlob();
            try (ObjectOutputStream oos = new ObjectOutputStream(blob.setBinaryStream(1))) {
                oos.writeObject(imgi);
                oos.close();
            }
            pr.setBlob(6, blob);
            pr.execute();
            JOptionPane.showMessageDialog(null, "New Record Added ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Add record Failed\n" + e);
        }
    }

    public void viewinfo() {
        if (combo_empview.getSelectedIndex() <= 0) {
            return;
        }
        String name = combo_empview.getSelectedItem().toString();
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
                idno.setText(Integer.toString(re.getInt("ID")));
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
                } catch (Exception ex) {
                    System.out.println("empview" + ex);
                }
            }
        } catch (Exception e) {
            System.out.println("empview" + e);
        }
    }

    public void loadcmbo() {
        DefaultComboBoxModel dm = (DefaultComboBoxModel) combo_empview.getModel();
        combo_empview.removeAllItems();
        combo_empview.setFont(new Font("Tahoma", 0, 16));
        combo_empview.addItem("Select Name");
        try {
            con = Global.getConnection(con);
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

}
