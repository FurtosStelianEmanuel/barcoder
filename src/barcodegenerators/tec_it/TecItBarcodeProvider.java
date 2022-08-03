/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerators.tec_it;

import annotations.Injectable;
import core.Barcode;
import core.BarcodeGenerationException;
import core.BarcodeMeasurementsHelperInterface;
import core.BarcodeProviderInterface;
import core.communication.CommunicationException;
import core.communication.http.ImageFetcherInterface;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import utils.URLHelper;

/**
 *
 * @author Manel
 */
@Injectable
public class TecItBarcodeProvider implements BarcodeProviderInterface {

    private final ImageFetcherInterface imageFetcher;
    private final String barcodeAPIUrl = "https://barcode.tec-it.com/barcode.ashx";
    private final BarcodeMeasurementsHelperInterface barcodeMeasurementsHelper;
    private final URLHelper urlHelper;

    public TecItBarcodeProvider(ImageFetcherInterface imageFetcher, BarcodeMeasurementsHelperInterface barcodeMeasurementsHelper, URLHelper urlHelper) {
        this.imageFetcher = imageFetcher;
        this.barcodeMeasurementsHelper = barcodeMeasurementsHelper;
        this.urlHelper = urlHelper;
    }

    @Override
    public Barcode getBarcodeFromString(String input, String barcodeType) throws BarcodeGenerationException {
        try {
            String url = String.format("%s?%s=%s&%s=%s&%s=%s",
                    barcodeAPIUrl,
                    BarcodeAPIParam.InputString.getUrlEncoded(),
                    urlHelper.encode(input), BarcodeAPIParam.Code.getUrlEncoded(),
                    barcodeType,
                    BarcodeAPIParam.TranslateEsc.getUrlEncoded(),
                    Translate.On.getUrlEncoded()
            );
            ImageIcon imageIcon = imageFetcher.fetchImage(url);
            Barcode barcode = new Barcode(imageIcon, input);

            if (barcodeMeasurementsHelper.getActualBarcodeHeight() == -2) {
                barcodeMeasurementsHelper.setActualBarcodeHeight(calculateActualBarcodeHeight(barcode));
            }

            return barcode;
        } catch (CommunicationException ex) {
            throw new BarcodeGenerationException(input, ex);
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
