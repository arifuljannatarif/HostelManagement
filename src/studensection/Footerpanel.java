package studensection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.DatePicker;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Footerpanel extends JPanel implements ActionListener {

    JButton save, update, reset, query;
    int btnh = 30, btnw = 100;
  JPanel footer;
    public Footerpanel() {
        super();
        footer=new JPanel();
        ImageIcon img;
        footer.setBackground(Color.WHITE);
        reset = new JButton("Reset");
        img = new ImageIcon(getClass().getResource("/icons/logout_icon_small.png"));
        reset.setIcon(img);
        reset.setPreferredSize(new Dimension(btnw, btnh));
        query = new JButton("Query");
        img = new ImageIcon(getClass().getResource("/icons/search_icon_small.png"));
        query.setIcon(img);
        query.setPreferredSize(new Dimension(btnw, btnh));
        update = new JButton("Update");
        img = new ImageIcon(getClass().getResource("/icons/edit_icon_small.gif"));
        update.setIcon(img);
        update.setPreferredSize(new Dimension(btnw, btnh));
        save = new JButton("Save");
        img = new ImageIcon(getClass().getResource("/icons/save-icon.png"));
        save.setIcon(img);
        save.setPreferredSize(new Dimension(btnw, btnh));
        footer.setLayout(new FlowLayout(FlowLayout.TRAILING, 15, 0));
        reset.addActionListener(this);
      footer. add (reset);
      footer. add(query);
      footer. add(update);
      footer. add(save);
      add(footer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
