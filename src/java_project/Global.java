package java_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

public class Global {

    public static String day, month, year, date;
  public static boolean cnct;
    public Global() {
        loadpref();
        Calendar cal = Calendar.getInstance();
        day = Integer.toString((int) cal.get(Calendar.DAY_OF_MONTH));
        month = Integer.toString((int) cal.get(Calendar.MONTH));
        int k = Integer.parseInt(month) + 1;
        month = Integer.toString(k);
        year = Integer.toString((int) cal.get(Calendar.YEAR));
    }

    public static void loadpref() {
        try {
            Preferences prefs = Preferences.userNodeForPackage(Mainframe.class);
            databaseurl = prefs.get("databaseurl", "jdbc:mysql://localhost/mydatabase");
            System.out.println(databaseurl);
            databasepassword = prefs.get("databasepass", "");
            databaseusername = prefs.get("databaseusername", "root");
            drivername = prefs.get("drivername", "com.mysql.cj.jdbc.Driver");
            System.out.println(databaseurl);
            System.out.println(databasepassword);
            System.out.println(databaseusername);
            System.out.println(drivername);

        } catch (Exception df) {
            System.out.println("reding pref failed " + df);
        }

    }

    public static String getDate() {
        return (year + "-" + month + "-" + day);
    }
    public static String monthname[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static String getMonthname() {
        return monthname[Integer.parseInt(month)];
    }
    public static String drivername = "com.mysql.cj.jdbc.Driver",
            databaseurl = "jdbc:mysql://localhost/mydatabase",
            databaseusername = "root", databasepassword = "";

    public static String getDay() {
        return day;
    }

    public static String getMonth() {
        return month;
    }

    public static String getYear() {
        return year;
    }

    public static String ledgeraccounttable = "LEDGER", salarytablename = "SALARYTABLE",
            studentinfotable = "STUDENTINFO", studentpaymentinfotable = "STUDENTPAYMENTABLE",
            foodcosttablename = "FOODCOSTTABLE", othercosttable = "OTHERCOSTTABLE", employeeinfo = "EMPLOYEEINFOTABLE";

    public static String getEmployeeinfo() {
        return employeeinfo;
    }

    public static String getOthercosttable() {
        return othercosttable;
    }

    public static String getFoodcosttablename() {
        return foodcosttablename;
    }

    public static Connection getConnection(Connection con) {
        try {
            cnct=true;
            con = DriverManager.getConnection(databaseurl, databaseusername, databasepassword);
            return con;
        } catch (Exception e) {
            cnct=false;
        }
        return null;
    }

    public static String getDrivername() {
        return drivername;
    }

    public static String getDatabaseurl() {
        return databaseurl;
    }

    public static String getDatabaseusername() {
        return databaseusername;
    }

    public static String getDatabasepassword() {
        return databasepassword;
    }

    public static String getLedgeraccounttable() {
        return ledgeraccounttable;
    }

    public static String getSalarytablename() {
        return salarytablename;
    }

    public static String getStudentinfotable() {
        return studentinfotable;
    }

    public static String getStudentpaymentinfotable() {
        return studentpaymentinfotable;
    }

}
