package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Loadingview extends JPanel {

    JProgressBar progressBar;
    Timer t;

    public Loadingview() {
        super();
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 120));
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setFont(new Font("Tahoma", Font.PLAIN, 23));
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(600, 60));
        progressBar.setForeground(new Color(120, 140, 45, 70));
        add(progressBar);
        startclock();
    }

    public void startclock() {
        t = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //progressBar.setString(val[i++ % 3] + " \n " + progressBar.getValue() + "%");
                progressBar.setValue(progressBar.getValue() + 10);
                progressBar.setString("Loading " + progressBar.getValue() + "%");
                if (progressBar.getValue() == 100) {
                    try {
                       getRootPane().getGlassPane().setVisible(false);
                    } catch (Exception ez) {
                        System.out.println("rootpne is null");
                    }
                    t.stop();
                }
            }
        });
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
    }

}
