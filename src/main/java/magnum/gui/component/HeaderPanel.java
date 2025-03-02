package magnum.gui.component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class HeaderPanel extends JPanel {

    public HeaderPanel () {
        GridLayout gridLayout = new GridLayout(0, 3);
        setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
}
