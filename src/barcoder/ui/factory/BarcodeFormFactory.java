/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.ui.factory;

import annotations.Injectable;
import banana.Injector;
import barcoder.utilities.barcodeform.BarcodeForm;
import core.data.Factory;

/**
 *
 * @author Manel
 */
@Injectable
public class BarcodeFormFactory extends Factory<BarcodeForm> {

    public BarcodeFormFactory(Injector container) {
        super(container);
    }
}
