/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.communication.http;

import core.communication.CommunicationException;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Manel
 */
public interface ImageFetcherInterface {

    ImageIcon fetchImage(String url) throws CommunicationException;

    ImageIcon fetchImage(File f) throws CommunicationException;
}
