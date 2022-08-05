/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerators.mockedgenerator;

import annotations.Injectable;
import core.Barcode;
import core.BarcodeGenerationException;
import core.BarcodeProviderInterface;
import core.communication.CommunicationException;
import core.communication.http.ImageFetcherInterface;
import javax.swing.ImageIcon;

/**
 *
 * @author Manel
 */
@Injectable
public class NodeBarcodeProvider implements BarcodeProviderInterface {

    private final ImageFetcherInterface imageFetcher;

    public NodeBarcodeProvider(ImageFetcherInterface imageFetcher) {
        this.imageFetcher = imageFetcher;
    }

    @Override
    public Barcode getBarcodeFromString(String input, String barcodeType) throws BarcodeGenerationException {
        try {
            String url = String.format("%s?%s=%s",
                    "http://localhost:666/api/barcode",
                    "input",
                    input
            );
            ImageIcon imageIcon = imageFetcher.fetchImage(url);
            Barcode barcode = new Barcode(imageIcon, input, barcodeType);

            return barcode;
        } catch (CommunicationException ex) {
            throw new BarcodeGenerationException(input, ex);
        }
    }
}
