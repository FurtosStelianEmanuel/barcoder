/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.ui;

import core.Barcode;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Manel
 */
public class DrawableBarcode extends Barcode {

    private Point location;

    private boolean isHovered = false;

    public DrawableBarcode(ImageIcon imageRepresentation, String textRepresentation) {
        super(imageRepresentation, textRepresentation);
    }

    public DrawableBarcode(Barcode barcode) {
        super(barcode.getImageRepresentation(), barcode.getTextRepresentation());
        this.location = new Point(0, 0);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(double x, double y) {
        location.setLocation(x, y);
    }

    public boolean isPointHoveringBarcode(Point point) {
        return point.getX() >= getLocation().getX() && point.getX() <= getLocation().getX() + getImageRepresentation().getIconWidth()
                && point.getY() >= getLocation().getY() && point.getY() <= getLocation().getY() + getImageRepresentation().getIconHeight();
    }

    public void setIsHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }

    public boolean wasHovered() {
        return isHovered;
    }
}
