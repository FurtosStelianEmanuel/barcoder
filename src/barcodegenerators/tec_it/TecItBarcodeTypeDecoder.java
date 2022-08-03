/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerators.tec_it;

import annotations.Injectable;
import barcoder.utilities.BarcodeTypeDecoder;
import core.BarcodeTypes;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Manel
 */
@Injectable
public class TecItBarcodeTypeDecoder implements BarcodeTypeDecoder, BarcodeTypes {

    private final Map<String, String> casualBarcodeTypeNameAndApiSpecificValue;

    public TecItBarcodeTypeDecoder() {
        casualBarcodeTypeNameAndApiSpecificValue = new LinkedHashMap() {
            {
                put(BarcodeTypes.Names.Code128.getCasualName(), TecItBarcodeTypeDecoder.this.getCode128());
                put(BarcodeTypes.Names.EAN8.getCasualName(), TecItBarcodeTypeDecoder.this.getEAN8());
                put(BarcodeTypes.Names.EAN13.getCasualName(), TecItBarcodeTypeDecoder.this.getEAN13());
                put(BarcodeTypes.Names.EAN14.getCasualName(), TecItBarcodeTypeDecoder.this.getEAN14());
                put(BarcodeTypes.Names.EAN8CompositeSymbology.getCasualName(), TecItBarcodeTypeDecoder.this.getEAN8CompositeSymbology());
                put(BarcodeTypes.Names.EAN13CompositeSymbology.getCasualName(), TecItBarcodeTypeDecoder.this.getEAN13CompositeSymbology());
                put(BarcodeTypes.Names.UPCA.getCasualName(), TecItBarcodeTypeDecoder.this.getUPCA());
                put(BarcodeTypes.Names.UPCE.getCasualName(), TecItBarcodeTypeDecoder.this.getUPCE());
                put(BarcodeTypes.Names.UPCACompositeSymbology.getCasualName(), TecItBarcodeTypeDecoder.this.getUPCACompositeSymbology());
                put(BarcodeTypes.Names.UPCECompositeSymbology.getCasualName(), TecItBarcodeTypeDecoder.this.getUPCECompositeSymbology());
            }
        };
    }

    @Override
    public String getApiSpecificValueFromCasualName(String casualName) {
        if (!casualBarcodeTypeNameAndApiSpecificValue.containsKey(casualName)) {
            return null;
        }

        return casualBarcodeTypeNameAndApiSpecificValue.get(casualName);
    }

    @Override
    public String[] getAllCasualNames() {
        return casualBarcodeTypeNameAndApiSpecificValue.keySet().toArray(new String[0]);
    }

    @Override
    public String getCode128() {
        return "Code128";
    }

    @Override
    public String getEAN8() {
        return "EAN8";
    }

    @Override
    public String getEAN13() {
        return "EAN13";
    }

    @Override
    public String getEAN14() {
        return "EAN14";
    }

    @Override
    public String getEAN8CompositeSymbology() {
        return "EAN8CCA";
    }

    @Override
    public String getEAN13CompositeSymbology() {
        return "EAN13CCA";
    }

    @Override
    public String getUPCA() {
        return "UPCA";
    }

    @Override
    public String getUPCE() {
        return "UPCE";
    }

    @Override
    public String getUPCACompositeSymbology() {
        return "UPCACCA";
    }

    @Override
    public String getUPCECompositeSymbology() {
        return "UPCECCA";
    }
}
