package studensection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Viewstudent extends JPanel implements ActionListener {

    Connection con;
    Statement st;
    PreparedStatement pr;
    ResultSet re;
    String query;
    JButton btn_refresh, save, update, reset, delet, sattach, pattach, lgattach;
    JComboBox viewcombo;
    String[] arr = {"Hostel Id: ", "Name : ", "Mobile : ", "Class : ",
        "Institute : ", "Fathers Name :", "Mothers Name :", "Contact No", "Address :",
        "Name ", "Contact NO ", "Relation ", "Monthly fee", "Alloted Room", "Refference ", "Remarks"};
    JPanel span, ppan, lgpan, opan, main, panel_combo, footer;
    JLabel pic_student, pic_parent, pic_lg;
    JTextArea aaddress, aremarks;
    JTextField[] output = new JTextField[30];
    JLabel[] label = new JLabel[30];
    JLabel jlabel;
    Border photoborder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 2),
            BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    int photoh = 200, photow = 200;
    boolean opaque = false, editable = false, state = false;
    int lh = 20, lw = 90, jth = 30, jtw = 300, loaded = 0, btnh = 30, btnw = 100;
    JScrollPane jc;
    Color background = new Color(255, 246, 255);

    public Viewstudent() {
        super();
        setBackground(background);
        main = new JPanel(new GridLayout(2, 2));
        main.setOpaque(false);
        setLayout(new BorderLayout());
        makespan();
        main.add(span);
        makeppan();
        main.add(ppan);
        makelgpan();
        main.add(lgpan);
        makeopan();
        main.add(opan);
        makefooter();
        add(footer, BorderLayout.SOUTH);
        panel_combo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btn_refresh = new JButton(new ImageIcon(getClass().getResource("/icons/refresh_icon_small.png")));
        btn_refresh.setPreferredSize(new Dimension(30, 30));
        viewcombo = new JComboBox();
        viewcombo.setPreferredSize(new Dimension(600, 30));
        viewcombo.setFont(V.outputfnt);
        panel_combo.add(viewcombo);
        panel_combo.add(btn_refresh);
        add(panel_combo, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);
        loadstudentviewCombo();
        btn_refresh.addActionListener(this);
        viewcombo.addActionListener(this);
        pic_student.setBorder(photoborder);
        pic_parent.setBorder(photoborder);
        pic_lg.setBorder(photoborder);

    }

    public void makespan() {
        span = new JPanel(null);
        span.setOpaque(false);
        span.setBorder(BorderFactory.createTitledBorder("Student Info"));
        int x = 10, y = 30 + jth, x1 = 100, y1 = 55, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;
        output[0] = new JTextField();
        output[0].setEditable(false);
        output[0].setBounds(80, 20, jtw / 2, jth);
        span.add(output[0]);
        jlabel = new JLabel(arr[0]);
        jlabel.setBounds(10, 20, sw1, sh1);
        span.add(jlabel);
        for (int i = 1; i < 5; i++) {
            jlabel = new JLabel(arr[i]);
            jlabel.setBounds(10, y, sw1, sh1);
            span.add(jlabel);
            output[i] = new JTextField();
            output[i].setBounds(80, y1, sw, sh);
            output[i].setFont(V.outputfnt);
            output[i].setEditable(editable);
            output[i].setOpaque(opaque);
            span.add(output[i]);
            span.add(jlabel);
            y1 += 45;
            y += 45;
        }
        pic_student = new JLabel();
        ImageIcon img = new ImageIcon(new ImageIcon(getClass()
                .getResource("/images/student_avatar.png")).getImage().getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
        pic_student.setBounds(430, 20, photow, photoh);
        pic_student.setIcon(img);
        span.add(pic_student);
        span.setSize(550, 550);
        img = new ImageIcon(getClass().getResource("/icons/icon_attach_small.png"));
        sattach = new JButton(img);
        sattach.setOpaque(false);
        sattach.setBounds(400, 20, 25, 25);
        sattach.setVisible(false);
        span.add(sattach);
        sattach.addActionListener(this);
        //  span.setBackground(Color.red);

    }

    public void makeppan() {
        ppan = new JPanel();
        ppan.setOpaque(false);
        ppan.setBorder(BorderFactory.createTitledBorder("Guardian Info"));
        ppan.setLayout(null);
        int x = 10, y = 25, x1 = 110, y1 = 20, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;
        for (int i = 5; i < 8; i++) {
            output[i] = new JTextField();
            output[i].setFont(V.outputfnt);
            output[i].setBounds(x1, y1, sw, sh);
            output[i].setFont(V.outputfnt);
            output[i].setEditable(editable);
            output[i].setOpaque(false);
            jlabel = new JLabel(arr[i]);
            jlabel.setBounds(10, y, sw1, sh1);
            ppan.add(jlabel);
            ppan.add(output[i]);
            y1 += 45;
            y += 45;
        }
        jlabel = new JLabel(arr[8]);
        jlabel.setBounds(10, y, sw1, sh1);
        ppan.add(jlabel);
        aaddress = new JTextArea();
        aaddress.setFont(V.outputfnt);
        aaddress.setOpaque(opaque);
        aaddress.setLineWrap(true);
        aaddress.setBounds(x1, y1, sw, sh + 50);
        ppan.add(aaddress);
        pic_parent = new JLabel();
        ImageIcon img = new ImageIcon(new ImageIcon(getClass()
                .getResource("/images/parent_avatar.png")).getImage()
                .getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
        pic_parent.setBounds(440, 20, photow, photoh);
        pic_parent.setIcon(img);
        ppan.add(pic_parent);
        img = new ImageIcon(getClass().getResource("/icons/icon_attach_small.png"));
        pattach = new JButton(img);
        pattach.setOpaque(false);
        pattach.setBounds(410, 20, 25, 25);
        pattach.setVisible(false);
        ppan.add(pattach);
        pattach.addActionListener(this);
        // ppan.setBackground(Color.YELLOW);

    }

    public void makelgpan() {
        lgpan = new JPanel();
        lgpan.setPreferredSize(new Dimension(600, 300));
        lgpan.setBorder(BorderFactory.createTitledBorder("Local Gardian info"));
        lgpan.setLayout(null);
        lgpan.setOpaque(false);
        int x = 10, y = 30, x1 = 110, y1 = 30, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;
        for (int i = 8; i < 11; i++) {
            jlabel = new JLabel(arr[i + 1]);
            output[i] = new JTextField();
            output[i].setBounds(x1, y1, sw, sh);
            output[i].setEditable(editable);
            output[i].setFont(V.outputfnt);
            output[i].setOpaque(opaque);
            jlabel.setBounds(10, y, sw1, sh1);
            lgpan.add(output[i]);
            lgpan.add(jlabel);
            y1 += 45;
            y += 45;
        }
        pic_lg = new JLabel();
        ImageIcon img = new ImageIcon(new ImageIcon(getClass()
                .getResource("/images/lg_avatar.png")).getImage().getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
        pic_lg.setBounds(435, 20, photow, photoh);
        pic_lg.setIcon(img);
        lgpan.add(pic_lg);
        lgpan.setSize(550, 550);
        img = new ImageIcon(getClass().getResource("/icons/icon_attach_small.png"));
        lgattach = new JButton(img);
        lgattach.setOpaque(false);
        lgattach.setBounds(410, 20, 25, 25);
        lgattach.setVisible(false);
        lgpan.add(lgattach);
        lgattach.addActionListener(this);

    }

    public void makeopan() {
        opan = new JPanel();
        opan.setPreferredSize(new Dimension(600, 300));
        opan.setBorder(BorderFactory.createTitledBorder("Office Section"));
        opan.setLayout(null);
        opan.setOpaque(opaque);
        int x = 10, y = 30, x1 = 110, y1 = 30, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;

        for (int i = 11; i < 14; i++) {
            jlabel = new JLabel(arr[i]);
            output[i] = new JTextField();
            output[i].setBounds(x1, y1, sw, sh);
            output[i].setEditable(editable);
            output[i].setOpaque(opaque);
            output[i].setFont(V.outputfnt);
            jlabel.setBounds(10, y, sw1 + 5, sh1);
            opan.add(output[i]);
            opan.add(jlabel);
            y1 += 45;
            y += 45;
        }
        jlabel = new JLabel(arr[15]);
        aremarks = new JTextArea();
        aremarks.setBounds(x1, y1, sw, sh + 40);
        aremarks.setEditable(editable);
        aremarks.setBorder(BorderFactory.createLineBorder(Color.black));
        aremarks.setOpaque(opaque);
        aremarks.setFont(V.outputfnt);
        jlabel.setBounds(10, y, sw1 + 5, sh1);
        opan.add(aremarks);
        opan.add(jlabel);
        opan.setSize(550, 550);
    }

    public void makefooter() {
        footer = new JPanel();
        footer.setPreferredSize(new Dimension(getWidth(), 35));
        ImageIcon img;
        footer.setBackground(new Color(181, 205, 228, 200));
        reset = new JButton("Reset");
        img = new ImageIcon(getClass().getResource("/icons/logout_icon_small.png"));
        reset.setIcon(img);
        reset.setPreferredSize(new Dimension(btnw, btnh));
        delet = new JButton("Delete");
        img = new ImageIcon(getClass().getResource("/icons/search_icon_small.png"));
        delet.setIcon(img);
        delet.setPreferredSize(new Dimension(btnw, btnh));
        update = new JButton("Update");
        img = new ImageIcon(getClass().getResource("/icons/edit_icon_small.gif"));
        update.setIcon(img);
        update.setPreferredSize(new Dimension(btnw, btnh));
        save = new JButton("Save");
        save.setEnabled(false);
        img = new ImageIcon(getClass().getResource("/icons/save_icon.png"));
        save.setIcon(img);
        save.setPreferredSize(new Dimension(btnw, btnh));
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        reset.addActionListener(this);
        update.addActionListener(this);
        save.addActionListener(this);
        delet.addActionListener(this);
        footer.add(reset);
        footer.add(delet);
        footer.add(update);
        footer.add(save);
    }
    ImageIcon defimg = new ImageIcon(getClass().getResource("/images/student_avatar.png"));

    public void show_info() {
        if (viewcombo.getSelectedIndex() == -1) {
            return;
        }
        String string = viewcombo.getSelectedItem().toString();
        try {
            con = Global.getConnection(con);
            st = con.createStatement();
            query = "Select * from " + Global.getStudentinfotable() + " Where NAME = " + "'" + string + "'" + "";
            re = st.executeQuery(query);
            if (re.next()) {
                output[0].setText(re.getString("HOSTELID"));
                output[1].setText(re.getString("NAME"));
                output[2].setText(re.getString("MOBILE"));
                output[03].setText(re.getString("CLASS"));
                output[4].setText(re.getString("INSTITUTION"));
                output[5].setText(re.getString("FNAME"));
                output[6].setText(re.getString("MNAME"));
                output[7].setText(re.getString("PMOBILE"));
                aaddress.setText(re.getString("ADDRESS"));
                output[8].setText(re.getString("LGNAME"));
                output[9].setText(re.getString("LGMOBILE"));
                output[10].setText(re.getString("RELATION"));
                output[11].setText(re.getString("MONTHLYFEE"));
                output[12].setText(re.getString("ROOM"));
                output[13].setText(re.getString("REFERENCE"));
                aremarks.setText(re.getString("REMARKS"));
                //reading image

                Blob blob = re.getBlob("PHOTO");
                ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
                ImageIcon imageIcon = (ImageIcon) ois.readObject();
                if (imageIcon != null) {
                    pic_student.setIcon(imageIcon);
                } else {
                    pic_student.setIcon(defimg);
                }
                ois.close();
                //parent
                blob = re.getBlob("PPHOTO");
                ois = new ObjectInputStream(blob.getBinaryStream());
                imageIcon = (ImageIcon) ois.readObject();
                if (imageIcon != null) {
                    pic_parent.setIcon(imageIcon);
                } else {
                    pic_parent.setIcon(defimg);
                }
                ois.close();
                blob = re.getBlob("LGPHOTO");
                ObjectInputStream ois1 = new ObjectInputStream(blob.getBinaryStream());
                ImageIcon imageIcon1 = (ImageIcon) ois1.readObject();
                if (imageIcon1 != null) {
                    pic_lg.setIcon(imageIcon1);
                } else {
                    pic_lg.setIcon(defimg);
                }
                ois.close();
                //end of reading image
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
///load students resourse to combobox

    public void loadstudentviewCombo() {

        DefaultComboBoxModel dm = (DefaultComboBoxModel) viewcombo.getModel();
        viewcombo.removeAllItems();
        viewcombo.setFont(new Font("Tahoma", 0, 16));
        viewcombo.addItem("Select Name");
        try {
            con = Global.getConnection(con);
            st = con.createStatement();
            query = "SELECT NAME FROM " + Global.getStudentinfotable() + "";
            re = st.executeQuery(query);
            while (re.next()) {
                dm.addElement(re.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public void seteditable() {
        state = (!state);
        for (int i = 1; i < 14; i++) {
            output[i].setEditable(state);
            output[i].setOpaque(state);
        }
        aaddress.setEditable(state);
        aaddress.setOpaque(state);
        aremarks.setEditable(state);
        aremarks.setOpaque(state);
        sattach.setVisible(state);
        pattach.setVisible(state);
        lgattach.setVisible(state);

    }

    public void updatedatabse() {
        int d = -1;
        if (output[0].getText().equals("")) {
            return;
        }
        String name = output[1].getText().toString(), hid = output[0].getText().toString(), fname = output[5].getText().toString(), fmobile = output[7].getText().toString();
        if (name.length() > 0 && hid.length() > 0 && fname.length() > 0 && fmobile.length() > 0) {
            d = JOptionPane.showConfirmDialog(null, "Add this information");
            if (d == JOptionPane.YES_OPTION) {
                try {
                    con = Global.getConnection(con);

                    String query = "UPDATE " + Global.getStudentinfotable() + " SET "
                            + "NAME= ? ,MOBILE= ? ,CLASS= ? ,INSTITUTION = ? ,FNAME = ? , MNAME = ? ,PMOBILE = ? ,"
                            + "LGNAME = ?,LGMOBILE = ? ,RELATION = ? ,MONTHLYFEE = ? ,ROOM = ? ,REFERENCE = ? ,"
                            + "ADDRESS = ? ,REMARKS = ? ,PHOTO = ?,PPHOTO = ? ,LGPHOTO = ?"
                            + " WHERE HOSTELID = " + Integer.parseInt(output[0].getText()) + "";

                    pr = con.prepareStatement(query);
                    pr.setString(1, output[1].getText());
                    pr.setString(2, output[2].getText());
                    pr.setString(3, output[3].getText());
                    pr.setString(4, output[4].getText());
                    pr.setString(5, output[5].getText());
                    pr.setString(6, output[6].getText());
                    pr.setString(7, output[7].getText());
                    pr.setString(8, output[8].getText());
                    pr.setString(9, output[9].getText());
                    pr.setString(10, output[10].getText());
                    pr.setString(11, output[11].getText());
                    pr.setString(12, output[12].getText());
                    pr.setString(13, output[13].getText());
                    pr.setString(14, aaddress.getText());
                    pr.setString(15, aremarks.getText());
                    //GETTING TO SAVE THE IMAGE student
                    //GETTING TO SAVE THE IMAGE student
                    ImageIcon imgi = (ImageIcon) pic_student.getIcon();
                    Blob blob = con.createBlob();
                    try (ObjectOutputStream oos = new ObjectOutputStream(blob.setBinaryStream(1))) {
                        oos.writeObject(imgi);
                    }
                    pr.setBlob(16, blob);
                    //guardian
                    ImageIcon imgi1 = (ImageIcon) pic_parent.getIcon();
                    Blob blob1 = con.createBlob();
                    try (ObjectOutputStream oos1 = new ObjectOutputStream(blob1.setBinaryStream(1))) {
                        oos1.writeObject(imgi1);
                    }
                    pr.setBlob(17, blob1);

                    ImageIcon imgi2 = (ImageIcon) pic_lg.getIcon();
                    Blob blob2 = con.createBlob();
                    try (ObjectOutputStream oos2 = new ObjectOutputStream(blob2.setBinaryStream(1))) {
                        oos2.writeObject(imgi1);
                    }
                    pr.setBlob(18, blob2);
                    pr.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Value Updated Succesfully", "Succesful", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error Connecting to server\n" + ex);
                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(null, "Error Saving data to server\n" + ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, " Please Enter All * marked values ");
        }

    }

    public void geticon(JLabel lab) {
        JFileChooser chooser = new JFileChooser("C:?user/desktop");
        int ok = chooser.showOpenDialog(this);
        if (ok == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            ImageIcon img = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
            lab.setIcon(img);
            return;
        }
    }

    public void resett() {
        for (int i = 0; i < 14; i++) {
            output[i].setText("");
        }
        aremarks.setText("");
        aaddress.setText("");
        viewcombo.setSelectedIndex(0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_refresh)) {
            loadstudentviewCombo();
        } else if (e.getSource().equals(viewcombo)) {
            show_info();
        } else if (e.getSource().equals(update)) {
            save.setEnabled(true);
            update.setText("Cancel");
            seteditable();
        } else if (e.getSource().equals(save)) {
            save.setEnabled(false);
            update.setText("update");
            updatedatabse();
            seteditable();
        } else if (e.getSource().equals(delet)) {
            try {
                deletrecord();
            } catch (SQLException ex) {
                Logger.getLogger(Viewstudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource().equals(reset)) {
            resett();

        } else if (e.getSource().equals(sattach)) {
            geticon(pic_student);
        } else if (e.getSource().equals(pattach)) {
            geticon(pic_parent);
        } else if (e.getSource().equals(lgattach)) {
            geticon(pic_lg);
        }
    }

    private void deletrecord() throws SQLException {
        try {
            if (output[0].getText().equals(null)) {
                return;
            }
            int k = JOptionPane.showConfirmDialog(this, "Sure ?\nWant To delete This Record", "Warning", JOptionPane.WARNING_MESSAGE);
            if (k == JOptionPane.YES_OPTION) {
                con = Global.getConnection(con);
                query = "DELETE FROM " + Global.getStudentinfotable() + " WHERE HOSTELID = " + Integer.parseInt(output[0].getText()) + "";
                System.out.println(query);
                pr = con.prepareStatement(query);
                pr.executeUpdate();
                JOptionPane.showMessageDialog(this, "Succesfully Deleted", "Delete", JOptionPane.PLAIN_MESSAGE);
                loadstudentviewCombo();
                resett();
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Unexpected Error Occured");
        }
    }

}
