/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerators.tec_it;

/**
 *
 * @author Manel
 */
enum BarcodeAPIParam {
    InputString("data"),
    Code("code"),
    TranslateEsc("translate-esc");

    private final String urlEncoded;

    private BarcodeAPIParam(String urlEncodedParam) {
        this.urlEncoded = urlEncodedParam;
    }

    public String getUrlEncoded() {
        return urlEncoded;
    }
}
