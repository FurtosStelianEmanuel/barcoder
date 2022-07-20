/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localbarcodeprovider;

import annotations.Injectable;
import barcoder.filesystem.PathManager;
import core.Barcode;
import core.BarcodeGenerationException;
import core.BarcodeMeasurementsHelperInterface;
import core.LocalBarcodeProviderInterface;
import core.communication.CommunicationException;
import core.communication.http.ImageFetcherInterface;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

/**
 *
 * @author Manel
 */
@Injectable
public class LocalStorageBarcodeProvider implements LocalBarcodeProviderInterface {

    private final ImageFetcherInterface imageFetcher;
    private final BarcodeMeasurementsHelperInterface barcodeMeasurementsHelper;

    public LocalStorageBarcodeProvider(ImageFetcherInterface imageFetcher, PathManager pathManager, BarcodeMeasurementsHelperInterface barcodeMeasurementsHelper) {
        this.imageFetcher = imageFetcher;
        this.barcodeMeasurementsHelper = barcodeMeasurementsHelper;
    }

    @Override
    public Barcode getBarcodeFromUrl(String url, String textRepresentation) throws BarcodeGenerationException {
        try {
            ImageIcon imageIcon = imageFetcher.fetchImage(Paths.get(url).toFile());
            Barcode barcode = new Barcode(imageIcon, textRepresentation);

            if (barcodeMeasurementsHelper.getActualBarcodeHeight() == -2) {
                barcodeMeasurementsHelper.setActualBarcodeHeight(calculateActualBarcodeHeight(barcode));
            }

            return barcode;
        } catch (CommunicationException ex) {
            throw new BarcodeGenerationException(textRepresentation, ex);
        }
    }

    private int calculateActualBarcodeHeight(Barcode barcode) {
        BufferedImage barcodeImage = (BufferedImage) barcode.getImageRepresentation().getImage();
        for (int i = 0; i < barcodeImage.getWidth(); i++) {
            for (int j = 0; j < barcodeImage.getHeight(); j++) {
                Color pixelColor = new Color(barcodeImage.getRGB(i, j));
                int height = 0;

                while (pixelColor.getRed() != Color.WHITE.getRed()) {
                    height++;
                    j++;
                    pixelColor = new Color(barcodeImage.getRGB(i, j));
                }

                if (height != 0) {
                    return height;
                }
            }
        }

        return -1;
    }
}
