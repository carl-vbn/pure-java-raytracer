package carlvbn.raytracing.gui;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {
    public ProgressDialog(Component parent, String text, String title) {
        setTitle(title);
        setSize(400, 120);
        setLocationRelativeTo(parent);

        JLabel lbLbText;
        JProgressBar pbProgressBar;

        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        setLayout(gbPanel0);

        lbLbText = new JLabel(text);
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 0;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 5,10,0,0 );
        gbPanel0.setConstraints(lbLbText, gbcPanel0);
        add(lbLbText);

        pbProgressBar = new JProgressBar();
        pbProgressBar.setIndeterminate(true);
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 5,10,5,10 );
        gbPanel0.setConstraints(pbProgressBar, gbcPanel0);
        add(pbProgressBar);

        setModal(true);
        setVisible(true);
    }
}
