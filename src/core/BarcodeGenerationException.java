/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Manel
 */
public class BarcodeGenerationException extends Exception {

    private final Exception innerException;

    public BarcodeGenerationException(String inputString, Exception innerException) {
        super(String.format("An error has occurred when trying to generate barcode for input string %s", inputString));
        this.innerException = innerException;
    }
}
