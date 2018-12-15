package studensection;

import layout.WrapLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java_project.Global;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class ViewStudentGrid extends JPanel implements AncestorListener {

    JComboBox gridsearch;
    Connection con;
    Statement st;
    PreparedStatement pr;
    ResultSet re;
    String query;
    JPanel pan;
    JScrollPane jc;
    int c = 0;
    Color background = new Color(0, 105, 148);

    public ViewStudentGrid() {
        super();
        pan = new JPanel(new WrapLayout(WrapLayout.CENTER, 5, 5));
        pan.setBackground(background);
        setLayout(new GridLayout());
        jc = new JScrollPane(pan);
        addAncestorListener((AncestorListener) this);
        add(jc);
        loadcard();
    }

    public void loadcard() {
        try {
            pan.removeAll();
            con = Global.getConnection(con);
            st = con.createStatement();
            String query = "Select * from " + Global.getStudentinfotable() + " Where 1";
            re = st.executeQuery(query);
            while (re.next()) {
                Blob blobp = re.getBlob("PHOTO");
                if (blobp == null) {
                    pan.add(new StudentRow(re.getString("NAME"), re.getString("FNAME"), re.getString("MNAME"), re.getString("Mobile"), null));
                    continue;
                }
                ObjectInputStream oisp = new ObjectInputStream(blobp.getBinaryStream());
                ImageIcon icon = new ImageIcon(((ImageIcon) oisp.readObject()).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
                pan.add(new StudentRow(re.getString("NAME"), re.getString("FNAME"), re.getString("MNAME"), re.getString("Mobile"), icon));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        repaint();
        validate();
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        loadcard();
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        loadcard();
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {

    }

}
