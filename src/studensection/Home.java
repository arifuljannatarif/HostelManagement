package studensection;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Home extends JPanel implements ActionListener {
    
    JButton hb, vb, pb;
    Font fnt = new Font("Tahoma", Font.BOLD, 16);
    JPanel hpanel;
    JLabel homebackground;
    GridBagConstraints gbc = new GridBagConstraints();
    
    public Home() {
        super();
        setLayout(new GridLayout());
        homebackground = new JLabel(new ImageIcon(getClass().getResource("/images/page_background.jpg")));
        homebackground.setLayout(new GridLayout());
        hpanel = new JPanel();
        hpanel.setBackground(new Color(239, 255, 188));
        hpanel.setLayout(new GridBagLayout());
        //add button
        hb = new JButton("Add new Student");
        hb.setOpaque(true);
        hb.setFont(fnt);
        hb.setPreferredSize(new Dimension(150, 150));
        hb.setIcon(new ImageIcon(getClass().getResource("/images/addm.png")));
        hb.setVerticalTextPosition(SwingConstants.BOTTOM);
        hb.setHorizontalTextPosition(SwingConstants.CENTER);
        hpanel.add(hb);
        hb.addActionListener(this);
        vb = new JButton("View Student Details");
        vb.setFont(fnt);
        vb.setPreferredSize(new Dimension(150, 150));
        vb.setIcon(new ImageIcon(getClass().getResource("/images/viewl.png")));
        vb.setVerticalTextPosition(SwingConstants.BOTTOM);
        vb.setHorizontalTextPosition(SwingConstants.CENTER);
        hpanel.add(vb);
        vb.addActionListener(this);
        //
        pb = new JButton("Payment");
        pb.setFont(fnt);
        pb.setIcon(new ImageIcon(getClass().getResource("/images/creditcardl.png")));
        pb.setVerticalTextPosition(SwingConstants.BOTTOM);
        pb.setPreferredSize(new Dimension(150, 150));
        pb.setHorizontalTextPosition(SwingConstants.CENTER);
        pb.addActionListener(this);
        hpanel.add(pb);
        hpanel.setOpaque(true);
        homebackground.add(hpanel);
        add(homebackground);
        hb.addActionListener(this);
        setVisible(true);
    }
    
    public void addb() {
        try {
            JPanel pr = (JPanel) this.getParent().getParent();
            BorderLayout br1, br = (BorderLayout) pr.getLayout();
//                JScrollPane jcp = (JScrollPane) br.getLayoutComponent(BorderLayout.CENTER);
//                JViewport jv = jcp.getViewport();
            JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
            br.getLayoutComponent(BorderLayout.NORTH).setVisible(true);
            pr.validate();
            CardLayout cl = (CardLayout) jp.getLayout();
            cl.show(jp, "add_student");
        } catch (Exception ds) {
            System.out.println(ds);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(hb)) {
            addb();
        } else if (e.getSource().equals(vb)) {
            try {
                JPanel pr = (JPanel) this.getParent().getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
//                JScrollPane jcp = (JScrollPane) br.getLayoutComponent(BorderLayout.CENTER);
//                JViewport jv = jcp.getViewport();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                br.getLayoutComponent(BorderLayout.NORTH).setVisible(true);
                br1 = (BorderLayout) pr.getParent().getLayout();
                pr.validate();
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "view_student");
            } catch (Exception ds) {
                
                System.out.println(ds);
            }
        } else {
            try {
                JPanel pr = (JPanel) this.getParent().getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
//                JScrollPane jcp = (JScrollPane) br.getLayoutComponent(BorderLayout.CENTER);
//                JViewport jv = jcp.getViewport();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                br.getLayoutComponent(BorderLayout.NORTH).setVisible(true);
                //br1 = (BorderLayout) pr.getParent().getLayout();;
                pr.validate();
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "student_payment");
            } catch (Exception ds) {
                
                System.out.println(ds);
            }
        }
    }
    
}
