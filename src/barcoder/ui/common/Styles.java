/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.ui.common;

import annotations.Injectable;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Manel
 */
@Injectable
public class Styles {

    private final String fontName = "Times New Roman";
    private final int fontSize = 26;

    public final Color background = Color.WHITE;
    public final Color text = Color.BLACK;
    public final Font textFont = new Font(fontName, Font.BOLD, fontSize);
    public final Color barcodeBackground = Color.WHITE;
}
