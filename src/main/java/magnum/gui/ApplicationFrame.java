package magnum.gui;

import magnum.gui.component.ContentPane;
import magnum.gui.component.FooterPanel;
import magnum.gui.component.HeaderPanel;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.util.Objects;

public class ApplicationFrame extends JFrame {
    private static ApplicationFrame INSTANCE;

    private ApplicationFrame() {}

    public void bootstrap() {
        setTitle("Magnum");
        setSize(1200, 900);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        HeaderPanel headerPanel = new HeaderPanel();
        ContentPane contentPane = new ContentPane();
        FooterPanel footerPanel = new FooterPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(contentPane, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static synchronized ApplicationFrame getinstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new ApplicationFrame();
        }
        return INSTANCE;
    }
}
