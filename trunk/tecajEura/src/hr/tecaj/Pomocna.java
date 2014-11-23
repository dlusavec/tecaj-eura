package hr.tecaj;

import java.awt.Component;

import java.lang.reflect.Field;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.text.DecimalFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Pomocna {
    private static DecimalFormat decimalniBrojFormat =
        (DecimalFormat) new DecimalFormat().getNumberInstance(new Locale("hr", "HR"));
    private static Connection konekcija;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public Pomocna() {
        super();
    }

    public static void setKonekcija(Connection konekcija) {
        Pomocna.konekcija = konekcija;
    }

    public static Connection getKonekcija() {
        return konekcija;
    }

    public static void porukaInfo(Component c, String poruka) {
        JOptionPane.showMessageDialog(c, poruka, "Informacija", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void porukaError(Component c, String poruka) {
        JOptionPane.showMessageDialog(c, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
    }

    public static void ispis(Object object) {
        if (object == null) {
            System.err.println("Ispis objekta null reference...");
        } else {
            Class klasa = object.getClass();
            Field[] polja = klasa.getDeclaredFields();
            for (Field polje : polja) {
                polje.setAccessible(true);
                try {
                    System.out.println("----------------------------------------");
                    System.out.println(polje.getName() + ": " + polje.get(object));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public static BigDecimal stringToBigDecimal(String broj) {
        try {
            return new BigDecimal(decimalniBrojFormat.parse(broj).toString());
        } catch (ParseException e) {
            //Jebes gresku pisi 0
            return new BigDecimal(0);
        }

    }

    public static void spojiSeNaBazu() {
        try {
            konekcija = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "dbo", "mareinfo");
        } catch (SQLException e) {
            porukaError(null, e.getMessage());
        }
    }

    public static java.util.Date stringUDatum(String datum) {
        try {
            return sdf.parse(datum);
        } catch (ParseException e) {
            return null;
        }
    }

    public static java.sql.Date datumUSQLDatum(Date datum) {
        return new java.sql.Date(datum.getTime());
    }

    public static java.sql.Date brojUSQLDatum(int godina, int mjesec, int dan) {
        return datumUSQLDatum(stringUDatum(Integer.toString(godina * 10000 + mjesec * 100 + dan)));

    }

    public static void tecajValuteKrozGodine(String valuta) {
        String upitSQL = "select godina,min(kupovni) min_tecaj from tecaj where valuta=? group by godina order by 1";
        PreparedStatement upit;
        ResultSet rs;
        try {
            upit = konekcija.prepareStatement(upitSQL);
            upit.setString(1, valuta.trim());
            rs = upit.executeQuery(upitSQL);
            while (rs.next()) {
                System.out.println(rs.getInt("godina"));
                System.out.println(rs.getBigDecimal("min_tecaj"));
            }
            rs.close();
            upit.close();
        } catch (SQLException e) {
            porukaError(null, e.getMessage());
        }
    }

    public static void getMinMaxTecajValute(String valuta) {
        String upitSQL = "select MIN(kupovni) minimalni,MAX(kupovni) maksimalni from tecaj where valuta=?";
        PreparedStatement upit;
        ResultSet rs;
        try {
            upit = konekcija.prepareStatement(upitSQL);
            upit.setString(1, valuta.trim());
            rs = upit.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getBigDecimal("minimalni"));
                System.out.println(rs.getBigDecimal("maksimalni"));
            }
            rs.close();
            upit.close();
        } catch (SQLException e) {
            porukaError(null, e.getMessage());
        }
    }
}
