/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerators.localbarcodeprovider;

import annotations.Injectable;
import core.Barcode;
import core.BarcodeGenerationException;
import core.LocalBarcodeProviderInterface;
import core.communication.CommunicationException;
import core.communication.http.ImageFetcherInterface;
import filesystem.PathManager;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

/**
 *
 * @author Manel
 */
@Injectable
public class LocalStorageBarcodeProvider implements LocalBarcodeProviderInterface {

    private final ImageFetcherInterface imageFetcher;

    public LocalStorageBarcodeProvider(ImageFetcherInterface imageFetcher, PathManager pathManager) {
        this.imageFetcher = imageFetcher;
    }

    @Override
    public Barcode getBarcodeFromUrl(String url, String textRepresentation, String barcodeType) throws BarcodeGenerationException {
        try {
            ImageIcon imageIcon = imageFetcher.fetchImage(Paths.get(url).toFile());
            Barcode barcode = new Barcode(imageIcon, textRepresentation, barcodeType);

            return barcode;
        } catch (CommunicationException ex) {
            throw new BarcodeGenerationException(textRepresentation, ex);
        }
    }
}
