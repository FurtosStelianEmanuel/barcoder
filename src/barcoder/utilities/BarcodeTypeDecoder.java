/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.utilities;

/**
 *
 * @author Manel
 */
public interface BarcodeTypeDecoder {

    String getApiSpecificValueFromCasualName(String textRepresentation);

    String[] getAllCasualNames();
}
