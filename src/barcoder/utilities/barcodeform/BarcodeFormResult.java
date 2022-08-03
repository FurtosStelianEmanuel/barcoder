/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.utilities.barcodeform;

/**
 *
 * @author Manel
 */
public class BarcodeFormResult {

    public String barcodeText;
    public String barcodeType;

    public BarcodeFormResult(String barcodeText, String barcodeType) {
        this.barcodeText = barcodeText;
        this.barcodeType = barcodeType;
    }
}
