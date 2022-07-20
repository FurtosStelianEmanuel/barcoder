/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.data.serialization;

/**
 *
 * @author Manel
 */
public class JsonSerializerException extends Exception {

    public Exception innerException;

    public JsonSerializerException(Exception innerException) {
        this.innerException = innerException;
    }
}
