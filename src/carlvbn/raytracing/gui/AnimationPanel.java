package carlvbn.raytracing.gui;

import carlvbn.raytracing.rendering.AnimationRenderer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AnimationPanel extends JPanel {
    public AnimationPanel(Viewport viewport, SettingsPanel settingsPanel) {
        JButton btnFirstPos;
        JButton btnSecondPos;
        JLabel lbLabel0;
        JSpinner spnFrameCount;
        JButton btnStart;
        JLabel lbLabel1;
        JSpinner spnResolution;

        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        setLayout(gbPanel0);

        btnFirstPos = new JButton("Set first position");
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(7,4,0,0);
        gbPanel0.setConstraints(btnFirstPos, gbcPanel0);
        add(btnFirstPos);

        btnSecondPos = new JButton("Set second position");
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(7,0,0,4);
        gbPanel0.setConstraints(btnSecondPos, gbcPanel0);
        add(btnSecondPos);

        lbLabel0 = new JLabel("Frames");
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 2;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,5,0,0);
        gbPanel0.setConstraints(lbLabel0, gbcPanel0);
        add(lbLabel0);

        spnFrameCount = new JSpinner();
        spnFrameCount.setModel(new SpinnerNumberModel(2, 2, 100000, 1));
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 2;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,0,0,0);
        gbPanel0.setConstraints(spnFrameCount, gbcPanel0);
        add(spnFrameCount);

        lbLabel1 = new JLabel("Output resolution (%)");
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 3;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,5,0,0);
        gbPanel0.setConstraints(lbLabel1, gbcPanel0);
        add(lbLabel1);

        spnResolution = new JSpinner();
        spnResolution.setModel(new SpinnerNumberModel(100, 1, 100, 1));
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 3;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,0,0,0);
        gbPanel0.setConstraints(spnResolution, gbcPanel0);
        add(spnResolution);

        btnStart = new JButton("Render image sequence");
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 2;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.SOUTH;
        gbcPanel0.insets = new Insets(7,4,4,4);
        gbPanel0.setConstraints(btnStart, gbcPanel0);
        add(btnStart);

        btnFirstPos.addActionListener(e -> AnimationRenderer.captureFirstPosition(viewport.getScene().getCamera()));
        btnSecondPos.addActionListener(e -> AnimationRenderer.captureSecondPosition(viewport.getScene().getCamera()));

        btnStart.addActionListener(e -> {
            File outputDir = new File("image_sequence");
            if (!outputDir.exists())
                outputDir.mkdir();

            try {
                AnimationRenderer.renderImageSequence(viewport.getScene(), outputDir, settingsPanel.getOutputWidth (), settingsPanel.getOutputHeight(), (int)spnFrameCount.getValue(), (int)spnResolution.getValue()/100F, viewport.isPostProcessingEnabled());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(viewport, ex.toString(), "Failed to save image sequence.", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}
