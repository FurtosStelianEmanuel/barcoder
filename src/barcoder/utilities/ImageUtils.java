/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.utilities;

import annotations.Injectable;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manel
 */
@Injectable
public class ImageUtils {

    public BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    public Image getCroppedImage(Image img, int newWidth, int newHeight) {
        BufferedImage bimage = toBufferedImage(img);

        return bimage.getSubimage(0, 0, newWidth, newHeight);
    }
}
