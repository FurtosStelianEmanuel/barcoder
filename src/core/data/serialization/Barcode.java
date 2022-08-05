/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.data.serialization;

/**
 *
 * @author Manel
 */
public class Barcode {

    public String imagePath;
    public String stringRepresentation;
    public String barcodeType;

    public Barcode() {
    }

    public Barcode(String imagePath, String stringRepresentation, String barcodeType) {
        this.imagePath = imagePath;
        this.stringRepresentation = stringRepresentation;
        this.barcodeType = barcodeType;
    }
}
