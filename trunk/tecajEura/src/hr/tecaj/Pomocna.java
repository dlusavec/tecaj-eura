package hr.tecaj;

import java.awt.Component;

import java.lang.reflect.Field;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.text.ParseException;

import java.util.Locale;

import javax.swing.JOptionPane;

public class Pomocna {
    private static DecimalFormat decimalniBrojFormat =
        (DecimalFormat) new DecimalFormat().getNumberInstance(new Locale("hr", "HR"));    
    public Pomocna() {
        super();
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
}
