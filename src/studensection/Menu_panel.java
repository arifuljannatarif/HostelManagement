package studensection;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;
import java_project.LoginPage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

public class Menu_panel extends JPanel implements ActionListener {

    JPanel menu_panel;
    Dimension dm;
    ImageIcon imageIcon;
    JButton addb, viewb, payb, homeb, logout, viewstdcard, pre = new JButton();
    BorderLayout bl;
    Color bg = new Color(83, 132, 155);;

    public Menu_panel() {
        super();
        dm = new Dimension(100, 80);
        setLayout(new GridLayout());
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(getWidth(), 70));
        //addnewstudent button
        addb = new JButton("Add new Student");//"Add New Student"
        imageIcon = new ImageIcon(getClass().getResource("/icons/add_icon.png"));
        addb.setIcon(imageIcon);
        addb.setVerticalTextPosition(SwingConstants.BOTTOM);
        addb.setHorizontalTextPosition(SwingConstants.CENTER);
        //end
        //View student button
        viewb = new JButton("View Studet info");//"View Student Details"
        viewb.setIcon(new ImageIcon(getClass().getResource("/images/views.png")));
        viewb.setVerticalTextPosition(SwingConstants.BOTTOM);
        viewb.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        //View student button
        viewstdcard = new JButton("Student Card");//"View Student Details"
        viewstdcard.setIcon(new ImageIcon(getClass().getResource("/images/views.png")));
        viewstdcard.setVerticalTextPosition(SwingConstants.BOTTOM);
        viewstdcard.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        payb = new JButton("Payment");//"Payment"
        payb.setIcon(new ImageIcon(getClass().getResource("/images/creditcardl.png")));
        payb.setVerticalTextPosition(SwingConstants.BOTTOM);
        payb.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        //home button
        homeb = new JButton("Home");//"Home"
        homeb.setIcon(new ImageIcon(getClass().getResource("/images/homes.png")));
        homeb.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeb.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        //start
        logout = new JButton("LOgout");//"Add New Student"
        imageIcon = new ImageIcon(getClass().getResource("/icons/logout_icon_small.png"));
        logout.setIcon(imageIcon);
        logout.setVerticalTextPosition(SwingConstants.BOTTOM);
        logout.setHorizontalTextPosition(SwingConstants.CENTER);
        //end
        ///adding buttons
        add(homeb);
        add(addb);
        add(viewb);
        add(payb);
        add(viewstdcard);
        add(logout);
        //end 
        addb.setBackground(bg);
        homeb.setBackground(bg);
        viewb.setBackground(bg);
        logout.setBackground(bg);
        viewstdcard.setBackground(bg);
        payb.setBackground(bg);
        //adding click listener
        homeb.addActionListener(this);
        addb.addActionListener(this);
        viewb.addActionListener(this);
        payb.addActionListener(this);
        logout.addActionListener(this);
        viewstdcard.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addb)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "add_student");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (e.getSource().equals(homeb)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                br.getLayoutComponent(BorderLayout.NORTH).setVisible(false);
                pr.validate();
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "home_card");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (e.getSource().equals(viewb)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();

                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "view_student");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (e.getSource().equals(payb)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "student_payment");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (e.getSource().equals(viewstdcard)) {
            try {
                System.out.println("aise");
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "sudent_grid");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (e.getSource().equals(logout)) {
            ((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this)).setVisible(false);//.setState(JFrame.ICONIFIED);//.setVisible(false);
            new LoginPage().setVisible(true);
        }

    }

    
}
