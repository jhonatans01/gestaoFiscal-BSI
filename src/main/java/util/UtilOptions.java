/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

/**
 *
 * @author jhonatan
 */
public class UtilOptions {

    public static void converterSet(String string, ButtonGroup bg) {
        for (Enumeration<AbstractButton> b = bg.getElements(); b.hasMoreElements();) {
            AbstractButton button = b.nextElement();
            if (string != null) {
                switch (Integer.parseInt(string)) {
                    case 1:
                        if (button.getText().equals("Fraco")) {
                            button.setSelected(true);
                        }
                        break;
                    case 2:
                        if (button.getText().equals("Regular")) {
                            button.setSelected(true);
                        }
                        break;
                    case 3:
                        if (button.getText().equals("Bom")) {
                            button.setSelected(true);
                        }
                        break;
                    case 4:
                        if (button.getText().equals("Ótimo")) {
                            button.setSelected(true);
                        }
                        break;
                    default:
                        break;

                }
            }
        }

    }

    public static Integer converterGet(ButtonGroup bg) {
        for (Enumeration<AbstractButton> b = bg.getElements(); b.hasMoreElements();) {
            AbstractButton button = b.nextElement();
            if (button.isSelected()) {
                switch (button.getText()) {
                    case "Fraco":
                        return 1;
                    case "Regular":
                        return 2;
                    case "Bom":
                        return 3;
                    case "Ótimo":
                        return 4;
                    default:
                        return null;

                }
            }
        }
        return null;
    }
}
