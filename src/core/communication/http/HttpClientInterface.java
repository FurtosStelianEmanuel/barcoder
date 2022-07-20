/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.communication.http;

import core.communication.CommunicationException;
import core.communication.ResponseCallback;

/**
 *
 * @author Manel
 */
public interface HttpClientInterface {

    /**
     *
     * @param callback
     * @throws CommunicationException
     * @deprecated Initially intended to load images over the network but
     * deprecated because the Image API already has a neat way of doing that
     * Could be revived if string data is needed
     */
    public void sendGetRequestAsync(ResponseCallback callback) throws CommunicationException;
}
