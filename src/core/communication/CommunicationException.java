/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.communication;

/**
 *
 * @author Manel
 */
public class CommunicationException extends Exception {

    private final Exception innerException;

    public CommunicationException(String url, Exception innerException) {
        super(String.format("An exception has occurred when trying to communicate at URL %s", url));
        this.innerException = innerException;
    }
}
