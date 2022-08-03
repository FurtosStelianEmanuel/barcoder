/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.filesystem;

import annotations.Injectable;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;

/**
 *
 * @author Manel
 */
@Injectable
public class PathManager {

    private final Map<String, Path> pathStore;

    public PathManager() {
        pathStore = new HashMap();
    }

    public enum Keys {
        SetupWritePath,
        SetupReadPath
    }

    public boolean setPathFor(Keys keyToSet) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose directory to store/load setup");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        pathStore.put(keyToSet.name(), fileChooser.getSelectedFile().toPath());
        return true;
    }

    public Path getPathFor(Keys key) {
        return pathStore.containsKey(key.name()) ? pathStore.get(key.name()) : null;
    }
}
