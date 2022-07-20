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
public class SetupException extends Exception {

    public Exception innerException;

    public SetupException(Exception innerException) {
        this.innerException = innerException;
    }
}
