package studensection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java_project.Global;
import java_project.V;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Addnewstudent extends JPanel implements ActionListener {

    Connection con;
    Statement st;
    String query = "";
    JButton save, update, reset, qry;
    int btnh = 30, btnw = 100;
    String[] arr = {"Hostel Id: ", "Name : ", "Mobile : ", "Class : ",
        "Institute : ", "Fathers Name :", "Mothers Name :", "Contact No", "Address :",
        "Name ", "Contact NO ", "Relation ", "Monthly fee", "Alloted Room", "Refference ", "Remarks"};
    JPanel span, ppan, lgpan, opan, main, footer;
    boolean opaque = false, editable = true;
    JLabel pic_student, pic_parent, pic_lg, jlabel;

    JButton btn_generate;
    JTextArea aaddress, aremarks;
    JTextField[] input = new JTextField[30];
    JLabel[] label = new JLabel[30];
    int photoh = 200, photow = 180, lh = 20, lw = 90, jth = 30, jtw = 300;
    Color background = new Color(200, 220, 229);
    JButton sattach, pattach, lgattach;
 Border photoborder=BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.black,2)
            ,BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    public Addnewstudent() {
        super();
        main = new JPanel(new GridLayout(2, 2));
        setBackground(background);
        main.setOpaque(!opaque);
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

        save.addActionListener(this);
        add(main, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        btn_generate.addActionListener(this);
         pic_student.setBorder(photoborder);
        pic_parent.setBorder(photoborder);
        pic_lg.setBorder(photoborder);
    }

    public void savedtodatabse() {
        int d = -1;
        String name = input[1].getText().toString(), hid = input[0].getText().toString(), fname = input[5].getText().toString(), fmobile = input[7].getText().toString();
        if (name.length() > 0 && hid.length() > 0 && fname.length() > 0 && fmobile.length() > 0 || true) {

            d = JOptionPane.showConfirmDialog(null, "Add this information");
            if (d == JOptionPane.YES_OPTION) {
                try {
                    con = Global.getConnection(con);
                    st = con.createStatement();
                    String query = "insert into " + Global.getStudentinfotable() + " ("
                            + "HOSTELID,NAME,MOBILE,CLASS,INSTITUTION,FNAME,MNAME,PMOBILE,"
                            + "LGNAME,LGMOBILE,RELATION,MONTHLYFEE,ROOM,REFERENCE,"
                            + "ADDRESS,REMARKS,PHOTO,PPHOTO,LGPHOTO"
                            + ")VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pr = con.prepareStatement(query);
                    pr.setString(1, input[0].getText());
                    pr.setString(2, input[1].getText());
                    pr.setString(3, input[2].getText());
                    pr.setString(4, input[3].getText());
                    pr.setString(5, input[4].getText());
                    pr.setString(6, input[5].getText());
                    pr.setString(7, input[6].getText());
                    pr.setString(8, input[7].getText());
                    pr.setString(9, input[8].getText());
                    pr.setString(10, input[9].getText());
                    pr.setString(11, input[10].getText());
                    pr.setString(12, input[11].getText());
                    pr.setString(13, input[12].getText());
                    pr.setString(14, input[13].getText());
                    pr.setString(15, aaddress.getText());
                    pr.setString(16, aremarks.getText());
                    //GETTING TO SAVE THE IMAGE student
                    ImageIcon imgi = (ImageIcon) pic_student.getIcon();
                    Blob blob = con.createBlob();
                    try (ObjectOutputStream oos = new ObjectOutputStream(blob.setBinaryStream(1))) {
                        oos.writeObject(imgi);
                    }
                    pr.setBlob(17, blob);
                    //guardian
                   ImageIcon imgi1 = (ImageIcon) pic_parent.getIcon();
                    Blob blob1 = con.createBlob();
                    try (ObjectOutputStream oos1 = new ObjectOutputStream(blob1.setBinaryStream(1))) {
                        oos1.writeObject(imgi1);
                    }
                    pr.setBlob(18, blob1);
                    
                   ImageIcon imgi2 = (ImageIcon) pic_lg.getIcon();
                    Blob blob2= con.createBlob();
                    try (ObjectOutputStream oos2 = new ObjectOutputStream(blob2.setBinaryStream(1))) {
                        oos2.writeObject(imgi1);
                    }
                    pr.setBlob(19, blob2);
                    ///LOXAL 
                    
                    ///END OF IMAGE PROCESSING
                    pr.execute();
                    JOptionPane.showMessageDialog(null, "New Record Added", "Succesful", JOptionPane.INFORMATION_MESSAGE);
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

    public void makespan() {
        span = new JPanel();
        span.setOpaque(opaque);
        span.setPreferredSize(new Dimension(600, 300));
        span.setBorder(BorderFactory.createTitledBorder("Student Info"));
        span.setLayout(null);
        int x = 10, y = 30 + jth, x1 = 90, y1 = 55, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;
        btn_generate = new JButton("Generate");
        btn_generate.setBounds(230, 20, jtw / 2, jth);
        span.add(btn_generate);
        input[0] = new JTextField();
        input[0].setEditable(false);
        input[0].setBounds(80, 20, jtw / 2, jth);
        span.add(input[0]);
        jlabel = new JLabel(arr[0]);
        jlabel.setBounds(10, 20, sw1, sh1);
        span.add(jlabel);
        for (int i = 1; i < 5; i++) {
            jlabel = new JLabel(arr[i]);
            jlabel.setBounds(10, y, sw1, sh1);
            span.add(jlabel);
            input[i] = new JTextField();
            input[i].setBounds(80, y1, sw, sh);
            input[i].setFont(V.inputfnt);
            input[i].setEditable(editable);
            span.add(input[i]);
            span.add(jlabel);
            y1 += 45;
            y += 45;
        }
        pic_student = new JLabel();
        ImageIcon img = new ImageIcon(new ImageIcon(getClass()
                .getResource("/images/student_avatar.png")).getImage().getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
        pic_student.setBounds(420, 20, photow, photoh);
        pic_student.setIcon(img);
        span.add(pic_student);
        span.setSize(550, 550);
        img = new ImageIcon(getClass().getResource("/icons/icon_attach_small.png"));
        sattach = new JButton(img);
        sattach.setOpaque(false);
        sattach.setBounds(390, 20, 25, 25);
        span.add(sattach);
        sattach.addActionListener(this);
        //  span.setBackground(Color.red);

    }

    public void makeppan() {
        ppan = new JPanel();
        ppan.setPreferredSize(new Dimension(600, 300));
        ppan.setBorder(BorderFactory.createTitledBorder("Guardian Info"));
        ppan.setLayout(null);
        ppan.setOpaque(opaque);
        int x = 10, y = 25, x1 = 100, y1 = 20, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;
        for (int i = 5; i < 8; i++) {
            input[i] = new JTextField();
            input[i].setFont(V.inputfnt);
            input[i].setBounds(x1, y1, sw, sh);
            input[i].setFont(V.inputfnt);
            input[i].setEditable(editable);
            jlabel = new JLabel(arr[i]);
            jlabel.setBounds(10, y, sw1, sh1);
            ppan.add(jlabel);
            ppan.add(input[i]);
            y1 += 45;
            y += 45;
        }
        jlabel = new JLabel(arr[8]);
        jlabel.setBounds(10, y, sw1, sh1);
        ppan.add(jlabel);
        aaddress = new JTextArea();
        aaddress.setFont(V.inputfnt);
        aaddress.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aaddress.setLineWrap(true);
        aaddress.setBounds(x1, y1, sw, sh + 50);
        ppan.add(aaddress);
        pic_parent = new JLabel();
        ImageIcon img = new ImageIcon(new ImageIcon(getClass()
                .getResource("/images/parent_avatar.png")).getImage()
                .getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
        pic_parent.setBounds(430, 20, photow, photoh);
        pic_parent.setIcon(img);
        ppan.add(pic_parent);
        img = new ImageIcon(getClass().getResource("/icons/icon_attach_small.png"));
        pattach = new JButton(img);
        pattach.setOpaque(false);
        pattach.setBounds(402, 20, 25, 25);
        ppan.add(pattach);
        pattach.addActionListener(this);

    }

    public void makelgpan() {
        lgpan = new JPanel();
        lgpan.setPreferredSize(new Dimension(600, 300));
        lgpan.setBorder(BorderFactory.createTitledBorder("Local Gardian info"));
        lgpan.setLayout(null);
        lgpan.setOpaque(opaque);
        int x = 10, y = 30, x1 = 90, y1 = 20, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;
        for (int i = 8; i < 11; i++) {
            jlabel = new JLabel(arr[i + 1]);
            input[i] = new JTextField();
            input[i].setBounds(80, y1, sw, sh);
            input[i].setEditable(editable);
            input[i].setFont(V.inputfnt);
            jlabel.setBounds(10, y, sw1, sh1);
            lgpan.add(input[i]);
            lgpan.add(jlabel);
            y1 += 45;
            y += 45;
        }
        pic_lg = new JLabel();
        ImageIcon img = new ImageIcon(new ImageIcon(getClass()
                .getResource("/images/lg_avatar.png")).getImage().getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
        pic_lg.setBounds(420, 20, photow, photoh);
        pic_lg.setIcon(img);
        lgpan.add(pic_lg);
        lgpan.setSize(550, 550);
        img = new ImageIcon(getClass().getResource("/icons/icon_attach_small.png"));
        lgattach = new JButton(img);
        lgattach.setOpaque(false);
        lgattach.setBounds(390, 20, 25, 25);
        lgpan.add(lgattach);
        lgattach.addActionListener(this);

    }

    public void makeopan() {
        opan = new JPanel();
        opan.setPreferredSize(new Dimension(600, 300));
        opan.setBorder(BorderFactory.createTitledBorder("Office Section"));
        opan.setLayout(null);
        opan.setOpaque(opaque);
        int x = 10, y = 30, x1 = 90, y1 = 20, sh = jth, sw = jtw, sh1 = lh, sw1 = lw;

        for (int i = 11; i < 14; i++) {
            jlabel = new JLabel(arr[i + 1]);
            input[i] = new JTextField();
            input[i].setBounds(80 + 10, y1, sw, sh);
            input[i].setEditable(editable);
            input[i].setFont(V.inputfnt);
            jlabel.setBounds(10, y, sw1 + 5, sh1);
            opan.add(input[i]);
            opan.add(jlabel);
            y1 += 45;
            y += 45;
        }
        jlabel = new JLabel(arr[15]);
        aremarks = new JTextArea();
        aremarks.setBounds(80 + 10, y1, sw, sh + 50);
        aremarks.setEditable(editable);
        aremarks.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aremarks.setOpaque(true);
        aremarks.setLineWrap(true);
        aremarks.setFont(V.inputfnt);
        jlabel.setBounds(10, y, sw1 + 5, sh1);
        opan.add(aremarks);
        opan.add(jlabel);
        opan.setSize(550, 550);

    }

    public void makefooter() {
        footer = new JPanel();
        footer.setPreferredSize(new Dimension(getWidth(), 35));
        footer.setBackground(new Color(181, 205, 228));
        reset = new JButton("Reset");
        ImageIcon img = new ImageIcon(getClass().getResource("/icons/logout_icon_small.png"));
        reset.setIcon(img);
        reset.setPreferredSize(new Dimension(btnw, btnh));
        save = new JButton("Save");
        img = new ImageIcon(getClass().getResource("/icons/save_icon.png"));
        save.setIcon(img);
        save.setPreferredSize(new Dimension(btnw, btnh));
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        reset.addActionListener(this);
        footer.add(reset);
        footer.add(save);
    }
File lastpath=null;

    public void geticon(JLabel lab) {
        JFileChooser chooser = new JFileChooser(lastpath);
//        chooser.setCurrentDirectory(new File  
//(System.getProperty("user.home") + System.getProperty("file.separator")+ "Music"));
        int ok = chooser.showOpenDialog(this);
        if (ok == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            ImageIcon img = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(photow, photoh, Image.SCALE_SMOOTH));
            lab.setIcon(img);
            lastpath=chooser.getCurrentDirectory();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_generate) {

            try {
                con = Global.getConnection(con);
                st = con.createStatement();
                String qq = "SELECT MAX(HOSTELID) FROM " + Global.studentinfotable + "";
                ResultSet re = st.executeQuery(qq);
                try {
                    int k;
                    if (re.next()) {
                        k = Integer.parseInt(re.getString(1));
                    } else {
                        k = 1000;
                    }
                    input[0].setText(Integer.toString(k + 1));

                } catch (Exception ed) {
                    input[0].setText("1000");
                }
            } catch (SQLException ex) {
                System.out.println(e);
            }
        } else if (e.getSource().equals(save)) {
            savedtodatabse();
        } else if (e.getSource().equals(sattach)) {
            geticon(pic_student);
        } else if (e.getSource().equals(pattach)) {
            geticon(pic_parent);
        } else if (e.getSource().equals(lgattach)) {
            geticon(pic_lg);
        }
        else if(e.getSource().equals(reset)){
            resetfield();
        }

    }
    private void resetfield(){
        for(int i=0;i<14;i++)input[i].setText("");aaddress.setText("");aremarks.setText("");
    }

}


