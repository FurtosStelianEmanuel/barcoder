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
public interface JsonSerializerInterface {

    <K> String serializeObject(K object) throws JsonSerializerException;

    <K> K deserializeString(String input, Class<K> type) throws JsonSerializerException;
}
