/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Manel
 */
public interface BarcodeTypes {

    String getCode128();

    String getEAN8();

    String getEAN13();

    String getEAN14();

    String getEAN8CompositeSymbology();

    String getEAN13CompositeSymbology();

    String getUPCA();

    String getUPCE();

    String getUPCACompositeSymbology();

    String getUPCECompositeSymbology();

    public enum Names {
        Code128("Code-128"),
        EAN8("EAN-8"),
        EAN13("EAN-13"),
        EAN14("EAN-14"),
        EAN8CompositeSymbology("EAN-8 Composite Symbology"),
        EAN13CompositeSymbology("EAN-13 Composite Symbology"),
        UPCA("UPC-A"),
        UPCE("UPC-E"),
        UPCACompositeSymbology("UPC-A Composite Symbology"),
        UPCECompositeSymbology("UPC-E Composite Symbology");

        private final String casualName;

        private Names(String casualName) {
            this.casualName = casualName;
        }

        private Names() {
            this.casualName = this.name();
        }

        public String getCasualName() {
            return casualName;
        }
    }
}
