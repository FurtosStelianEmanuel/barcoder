/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import javax.swing.ImageIcon;

/**
 *
 * @author Manel
 */
public class Barcode {

    private final ImageIcon imageRepresentation;
    private final String textRepresentation;

    public Barcode(ImageIcon imageRepresentation, String textRepresentation) {
        this.imageRepresentation = imageRepresentation;
        this.textRepresentation = textRepresentation;
    }

    public ImageIcon getImageRepresentation() {
        return imageRepresentation;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }
}
