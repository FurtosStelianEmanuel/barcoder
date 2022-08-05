/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcoder.ui;

import annotations.Injectable;
import barcoder.ui.factory.BarcodeContainerFactory;
import core.BarcodeContainerInterface;
import core.BarcodeGenerationException;
import core.Constants;
import core.MasterFormInterface;
import core.MasterSlaveLink;
import core.SetupException;
import core.data.serialization.BarcodeContainerSerializer;
import core.data.serialization.Configuration;
import core.data.serialization.JsonSerializerException;
import core.data.serialization.JsonSerializerInterface;
import filesystem.FileManager;
import filesystem.FileSystemException;
import filesystem.PathManager;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Manel
 */
@Injectable
public class MasterForm extends javax.swing.JFrame implements MasterFormInterface, MasterSlaveLink {

    private final BarcodeContainerFactory containerFactory;
    List<BarcodeContainerInterface> barcodeContainers;
    private final JsonSerializerInterface jsonSerializer;
    private final BarcodeContainerSerializer barcodeContainerSerializer;
    private final PathManager pathManager;
    private final FileManager fileManager;

    /**
     * Creates new form MasterForm
     *
     * @param containerFactory
     * @param jsonSerializer
     * @param barcodeContainerSerializer
     * @param pathManager
     * @param fileManager
     */
    public MasterForm(BarcodeContainerFactory containerFactory, JsonSerializerInterface jsonSerializer, BarcodeContainerSerializer barcodeContainerSerializer, PathManager pathManager, FileManager fileManager) {
        initComponents();
        this.containerFactory = containerFactory;
        barcodeContainers = new ArrayList();
        this.jsonSerializer = jsonSerializer;
        this.barcodeContainerSerializer = barcodeContainerSerializer;
        this.pathManager = pathManager;
        this.fileManager = fileManager;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Barcoder");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Add barcode container");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Save current setup");
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setText("Exit");
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("Load setup");
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addContainer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (pathManager.setPathFor(PathManager.Keys.SetupWritePath)) {
                saveSetup();
            }
        } catch (SetupException ex) {
            Logger.getLogger(MasterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            loadSetup();
        } catch (SetupException ex) {
            Logger.getLogger(MasterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        exit();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addContainer() {
        BarcodeContainerInterface barcodeContainer = containerFactory.getNewInstance();
        barcodeContainer.registerMaster(this);
        barcodeContainers.add(barcodeContainer);
        barcodeContainer.setVisible(true);
    }

    @Override
    public void saveSetup() throws SetupException {
        Configuration configuration = new Configuration();
        configuration.containers = barcodeContainerSerializer.saveBarcodesInFileSystemAndGetContainers(barcodeContainers);

        try {
            fileManager.outputStringToFile(jsonSerializer.serializeObject(configuration), pathManager.getPathFor(PathManager.Keys.SetupWritePath), Constants.MANAGER_FILE_NAME, FileManager.FileExtensions.Json);
        } catch (JsonSerializerException | FileSystemException ex) {
            throw new SetupException(ex);
        }
    }

    @Override
    public void exit() {
        for (BarcodeContainerInterface barcodeContainer : barcodeContainers) {
            ((JFrame) barcodeContainer).dispose();
        }

        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void onStartup() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void deleteContainer(BarcodeContainerInterface container) {
        barcodeContainers.remove(container);
        ((JFrame) container).dispose();
    }

    @Override
    public void loadSetup() throws SetupException {
        pathManager.setPathFor(PathManager.Keys.SetupReadPath);
        Path setupPath = pathManager.getPathFor(PathManager.Keys.SetupReadPath);
        if (setupPath == null) {
            return;
        }

        File masterFile = Paths.get(setupPath.toString(), Constants.MANAGER_FILE_NAME_AND_EXTENSION).toFile();
        if (!masterFile.exists()) {
            return;
        }

        try {
            Configuration setupConfiguration = jsonSerializer.deserializeString(fileManager.readStringFromFile(masterFile), Configuration.class);
            List<BarcodeContainerInterface> barcodeContainersFromFileSystem = barcodeContainerSerializer.getBarcodeContainersFromFileSystem(setupConfiguration.containers);
            barcodeContainers.addAll(barcodeContainersFromFileSystem);

            for (BarcodeContainerInterface barcodeContainer : barcodeContainers) {
                barcodeContainer.setVisible(true);
                barcodeContainer.registerMaster(this);
            }
        } catch (FileSystemException | JsonSerializerException | BarcodeGenerationException ex) {
            throw new SetupException(ex);
        }
    }
}
