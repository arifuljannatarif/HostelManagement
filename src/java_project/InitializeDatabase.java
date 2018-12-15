package java_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class InitializeDatabase {

    Connection con;
    Statement st;
    DatabaseMetaData dbm;
    Timer t;
    String error = "";
    public static boolean ok = true;

    int i = 0;
    String[] val = {"loading resources", "Reading Changes", "Updating Changes"};

    public InitializeDatabase() throws InterruptedException {

        ok = true;

        try {
            System.out.println("registering driver");
            Class.forName(Global.getDrivername());
            System.out.println("driver registered");

        } catch (Exception e) {
            System.out.println(e);
            error += "Connection to Database Failed";
            ok = false;
        }
        try {
            System.out.println("connecting");
            con = Global.getConnection(con);
            System.out.println("connected");
        } catch (Exception e) {
            ok = false;
        }
        try {
            dbm = con.getMetaData();
            ResultSet rs;
            try {
                System.out.println("checking table 1");
                rs = dbm.getTables(null, null, Global.studentinfotable, new String[]{"TABLE"});
                if (!rs.next()) {

                    String query = "CREATE TABLE " + Global.studentinfotable + "( "
                            + "HOSTELID INT PRIMARY KEY"
                            + ",NAME VARCHAR(100)"
                            + ",FNAME VARCHAR(100)"
                            + ",MNAME VARCHAR(100)"
                            + ",PMOBILE VARCHAR(100)"
                            + ",LGNAME VARCHAR(100)"
                            + ",LGMOBILE VARCHAR(100)"
                            + ",MOBILE VARCHAR(100)"
                            + ",ADDRESS VARCHAR(100)"
                            + ",REMARKS VARCHAR(100)"
                            + ",STATUS INT DEFAULT 1"
                            + ", INSTITUTION VARCHAR(150)"
                            + ",CLASS VARCHAR(50)"
                            + ",RELATION VARCHAR(100)"
                            + ",MONTHLYFEE VARCHAR(50)"
                            + ",ROOM VARCHAR(50)"
                            + ",REFERENCE VARCHAR(100)"
                            + ",PHOTO LONGBLOB"
                            + ",PPHOTO LONGBLOB"
                            + ",LGPHOTO LONGBLOB"
                            + ")";
                    st = con.createStatement();
                    st.execute(query);
                }
            } catch (Exception exx) {
                ok = false;
                System.out.println("careating stdinfo " + exx);
            }
            // STUDENTPAYMENT TABLE
            try {
                rs = dbm.getTables(null, null, Global.getStudentpaymentinfotable(), new String[]{"TABLE"});
                if (!rs.next()) {
                    String query = "CREATE TABLE " + Global.studentpaymentinfotable + "( "
                            + "NAME VARCHAR(100)"
                            + ",PAYID  int  AUTO_INCREMENT"
                            + ",DATE VARCHAR(70)"
                            + ",PURPOSE VARCHAR(200)"
                            + ",AMOUNT INT,PRIMARY KEY(PAYID)"
                            + ")";
                    st = con.createStatement();
                    st.execute(query);
                }
            } catch (Exception xt) {
                ok = false;
                System.out.println("stdpayment " + xt);
            }
            //foodcosttable creating

            try {
                rs = dbm.getTables(null, null, Global.getFoodcosttablename(), new String[]{"TABLE"});
                if (!rs.next()) {
                    String query = "CREATE TABLE " + Global.getFoodcosttablename() + "( "
                            + "DATE DATE"
                            + ",MONTH VARCHAR(70),"
                            + "PAYID int AUTO_INCREMENT"
                            + ",PURPOSE VARCHAR(200)"
                            + ",AMOUNT INT,"
                            + "PRIMARY KEY (PAYID)"
                            + ")";
                    st = con.createStatement();
                    st.execute(query);
                }
            } catch (Exception sal) {
                ok = false;
                System.out.println("exception creation foodcost" + sal);
            }

            //
            //othercosttable creating
            try {
                rs = dbm.getTables(null, null, Global.getOthercosttable(), new String[]{"TABLE"});
                if (!rs.next()) {
                    String query = "CREATE TABLE " + Global.getOthercosttable() + "( "
                            + "DATE DATE"
                            + ",PAYID int AUTO_INCREMENT"
                            + ",PURPOSE VARCHAR(200)"
                            + ",AMOUNT INT,"
                            + "PRIMARY KEY(PAYID)"
                            + ")";
                    st = con.createStatement();
                    st.execute(query);
                }
            } catch (Exception er) {
                ok = false;
                System.out.println("other cost creating problem " + er);
            }

            //
            try {
                //employeeinfo table
                rs = dbm.getTables(null, null, Global.getEmployeeinfo(), new String[]{"TABLE"});
                if (!rs.next()) {
                    String query = "CREATE TABLE " + Global.getEmployeeinfo() + "( "
                            + "NAME VARCHAR(100)"
                            + ",NIDNO VARCHAR(100)"
                            + ",ADDRESS VARCHAR(200)"
                            + ",MOBILE VARCHAR(20)"
                            + ",SALARY INT(100)"
                            + ",PHOTO LONGBLOB"
                            + ",ID int AUTO_INCREMENT"
                            + ",STATUS INT DEFAULT 1"
                            + ",PRIMARY KEY(id)"
                            + ")";
                    st = con.createStatement();
                    st.execute(query);
                    System.out.println("oiedsd");
                }
            } catch (Exception et) {
                ok = false;
                System.out.println("creating emp info " + et);
            }

            //salarytable
            try {
                rs = null;
                rs = dbm.getTables(null, null, Global.getSalarytablename(), new String[]{"TABLE"});
                if (!rs.next()) {
                    String query = "CREATE TABLE " + Global.getSalarytablename() + "( "
                            + "DATE DATE"
                            + ",NAME VARCHAR(100)"
                            + ",PAYID  int not null AUTO_INCREMENT"
                            + ",NOTE VARCHAR(100)"
                            + ",AMOUNT INT"
                            + ",STATUS INT DEFAULT 1 ,PRIMARY KEY (PAYID) )";
                    st = con.createStatement();
                    st.execute(query);
                }
            } catch (Exception lk) {
                ok = false;
                System.out.println("salarytable " + lk);
            }
        } catch (Exception e) {
            error += "Filed to create Table";
            ok = false;
            System.out.println(e);

        }
    }

}
