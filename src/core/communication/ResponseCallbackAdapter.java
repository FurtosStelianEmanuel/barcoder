/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.communication;

/**
 *
 * @author Manel
 * @param <K>
 */
public abstract class ResponseCallbackAdapter<K> {

    public abstract void onResponse(K responseData);
}
