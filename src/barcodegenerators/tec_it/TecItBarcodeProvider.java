/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerators.tec_it;

import annotations.Injectable;
import barcoder.utilities.BarcodeTypeDecoder;
import core.Barcode;
import core.BarcodeGenerationException;
import core.BarcodeProviderInterface;
import core.communication.CommunicationException;
import core.communication.http.ImageFetcherInterface;
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
    private final URLHelper urlHelper;
    private final BarcodeTypeDecoder barcodeTypeDecoder;

    public TecItBarcodeProvider(ImageFetcherInterface imageFetcher, URLHelper urlHelper, BarcodeTypeDecoder barcodeTypeDecoder) {
        this.imageFetcher = imageFetcher;
        this.urlHelper = urlHelper;
        this.barcodeTypeDecoder = barcodeTypeDecoder;
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
            Barcode barcode = new Barcode(imageIcon, input, barcodeTypeDecoder.getCasualNameFromApiSpecificValue(barcodeType));

            return barcode;
        } catch (CommunicationException ex) {
            throw new BarcodeGenerationException(input, ex);
        }
    }
}
