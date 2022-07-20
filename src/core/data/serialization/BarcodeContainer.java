/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.data.serialization;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manel
 */
public class BarcodeContainer {

    public List<Barcode> barcodes;
    public int x;
    public int y;
    public int width;
    public int height;

    public BarcodeContainer() {
        barcodes = new ArrayList();
    }
}
