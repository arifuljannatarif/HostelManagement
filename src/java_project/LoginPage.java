package java_project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import layout.updatedbpanel;

public class LoginPage extends JFrame implements ActionListener, MouseListener {
    
    JLabel back, l1, l2, l3;
    JButton btn_login;
    JLabel dsetup;
    JTextField username, password;
    JPanel xp;
    int y = 150, x = -25;
    Timer t;
    JProgressBar progressBar;
    
    public LoginPage() {
        super("Login");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        ImageIcon img = new ImageIcon(getClass().getResource("/images/login_background.jpg"));
        setIconImage(new ImageIcon(getClass().getResource("/icons/icon_main_app.png")).getImage());
        back = new JLabel(img);
        back.setLayout(new GridBagLayout());
        xp = new JPanel(null);
        xp.setPreferredSize(new Dimension(300, 400));
        xp.setBackground(new Color(241, 230, 255, 80));
        username = new JTextField("Admin");
        username.setBounds(x + 90, y + 5, 180, 30);
        password = new JTextField();
        password.setBounds(x + 90, y + 70, 180, 30);
        l1 = new JLabel("Username : ");
        l1.setForeground(Color.WHITE);
        l1.setBounds(x + 90, y - 30, 70, 30);
        l2 = new JLabel("Password : ");
        l2.setForeground(Color.WHITE);
        l2.setBounds(x + 90, y + 40, 70, 30);
        l3 = new JLabel();
        l3.setForeground(Color.WHITE);
        l3.setBounds(x + 90, y - 90, 200, 30);
        btn_login = new JButton("login");
        btn_login.setBounds(x + 130, y + 110, 100, 30);
        btn_login.setBackground(new Color(151, 255, 142));
        btn_login.setIcon(new ImageIcon(getClass().getResource("/icons/icon_lock_small.png")));
        btn_login.addActionListener(this);
        btn_login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dsetup = new JLabel("<HTML><U>Update db Property</U></HTML>");
        dsetup.setOpaque(false);
        dsetup.setBounds(x + 170, y + 150, 140, 30);
        dsetup.setBackground(new Color(220, 230, 255, 90));
        dsetup.setForeground(Color.WHITE);
        dsetup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dsetup.addMouseListener(this);
        //----
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        progressBar.setBounds(x + 30, y - 90, 280, 30);
        //----
        
        xp.add(progressBar);
        xp.add(username);
        xp.add(password);
        xp.add(l1);
        xp.add(l2);
        xp.add(l3);
        xp.add(btn_login);
        xp.add(dsetup);
        back.add(xp);
        add(back);
        
    }
    Connection con;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_login)) {
            if (!username.getText().equals("Admin") || !password.getText().equals("1234")) {
                l3.setText("Wrong username or password");
                repaint();
                return;
            }
            try {
                l3.setText("");
                progressBar.setVisible(true);
                startprogress();
                Global.getConnection(con);
                if (Global.cnct) {
                    new InitializeDatabase();
                } else {
                    l3.setText("Failed to connect database");
                    progressBar.setVisible(false);
                    repaint();
                    return;
                }
                if (InitializeDatabase.ok) {
                    this.dispose();
                    new Mainframe().setVisible(true);
                } else {
                    l2.setVisible(true);
                    
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public static void main(String[] args) {
        try {
            // UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        new Global();
        
        new LoginPage().setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(dsetup)) {
            getRootPane().setGlassPane(new updatedbpanel());
            getRootPane().getGlassPane().setVisible(true);
        }
    }
    
    public void startprogress() {
        t = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar.setString("Loading " + progressBar.getValue() + "%");
                progressBar.setValue(progressBar.getValue() + 5);
            }
        });
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
