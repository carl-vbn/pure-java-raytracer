package carlvbn.raytracing.gui;

import carlvbn.raytracing.math.Vector3;
import carlvbn.raytracing.pixeldata.Color;
import carlvbn.raytracing.rendering.Renderer;
import carlvbn.raytracing.rendering.Scene;
import carlvbn.raytracing.rendering.Skybox;
import carlvbn.raytracing.solids.Plane;
import carlvbn.raytracing.solids.Sphere;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SettingsPanel extends JPanel {
    private int selectedSkyboxIndex;

    public SettingsPanel(Viewport viewport, JDialog animationDialog) {
        JSlider sdResolution;
        JLabel lbViewportRes;
        JLabel lbSensitivity;
        JSlider sdSensitivity;
        JLabel lbLightHA;
        JLabel lbLighVA;
        JSlider sdLightHA, sdLightVA;
        JLabel lbSpeed;
        JSlider sdSpeed;
        JLabel lbLightDist;
        JSlider sdLightDistance;
        JLabel lbFOV;
        JSlider sdFOV;
        JLabel lbSkybox, lbScene;
        JComboBox<String> cbScene, cbSkybox;
        JLabel lbOutRes;
        JSpinner spImageWidth, spImageHeight;
        JButton btnRenderImage, btnShowAnimationDialog;
        JCheckBox cbxPostProcessing;
        JLabel lbBloomRadius;
        JSlider sdBloomRadius;
        JLabel lbBloomIntensity;
        JSlider sdBloomIntensity;

        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbPanel0);

        lbViewportRes = new JLabel("Viewport resolution");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10,5,0,0);
        gbPanel0.setConstraints(lbViewportRes, gbc);
        this.add(lbViewportRes);

        sdResolution = new JSlider();
        sdResolution.setMinimum(1);
        sdResolution.setMaximum(100);
        sdResolution.setValue(25);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdResolution, gbc);
        this.add(sdResolution);

        lbSensitivity = new JLabel("Mouse sensitivity");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbSensitivity, gbc);
        this.add(lbSensitivity);

        sdSensitivity = new JSlider();
        sdSensitivity.setMinimum(1);
        sdSensitivity.setMaximum(100);
        sdSensitivity.setValue(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdSensitivity, gbc);
        this.add(sdSensitivity);

        lbSpeed = new JLabel("Movement speed");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(lbSpeed, gbc);
        this.add(lbSpeed);

        sdSpeed = new JSlider();
        sdSpeed.setMinimum(1);
        sdSpeed.setMaximum(200);
        sdSpeed.setValue(100);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdSpeed, gbc);
        this.add(sdSpeed);

        lbFOV = new JLabel("Field of vision");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbFOV, gbc);
        this.add(lbFOV);

        sdFOV = new JSlider();
        sdFOV.setMinimum(10);
        sdFOV.setMaximum(120);
        sdFOV.setValue(60);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdFOV, gbc);
        this.add(sdFOV);

        lbLightHA = new JLabel("Light horizontal angle");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10,5,0,0);
        gbPanel0.setConstraints(lbLightHA, gbc);
        this.add(lbLightHA);

        sdLightHA = new JSlider();
        sdLightHA.setMinimum(0);
        sdLightHA.setMaximum((int) (Math.PI*200));
        sdLightHA.setValue(sdLightHA.getMinimum());
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdLightHA, gbc);
        this.add(sdLightHA);

        lbLighVA = new JLabel("Light vertical angle");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbLighVA, gbc);
        this.add(lbLighVA);


        sdLightVA = new JSlider();
        sdLightVA.setMinimum(0);
        sdLightVA.setMaximum((int) (Math.PI*200));
        sdLightVA.setValue(sdLightVA.getMaximum());
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdLightVA, gbc);
        this.add(sdLightVA);

        lbLightDist = new JLabel("Light distance");
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbLightDist, gbc);
        this.add(lbLightDist);

        sdLightDistance = new JSlider();
        sdLightDistance.setMinimum(0);
        sdLightDistance.setMaximum(200);
        sdLightDistance.setValue(20);
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdLightDistance, gbc);
        this.add(sdLightDistance);

        lbScene = new JLabel("Scene");
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbScene, gbc);
        this.add(lbScene);

        lbSkybox = new JLabel("Skybox");
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbSkybox, gbc);
        this.add(lbSkybox);

        cbScene = new JComboBox<String>();
        cbScene.addItem("RGB Spheres");
        cbScene.addItem("RT Demo");
        cbScene.addItem("Mirror spheres");
        cbScene.addItem("Random spheres");
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,5,2);
        gbPanel0.setConstraints(cbScene, gbc);
        this.add(cbScene);

        cbSkybox = new JComboBox<String>();
        cbSkybox.addItem("Sky");
        cbSkybox.addItem("Studio");
        cbSkybox.addItem("Outdoor");
        cbSkybox.addItem("Factory");
        cbSkybox.addItem("Custom");
        gbc.gridx = 1;
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,2,5,5);
        gbPanel0.setConstraints(cbSkybox, gbc);
        this.add(cbSkybox);

        cbxPostProcessing = new JCheckBox("Post-processing");
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,1,5,0);
        gbPanel0.setConstraints(cbxPostProcessing, gbc);
        this.add(cbxPostProcessing);

        lbBloomRadius = new JLabel("Bloom radius");
        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbBloomRadius, gbc);
        this.add(lbBloomRadius);

        sdBloomRadius = new JSlider();
        sdBloomRadius.setMinimum(0);
        sdBloomRadius.setMaximum(50);
        sdBloomRadius.setValue(10);
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdBloomRadius, gbc);
        this.add(sdBloomRadius);

        lbBloomIntensity = new JLabel("Bloom intensity");
        gbc.gridx = 0;
        gbc.gridy = 19;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbBloomIntensity, gbc);
        this.add(lbBloomIntensity);

        sdBloomIntensity = new JSlider();
        sdBloomIntensity.setMinimum(0);
        sdBloomIntensity.setMaximum(100);
        sdBloomIntensity.setValue(50);
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdBloomIntensity, gbc);
        this.add(sdBloomIntensity);

        lbOutRes = new JLabel("Output resolution");
        gbc.gridx = 0;
        gbc.gridy = 21;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10,5,0,0);
        gbPanel0.setConstraints(lbOutRes, gbc);
        this.add(lbOutRes);

        SpinnerModel spImageWidthModel = new SpinnerNumberModel(1920, 0, 100000, 1);
        spImageWidth = new JSpinner(spImageWidthModel);
        spImageWidth.setEditor(new JSpinner.NumberEditor(spImageWidth, "#"));
        gbc.gridx = 0;
        gbc.gridy = 22;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(spImageWidth, gbc);
        this.add(spImageWidth);

        SpinnerModel spImageHeightModel = new SpinnerNumberModel(1080, 0, 100000, 1);
        spImageHeight = new JSpinner(spImageHeightModel);
        spImageHeight.setEditor(new JSpinner.NumberEditor(spImageHeight, "#"));
        gbc.gridx = 1;
        gbc.gridy = 22;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,5);
        gbPanel0.setConstraints(spImageHeight, gbc);
        this.add(spImageHeight);

        btnRenderImage = new JButton("Render image");
        gbc.gridx = 0;
        gbc.gridy = 23;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,4,0,4);
        gbPanel0.setConstraints(btnRenderImage, gbc);
        this.add(btnRenderImage);

        btnShowAnimationDialog = new JButton("Show animation dialog");
        gbc.gridx = 0;
        gbc.gridy = 24;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,4,4,4);
        gbPanel0.setConstraints(btnShowAnimationDialog, gbc);
        this.add(btnShowAnimationDialog);

        ChangeListener lightSettingsChangeListener =  e -> viewport.setLightAngle(sdLightHA.getValue()/100F, sdLightVA.getValue()/100F, sdLightDistance.getValue()/10F);

        lightSettingsChangeListener.stateChanged(null); // Update light angle

        sdLightHA.addChangeListener(lightSettingsChangeListener);
        sdLightVA.addChangeListener(lightSettingsChangeListener);
        sdLightDistance.addChangeListener(lightSettingsChangeListener);

        sdResolution.addChangeListener(e -> viewport.setResolution(sdResolution.getValue()/100F));

        sdSpeed.addChangeListener(e -> viewport.setMovementSpeed(sdSpeed.getValue()/100F));

        sdSensitivity.addChangeListener(e -> viewport.setMouseSensitivity(sdSensitivity.getValue()/100F));

        sdFOV.addChangeListener(e -> viewport.getScene().getCamera().setFOV(sdFOV.getValue()));

        cbScene.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Scene scene = viewport.getScene();
                switch (cbScene.getSelectedIndex()) {
                    case 0: // RGB Spheres
                        scene.clearSolids();
                        scene.addSolid(new Sphere(new Vector3(-1, 0, 0), 0.4F, Color.RED, 0.4F, 0F));
                        scene.addSolid(new Sphere(new Vector3(0, 0, 0), 0.4F, Color.GREEN, 0.4F, 0F));
                        scene.addSolid(new Sphere(new Vector3(1, 0, 0), 0.4F, Color.BLUE, 0.4F, 0F));

                        scene.addSolid(new Plane(-1F, new Color(0, 0, 0), true,0.25F, 0F));
                        break;
                    case 1: // RT Demo
                        scene.clearSolids();
                        scene.addSolid(new Sphere(new Vector3(0F, -0.6F, 0F), 0.4F, Color.WHITE, 0.2F, 0F));
                        scene.addSolid(new Sphere(new Vector3(1F, -0.7F, -0.5F), 0.3F, new Color(0.5F, 0, 1F), 0.2F, 0F));
                        scene.addSolid(new Sphere(new Vector3(1.5F, 0F, 1F), 1F, Color.RED, 0.2F, 0F));
                        scene.addSolid(new Sphere(new Vector3(2F, -0.65F, -0.7F), 0.35F, Color.GREEN, 0.2F, 1F));
                        scene.addSolid(new Sphere(new Vector3(-2F, -0.35F, -0.7F), 0.7F, Color.BLUE, 0.2F, 0F));

                        scene.addSolid(new Plane(-1F, new Color(0, 0, 0), true,0.25F, 0F));
                        break;
                    case 2: // Mirror spheres
                        scene.clearSolids();
                        for (int i = -5; i<=5; i++) {
                            for (int j = -5; j<=5; j++) {
                                scene.addSolid(new Sphere(new Vector3(i, 0, j), 0.4F, Color.WHITE, 1F, 0F));
                            }
                        }

                        scene.addSolid(new Plane(-1F, new Color(0, 0, 0), true,0.25F, 0F));
                        break;
                    case 3: // Random spheres
                        scene.clearSolids();
                        Random rand = new Random();
                        for (int i = 0; i<100; i++) {
                            scene.addSolid(new Sphere(new Vector3(rand.nextFloat()*20-10,rand.nextFloat()*20-10, rand.nextFloat()*20-10), rand.nextFloat(), new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()), rand.nextFloat(), rand.nextFloat()));
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        cbSkybox.addItemListener(e -> {
            int selectedIndex = cbSkybox.getSelectedIndex();

            if (e.getStateChange() == ItemEvent.SELECTED && selectedIndex != selectedSkyboxIndex) {
                Skybox skybox = viewport.getScene().getSkybox();
                if (skybox.isLoaded()) {
                    if (selectedIndex < cbSkybox.getItemCount()-1) {
                        skybox.reload(cbSkybox.getSelectedItem() +".jpg");
                        selectedSkyboxIndex = selectedIndex;
                    } else {
                        JFileChooser fc = new JFileChooser("Choose a Skybox (HDRI)");
                        fc.setFileFilter(new FileFilter() {
                            @Override
                            public boolean accept(File f) {
                                return f.isDirectory() || f.getName().endsWith("jpg") || f.getName().endsWith("jpeg") || f.getName().endsWith("png") || f.getName().endsWith("bmp");
                            }

                            @Override
                            public String getDescription() {
                                return "Equirectangular images (*.jpg, *.png, *.bmp)";
                            }
                        });

                        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        if (fc.showOpenDialog(viewport) == JFileChooser.APPROVE_OPTION) {
                            skybox.reloadFromFile(fc.getSelectedFile());
                            selectedSkyboxIndex = selectedIndex;
                        } else {
                            cbSkybox.setSelectedIndex(selectedSkyboxIndex);
                        }
                    }

                } else {
                    cbSkybox.setSelectedIndex(selectedSkyboxIndex);
                }
            }
        });

        btnRenderImage.addActionListener(e -> {
            try {
                viewport.renderToImage((int)spImageWidth.getValue(), (int)spImageHeight.getValue());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(viewport, ex.toString(), "Could not save image", JOptionPane.ERROR_MESSAGE);
            }
        });

        cbxPostProcessing.addChangeListener(e -> {
            boolean checked = cbxPostProcessing.isSelected();
            viewport.setPostProcessingEnabled(checked);

            sdBloomRadius.setEnabled(checked);
            sdBloomIntensity.setEnabled(checked);
            lbBloomRadius.setEnabled(checked);
            lbBloomIntensity.setEnabled(checked);

        });

        btnShowAnimationDialog.addActionListener(e ->  {
            animationDialog.setVisible(true);
            animationDialog.setLocationRelativeTo(viewport);
        });

        sdBloomRadius.addChangeListener(e -> Renderer.bloomRadius = sdBloomRadius.getValue());
        sdBloomIntensity.addChangeListener(e -> Renderer.bloomIntensity = sdBloomIntensity.getValue()/100F);

        sdBloomRadius.setEnabled(false);
        sdBloomIntensity.setEnabled(false);
        lbBloomRadius.setEnabled(false);
        lbBloomIntensity.setEnabled(false);
    }
}
