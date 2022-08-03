/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.data.serialization;

import annotations.Injectable;
import barcoder.ui.factory.BarcodeContainerFactory;
import barcoder.utilities.ImageUtils;
import core.BarcodeContainerInterface;
import core.BarcodeGenerationException;
import core.LocalBarcodeProviderInterface;
import core.SetupException;
import filesystem.FileManager;
import filesystem.PathManager;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;

/**
 *
 * @author Manel
 */
@Injectable
public class BarcodeContainerSerializer {

    private final PathManager pathManager;
    private final LocalBarcodeProviderInterface barcodeProvider;
    private final BarcodeContainerFactory barcodeContainerFactory;
    private final ImageUtils imageUtils;

    private final String storedImageExtension = FileManager.FileExtensions.PNG.representation;
    private final String fileNameFormat = "%s.%s";

    public BarcodeContainerSerializer(PathManager pathManager, LocalBarcodeProviderInterface barcodeProvider, BarcodeContainerFactory barcodeContainerFactory, ImageUtils imageUtils) {
        this.pathManager = pathManager;
        this.barcodeProvider = barcodeProvider;
        this.barcodeContainerFactory = barcodeContainerFactory;
        this.imageUtils = imageUtils;
    }

    public BarcodeContainer saveBarcodesInFileSystemAndGetContainer(BarcodeContainerInterface barcodeContainer) throws SetupException {
        BarcodeContainer serializableContainer = new BarcodeContainer();
        serializableContainer.x = (int) barcodeContainer.getLocation().getX();
        serializableContainer.y = (int) barcodeContainer.getLocation().getY();

        for (core.ui.DrawableBarcode drawableBarcode : barcodeContainer.getBarcodes()) {
            BufferedImage bufferedImage = imageUtils.toBufferedImage(drawableBarcode.getImageRepresentation().getImage());
            String fileName = String.format(fileNameFormat, UUID.randomUUID().toString(), storedImageExtension);
            Path filePath = Paths.get(pathManager.getPathFor(PathManager.Keys.SetupWritePath).toString(), fileName);

            try {
                ImageIO.write(bufferedImage, storedImageExtension, filePath.toFile());
            } catch (IOException ex) {
                throw new SetupException(ex);
            }

            Barcode serializableBarcode = new Barcode(filePath.toString(), drawableBarcode.getTextRepresentation());
            serializableContainer.barcodes.add(serializableBarcode);
        }

        return serializableContainer;
    }

    public List<BarcodeContainer> saveBarcodesInFileSystemAndGetContainers(List<BarcodeContainerInterface> barcodeContainers) throws SetupException {
        List<BarcodeContainer> serializableBarcodeContainers = new ArrayList();

        for (BarcodeContainerInterface barcodeContainer : barcodeContainers) {
            final BarcodeContainer serializedBarcode = saveBarcodesInFileSystemAndGetContainer(barcodeContainer);
            serializedBarcode.width = (int) barcodeContainer.getSize().getWidth();
            serializedBarcode.height = (int) barcodeContainer.getSize().getHeight();
            serializableBarcodeContainers.add(serializedBarcode);
        }

        return serializableBarcodeContainers;
    }

    public List<BarcodeContainerInterface> getBarcodeContainersFromFileSystem(List<BarcodeContainer> serializedContainers) throws BarcodeGenerationException {
        List<BarcodeContainerInterface> barcodeContainers = new ArrayList();

        for (BarcodeContainer serializedContainer : serializedContainers) {
            BarcodeContainerInterface barcodeContainer = barcodeContainerFactory.getNewInstance();
            barcodeContainer.setLocation(new Point(serializedContainer.x, serializedContainer.y));
            barcodeContainer.setSize(serializedContainer.width, serializedContainer.height);

            for (Barcode serializedBarcode : serializedContainer.barcodes) {
                core.Barcode barcode = barcodeProvider.getBarcodeFromUrl(serializedBarcode.imagePath, serializedBarcode.stringRepresentation);
                barcodeContainer.addBarcode(barcode);
            }

            barcodeContainers.add(barcodeContainer);
        }

        return barcodeContainers;
    }
}
