package magnum.gui;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel () {
        GridLayout gridLayout = new GridLayout(0, 3);
        setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
}
