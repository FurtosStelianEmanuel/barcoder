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
public interface LocalBarcodeProviderInterface {

    Barcode getBarcodeFromUrl(String url, String textRepresentation) throws BarcodeGenerationException;
}
