/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tec_it;

/**
 *
 * @author Manel
 */
enum Codes {
    Code128("Code128");

    private final String urlEncoded;

    private Codes(String urlEncodedParam) {
        this.urlEncoded = urlEncodedParam;
    }

    public String getUrlEncoded() {
        return urlEncoded;
    }
}
