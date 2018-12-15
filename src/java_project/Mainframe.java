package java_project;

import accountssection.Accounts_menu;
import accountssection.Employee_statement;
import accountssection.Food_expense;
import accountssection.Other_expense;
import accountssection.Salary_payment;
import studensection.Addnewstudent;
import studensection.Logopan;
import studensection.Menu_panel;
import studensection.Student_Payment;
import studensection.ViewStudentGrid;
import studensection.Viewstudent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import layout.Loadingview;

public class Mainframe extends JFrame implements ActionListener {

    JPanel panel_main, panel_menu, panel_mainarea, panel_card, home;
    JLabel homebackground;

    public Mainframe() {
        super("Hostel Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setSize(1300, 700);
        setIconImage(new ImageIcon(getClass().getResource("/icons/icon_main_app.png")).getImage());
        setMinimumSize(new Dimension(1300, 700));
        setLocationRelativeTo(null);
        add(new Logopan(), BorderLayout.NORTH);
        panel_card = new JPanel(new CardLayout());
        panel_card.setPreferredSize(new Dimension(500, 1250));
        panel_mainarea = new JPanel();
        add(panel_mainarea, BorderLayout.CENTER);// JPanel
        panel_mainarea.setLayout(new BorderLayout());
        makeHome();
        panel_mainarea.add(homebackground, BorderLayout.CENTER);
        add(panel_mainarea);
        //loadaccountssection();
        new Global();
        add(new JButton(), BorderLayout.EAST);
        add(new JButton(), BorderLayout.WEST);
    }

    public void loadstudentsection() {
        panel_mainarea.removeAll();
        getRootPane().setGlassPane(new Loadingview());
        getRootPane().getGlassPane().setVisible(true);
        panel_menu = new Menu_panel();
        panel_card.removeAll();
        panel_mainarea.add(panel_menu, BorderLayout.NORTH);
        panel_card.setPreferredSize(new Dimension(500, 1250));
        panel_card.add(new Addnewstudent(), "add_student");
        panel_card.add(new Student_Payment(), "student_payment");
        panel_card.add(homebackground, "home_card");
        panel_card.add(new ViewStudentGrid(), "sudent_grid");
        panel_card.add(new Viewstudent(), "view_student");
        panel_mainarea.add(panel_card, BorderLayout.CENTER);

    }

    public void loadaccountssection() {
        panel_mainarea.removeAll();
        getRootPane().setGlassPane(new Loadingview());
        getRootPane().getGlassPane().setVisible(true);
        panel_menu = new Accounts_menu();
        panel_card.removeAll();
        panel_mainarea.add(panel_menu, BorderLayout.NORTH);
        panel_card.setPreferredSize(new Dimension(500, 1250));//check
        panel_card.add(new Food_expense(), "food_expense");
        panel_card.add(new Other_expense(), "other_expense");
        panel_card.add(homebackground, "home_card");
        panel_card.add(new Salary_payment(), "salary_expense");
        panel_card.add(new Employee_statement(), "employee_statement");
        panel_mainarea.add(panel_card, BorderLayout.CENTER);
    }
    JButton hb, vb, pb;

    public void makeHome() {
        homebackground = new JLabel(new ImageIcon(getClass().getResource("/images/page_background.jpg")));
        homebackground.setLayout(new GridLayout());
        home = new JPanel(new GridBagLayout());
        Font fnt = new Font("Tahoma", Font.BOLD, 16);
        home.setBackground(new Color(239, 255, 188));
        //add button
        hb = new JButton("Accounts Section");
        hb.setOpaque(false);
        hb.setFont(fnt);
        hb.setPreferredSize(new Dimension(200, 200));
        hb.setIcon(new ImageIcon(getClass().getResource("/icons/icon_account.png")));
        hb.setVerticalTextPosition(SwingConstants.BOTTOM);
        hb.setHorizontalTextPosition(SwingConstants.CENTER);
        home.add(hb);
        hb.addActionListener(this);
        vb = new JButton("Student Section");
        vb.setFont(fnt);
        vb.setPreferredSize(new Dimension(200, 200));
        vb.setIcon(new ImageIcon(getClass().getResource("/icons/icon_student_home.png")));
        vb.setVerticalTextPosition(SwingConstants.BOTTOM);
        vb.setHorizontalTextPosition(SwingConstants.CENTER);
        home.add(vb);
        home.setOpaque(false);
        homebackground.add(home);
        vb.addActionListener((ActionListener) this);
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        new InitializeDatabase();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(hb)) {
            this.loadaccountssection();
        } else {
            this.loadstudentsection();
        }
    }

}
