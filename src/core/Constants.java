/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import barcoder.filesystem.FileManager.FileExtensions;

/**
 *
 * @author Manel
 */
public class Constants {

    public static final String MANAGER_FILE_NAME = "manager";
    public static final String MANAGER_FILE_NAME_AND_EXTENSION = String.format("%s.%s", MANAGER_FILE_NAME, FileExtensions.Json.representation);
}
