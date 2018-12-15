package studensection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java_project.V;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.Border;
public class StudentRow extends JPanel{
    JLabel name,Fname,mname,mobile,photo; ImageIcon img1 = new ImageIcon(new ImageIcon(getClass()
                .getResource("/images/lg_avatar.png")).getImage().getScaledInstance(90,90, Image.SCALE_SMOOTH));
    Border br=BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.black,2),BorderFactory.createRaisedBevelBorder());
    Color bc=new Color(190,204,255);
    public StudentRow() {
        super();
        setLayout(null);
       setBorder(br);
        setBackground(bc);
        setSize(350,120);
        setPreferredSize(new Dimension(350,120));
        name=new JLabel("Name");Fname=new JLabel("Fathers Name");mname=new JLabel("Mothers Name");
        mobile=new JLabel("Mobile Number");photo=new JLabel(img1);
        name.setBounds(8,6,220,25);
        name.setFont(V.stdrow);
        Fname.setBounds(8,30,220,25);
        Fname.setFont(V.stdrow);
        mname.setBounds(8,58,220,25);
        mname.setFont(V.stdrow);
        mobile.setBounds(8,87,225,25);
        mobile.setFont(V.stdrow);
        photo.setBounds(230,4,120,120);
        add(name);
        add(Fname);
        add(mname);
        add(mobile);
        add(photo);
        
    }
    public StudentRow(String n,String fn,String mn,String m,ImageIcon img){
         super();
        setLayout(null);
      setBorder(br);
        if(n==null)n="";
        if(fn==null)n="";
        if(mn==null)n="";
        if(m==null)n="";
       if(img==null)img=this.img1;
          setBackground(bc);
        setSize(350,120);
        setPreferredSize(new Dimension(350,120));
        this.name=new JLabel(n);
        Fname=new JLabel(fn);
        mname=new JLabel(mn);
        mobile = new JLabel(m);
        photo=new JLabel(img);
        name.setBounds(8,6,220,25);
        name.setFont(V.stdrow);
        Fname.setBounds(8,30,220,25);
        Fname.setFont(V.stdrow);
        mname.setBounds(8,58,220,25);
        mname.setFont(V.stdrow);
        mobile.setBounds(8,87,220,25);
        mobile.setFont(V.stdrow);
        photo.setBounds(230,4,120,120);
        add(name);
        add(Fname);
        add(mname);
        add(mobile);
        add(photo);
    }
   
}
