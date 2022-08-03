/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.utilities.barcodeform;

import core.communication.ResponseCallbackAdapter;

/**
 *
 * @author Manel
 */
public interface BarcodeFormInterface {

    void showFormAndCallbackWithResult(ResponseCallbackAdapter adapter);
}
