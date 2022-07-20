/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.ui.factory;

import annotations.Injectable;
import banana.Injector;
import barcoder.ui.ControlPanel;
import core.data.Factory;

/**
 *
 * @author Manel
 */
@Injectable
public class ControlPanelFactory extends Factory<ControlPanel> {

    public ControlPanelFactory(Injector container) {
        super(container);
    }
}
