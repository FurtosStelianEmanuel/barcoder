/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.ui.factory;

import annotations.Injectable;
import banana.Injector;
import barcoder.ui.BarcodeContainer;
import core.data.Factory;

/**
 *
 * @author Manel
 */
@Injectable
public class BarcodeContainerFactory extends Factory<BarcodeContainer> {

    public BarcodeContainerFactory(Injector container) {
        super(container);
    }
}
