/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import annotations.Injectable;

/**
 *
 * @author Manel
 */
@Injectable
public class BarcodeMeasurementsHelper implements BarcodeMeasurementsHelperInterface {

    private int actualHeight = -2;

    @Override

    public int getActualBarcodeHeight() {
        return actualHeight;
    }

    @Override
    public void setActualBarcodeHeight(int height) {
        this.actualHeight = height;
    }

}
