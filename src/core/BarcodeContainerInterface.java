/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.ui.DrawableBarcode;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Manel
 */
public interface BarcodeContainerInterface {

    List<DrawableBarcode> getBarcodes();

    void addBarcode(DrawableBarcode barcode);

    void addBarcode(Barcode barcode);

    void deleteBarcode(DrawableBarcode barcode);

    void setDeleteEnabled(boolean isEnabled);

    void setHighlightEnabled(boolean isEnabled);

    void registerMaster(MasterSlaveLink master);

    void deleteContainer();

    Point getLocation();

    void setLocation(Point location);

    void setVisible(boolean isVisible);

    void setSize(int width, int height);

    Dimension getSize();

    public interface ControlPanelInterface {

        void bindToContainer(BarcodeContainerInterface containerInterface);

        BarcodeContainerInterface getContainer();

        void setHighlightToggle(boolean isEnabled);

        void setLocation(int x, int y);

        void setSize(int width, int height);
    }
}
