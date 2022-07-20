/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.data;

import banana.Injector;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author Manel
 * @param <T>
 */
public abstract class Factory<T> {

    private final Injector container;

    public Factory(Injector container) {
        this.container = container;
    }

    public <T> T getNewInstance() {
        Class c = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        return (T) container.resolveDependencies(c);
    }
}