/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.utilities;

import annotations.Injectable;
import barcoder.ui.common.Styles;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Manel
 */
@Injectable
public class DrawingUtils {

    private final Styles styles;

    public DrawingUtils(Styles styles) {
        this.styles = styles;
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setColor(styles.text);
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
