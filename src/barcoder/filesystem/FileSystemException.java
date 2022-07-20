/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.filesystem;

/**
 *
 * @author Manel
 */
public class FileSystemException extends Exception {

    public Exception innerException;

    public FileSystemException(Exception innerException) {
        this.innerException = innerException;
    }
}
