package layout;

import com.sun.javafx.scene.control.skin.CustomColorDialog;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JPanel;

public class Dialogue extends Dialog {
    JPanel dpan;
    
    public Dialogue(Frame owner) throws InterruptedException {
        super(owner);
        
        dpan=new JPanel();
        setSize(300,300);
        setPreferredSize(new Dimension(200,200));
        setBackground(Color.yellow);
        add(dpan);
        setAlwaysOnTop(true);
       setModalityType(Dialog.ModalityType.MODELESS);
        setVisible(true);
        Thread.sleep(10000);
    }

}
