package accountssection;

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
public class Accounts_menu extends JPanel implements ActionListener, MouseListener {
    JPanel menu_panel;
    Dimension dm;
    ImageIcon imageIcon;
    JButton menu_food_expense, menu_other_expense, menu_salary_expense, homeb, logout, menu_employee_statement, pre = new JButton();
    BorderLayout bl;
    Color bg = new Color(83, 132, 155);

    public Accounts_menu() {
        super();
        dm = new Dimension(100, 80);
        setLayout(new GridLayout());
        ////setBorder(new BevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(getWidth(), 70));
        //addnewstudent button
        menu_food_expense = new JButton("Food Expense");//"Add New Student"
        imageIcon = new ImageIcon(getClass().getResource("/icons/icon_food_expense.png"));
        menu_food_expense.setIcon(imageIcon);
        menu_food_expense.setVerticalTextPosition(SwingConstants.BOTTOM);
        menu_food_expense.setHorizontalTextPosition(SwingConstants.CENTER);
        //end
        //View student button
        menu_other_expense = new JButton("Other Expense");//"View Student Details"
        menu_other_expense.setIcon(new ImageIcon(getClass().getResource("/icons/icon_other_expense.png")));
        menu_other_expense.setVerticalTextPosition(SwingConstants.BOTTOM);
        menu_other_expense.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        //View student button
        menu_employee_statement = new JButton("Employee Statement");//"View Student Details"
        menu_employee_statement.setIcon(new ImageIcon(getClass().getResource("/icons/icon_employee_statement.png")));
        menu_employee_statement.setVerticalTextPosition(SwingConstants.BOTTOM);
        menu_employee_statement.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        menu_salary_expense = new JButton("Salary Payment");//"Payment"
        menu_salary_expense.setIcon(new ImageIcon(getClass().getResource("/images/creditcardl.png")));
        menu_salary_expense.setVerticalTextPosition(SwingConstants.BOTTOM);
        menu_salary_expense.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        //home button
        homeb = new JButton("Home");//"Home"
        homeb.setIcon(new ImageIcon(getClass().getResource("/images/homes.png")));
        homeb.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeb.setHorizontalTextPosition(SwingConstants.CENTER);

        //end
        //start
        logout = new JButton("");//"Add New Student"
        imageIcon = new ImageIcon(getClass().getResource("/icons/logout_icon_small.png"));
        logout.setIcon(imageIcon);
        logout.setVerticalTextPosition(SwingConstants.BOTTOM);
        logout.setHorizontalTextPosition(SwingConstants.CENTER);
        //end
        ///adding buttons
        add(homeb);
        add(menu_food_expense);
        add(menu_other_expense);
        add(menu_salary_expense);
        add(menu_employee_statement);
        add(logout);
        //end 
        menu_food_expense.setBackground(bg);
        homeb.setBackground(bg);
        menu_other_expense.setBackground(bg);
        logout.setBackground(bg);
        menu_employee_statement.setBackground(bg);
        menu_salary_expense.setBackground(bg);
        //adding click listener
        homeb.addActionListener(this);
        menu_food_expense.addActionListener(this);
        menu_other_expense.addActionListener(this);
        menu_salary_expense.addActionListener(this);
        logout.addActionListener(this);
        menu_employee_statement.addActionListener(this);
        //end

        //adding mouse listener
        //end
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton press = (JButton) e.getSource();

        if (press.equals(menu_food_expense)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "food_expense");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (press.equals(homeb)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                br.getLayoutComponent(BorderLayout.NORTH).setVisible(false);
                pr.validate();
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "home_card");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (press.equals(menu_other_expense)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();

                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "other_expense");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (press.equals(menu_salary_expense)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "salary_expense");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (press.equals(menu_employee_statement)) {
            try {
                JPanel pr = (JPanel) this.getParent();
                BorderLayout br1, br = (BorderLayout) pr.getLayout();
                JPanel jp = (JPanel) br.getLayoutComponent(BorderLayout.CENTER);
                CardLayout cl = (CardLayout) jp.getLayout();
                cl.show(jp, "employee_statement");
            } catch (Exception ds) {
                System.out.println(ds);
            }
        } else if (press.equals(logout)) {
            ((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this)).setVisible(false);//.setState(JFrame.ICONIFIED);//.setVisible(false);
            new LoginPage().setVisible(true);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setPreferredSize(new Dimension(getWidth(), 70));
        menu_food_expense.setText("Food Expense");
        menu_other_expense.setText("Other Expense");
        menu_salary_expense.setText("Salary Payment");
        homeb.setText("Home");
        menu_employee_statement.setText("Employee Statement");
        logout.setText("logout");
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
