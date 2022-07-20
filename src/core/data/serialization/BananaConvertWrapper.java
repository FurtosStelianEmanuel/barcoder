/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.data.serialization;

import annotations.Injectable;
import bananaconvert.BananaConvert;
import bananaconvert.marshaler.exception.DeserializationException;
import bananaconvert.marshaler.exception.SerializationException;

/**
 *
 * @author Manel
 */
@Injectable
public class BananaConvertWrapper implements JsonSerializerInterface {

    private final BananaConvert bananaConvert;

    public BananaConvertWrapper() {
        bananaConvert = new BananaConvert();
    }

    @Override
    public <K> String serializeObject(K object) throws JsonSerializerException {
        try {
            return bananaConvert.serializeToJson(object);
        } catch (SerializationException ex) {
            throw new JsonSerializerException(ex);
        }
    }

    @Override
    public <K> K deserializeString(String input, Class<K> type) throws JsonSerializerException {
        try {
            return bananaConvert.deserializeJson(input, type);
        } catch (DeserializationException ex) {
            throw new JsonSerializerException(ex);
        }
    }
}
