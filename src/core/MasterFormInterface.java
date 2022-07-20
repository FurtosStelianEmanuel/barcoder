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
public interface MasterFormInterface {

    void addContainer();

    void loadSetup() throws SetupException;

    void saveSetup() throws SetupException;

    void exit();

    void onStartup();
}
