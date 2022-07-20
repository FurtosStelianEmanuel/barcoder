/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder;

import banana.Injector;
import banana.exceptions.ClassNotInjectable;
import banana.exceptions.InterfaceNotImplemented;
import banana.exceptions.UnresolvableDependency;
import barcoder.filesystem.FileManager;
import barcoder.filesystem.PathManager;
import barcoder.ui.MasterForm;
import barcoder.ui.common.Styles;
import barcoder.ui.factory.BarcodeContainerFactory;
import barcoder.ui.factory.ControlPanelFactory;
import barcoder.utilities.DrawingUtils;
import barcoder.utilities.ImageUtils;
import communication.http.ImageFetcher;
import core.BarcodeGenerationException;
import core.BarcodeMeasurementsHelper;
import core.BarcodeMeasurementsHelperInterface;
import core.BarcodeProviderInterface;
import core.LocalBarcodeProviderInterface;
import core.MasterFormInterface;
import core.communication.http.ImageFetcherInterface;
import core.data.serialization.BananaConvertWrapper;
import core.data.serialization.BarcodeContainerSerializer;
import core.data.serialization.JsonSerializerInterface;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import localbarcodeprovider.LocalStorageBarcodeProvider;
import tec_it.TecItBarcodeProvider;

/**
 *
 * @author Manel
 */
public class Barcoder {
    
    public Injector container;
    public MasterFormInterface masterForm;
    
    public Barcoder(Injector container) throws BarcodeGenerationException {
        this.container = container;
    }
    
    public void startup() {
        masterForm = container.resolveDependencies(MasterFormInterface.class);
        masterForm.onStartup();
    }

    /**
     * @param args the command line arguments
     * @throws banana.exceptions.InterfaceNotImplemented
     * @throws banana.exceptions.ClassNotInjectable
     * @throws core.BarcodeGenerationException
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws banana.exceptions.UnresolvableDependency
     */
    public static void main(String[] args) throws InterfaceNotImplemented, ClassNotInjectable, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, BarcodeGenerationException, UnresolvableDependency {
        Injector applicationContainer = new Injector(new HashMap<>(), new HashMap<>(), new ArrayList<>());
        
        applicationContainer
                .addDependency(ImageFetcherInterface.class, ImageFetcher.class)
                .addDependency(FileManager.class, FileManager.class)
                .addDependency(JsonSerializerInterface.class, new BananaConvertWrapper())
                .addDependency(PathManager.class, PathManager.class)
                .addDependency(BarcodeProviderInterface.class, TecItBarcodeProvider.class)
                .addDependency(BarcodeContainerSerializer.class, BarcodeContainerSerializer.class)
                .addDependency(Styles.class, Styles.class)
                .addDependency(Injector.class, applicationContainer)
                .addDependency(ControlPanelFactory.class, new ControlPanelFactory(applicationContainer))
                .addDependency(BarcodeContainerFactory.class, BarcodeContainerFactory.class)
                .addDependency(MasterFormInterface.class, MasterForm.class)
                .addDependency(LocalBarcodeProviderInterface.class, LocalStorageBarcodeProvider.class)
                .addDependency(BarcodeMeasurementsHelperInterface.class, BarcodeMeasurementsHelper.class)
                .addDependency(ImageUtils.class, ImageUtils.class)
                .addDependency(DrawingUtils.class, DrawingUtils.class);
        
        applicationContainer.initialise();
        
        new Barcoder(applicationContainer).startup();
    }
    
}
