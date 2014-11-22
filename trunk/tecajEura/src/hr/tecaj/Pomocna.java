package hr.tecaj;

import java.awt.Component;

import java.lang.reflect.Field;

import javax.swing.JOptionPane;

public class Pomocna {
    public Pomocna() {
        super();
    }
    public static void porukaInfo(Component c,String poruka){
        JOptionPane.showMessageDialog(c,poruka,"Informacija",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void porukaError(Component c,String poruka){
        JOptionPane.showMessageDialog(c,poruka,"Greška",JOptionPane.ERROR_MESSAGE);
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
                                    System.out
                                                    .println("----------------------------------------");
                                    System.out.println(polje.getName() + ": "
                                                    + polje.get(object));                                   
                            } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                            } catch (IllegalAccessException e) {
                                    System.out.println(e.getMessage());
                            }
                    }

            }
    }
}
