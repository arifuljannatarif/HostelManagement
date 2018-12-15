package studensection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Logopan extends JPanel {

    public Logopan() {
        super();
        setLayout(new FlowLayout());
        setBackground(new Color(204, 153, 255));
        setPreferredSize(new Dimension(getWidth(),90));
        ImageIcon img
                = new ImageIcon(new ImageIcon(getClass()
                        .getResource("/images/viewl.png")).getImage().
                        getScaledInstance(60, 60, Image.SCALE_DEFAULT));
           
        JLabel nam,ic;
        nam=new JLabel("Scholars Residence");
        nam.setFont(new Font("Tahoma",Font.BOLD,50));
        ic=new JLabel(img);
      add(ic);  add(nam);
        

    }

}
