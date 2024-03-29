package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.prefs.Preferences;
import java_project.Global;
import java_project.LoginPage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;

public class updatedbpanel extends JPanel implements ActionListener, MouseListener {

    JProgressBar progressBar;
    Timer t;
    JLabel l1, l2, l3, l4;
    JTextField durl, dusername, dpass;
    JButton save, def, cncl;
    GridBagConstraints gb;

    public updatedbpanel() {
        super();
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 120));

        durl = new JTextField();
        durl.setPreferredSize(new Dimension(200, 30));
        dpass = new JTextField();
        dpass.setPreferredSize(new Dimension(200, 30));
        dusername = new JTextField();
        dusername.setPreferredSize(new Dimension(200, 30));
        l1 = new JLabel("Database Url : ");
        l1.setForeground(Color.WHITE);
        l1.setPreferredSize(new Dimension(130, 30));
        l2 = new JLabel("Database Username : ");
        l2.setForeground(Color.WHITE);
        l2.setPreferredSize(new Dimension(130, 30));
        l3 = new JLabel("Database Password : ");
        l3.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setPreferredSize(new Dimension(130, 30));
        save = new JButton(new ImageIcon(getClass().getResource("/icons/save_icon.png")));
        save.setPreferredSize(new Dimension(200, 30));
        save.setText("Save");
        def = new JButton("Load Default");
        def.setPreferredSize(new Dimension(200, 30));
        cncl = new JButton("Cancel");
        cncl.setPreferredSize(new Dimension(200, 30));
        gb = new GridBagConstraints();
        gb.insets = new Insets(5, 5, 5, 5);
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setFont(new Font("Tahoma", Font.PLAIN, 23));
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(600, 60));
        progressBar.setForeground(new Color(120, 140, 45, 70));
        //add(progressBar, gb);
        //labels
        gb.gridx = 0;
        gb.gridy = 1;
        add(l1, gb);
        gb.gridx = 0;
        gb.gridy = 2;
        add(l2, gb);
        gb.gridx = 0;
        gb.gridy = 3;
        add(l3, gb);
        //textrfield
        gb.gridx = 1;
        gb.gridy = 1;
        add(durl, gb);
        gb.gridx = 1;
        gb.gridy = 2;
        add(dusername, gb);
        gb.gridx = 1;
        gb.gridy = 3;
        add(dpass, gb);

        gb.gridx = 1;
        gb.gridy = 4;
        add(save, gb);
        save.addActionListener(this);

        gb.gridx = 1;
        gb.gridy = 5;
        add(def, gb);
        def.addActionListener(this);

        gb.gridx = 1;
        gb.gridy = 6;
        add(cncl, gb);
        cncl.addActionListener(this);
        addMouseListener(this);
        save.addActionListener(this);
        def.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(save)) {
            try {
                Preferences prefs = Preferences.userNodeForPackage(LoginPage.class);
                prefs.put("databaseurl", durl.getText());
                prefs.put("databasepass", dpass.getText());
                prefs.put("databaseusername", dusername.getText());
                getRootPane().getGlassPane().setVisible(false);
                Global.loadpref();
            } catch (Exception df) {
                System.out.println("reding pref " + df);
            }
        } else if (e.getSource().equals(def)) {
            try {
                Preferences prefs = Preferences.userNodeForPackage(LoginPage.class);
                durl.setText(prefs.get("default", "jdbc:mysql://localhost/mydatabase"));
                dpass.setText(prefs.get("default", ""));
                dusername.setText(prefs.get("default", "root"));

            } catch (Exception df) {
                System.out.println("reading pref " + df);
            }
        } else {
            getRootPane().getGlassPane().setVisible(false);
        }
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

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
