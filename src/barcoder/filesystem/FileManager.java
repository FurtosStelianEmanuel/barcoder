/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.filesystem;

import annotations.Injectable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Manel
 */
@Injectable
public class FileManager {

    public enum FileExtensions {
        Json("json"),
        PNG("png");
        public final String representation;

        private FileExtensions(String representation) {
            this.representation = representation;
        }
    }

    public void outputStringToFile(String toStore, Path path, String fileName, FileExtensions fileExtension) throws FileSystemException {
        FileWriter file = null;
        try {
            file = new FileWriter(Paths.get(path.toString(), String.format("%s.%s", fileName, fileExtension.representation)).toFile());
            file.write(toStore);
            file.close();
        } catch (IOException ex) {
            throw new FileSystemException(ex);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException ex) {
                throw new FileSystemException(ex);
            }
        }
    }

    public String readStringFromFile(File path) throws FileSystemException {
        try {
            byte[] encoded = Files.readAllBytes(path.toPath());
            return new String(encoded, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            throw new FileSystemException(ex);
        }
    }
}
