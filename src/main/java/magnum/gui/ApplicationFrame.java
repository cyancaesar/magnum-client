package magnum.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ApplicationFrame extends JFrame {
    private static ApplicationFrame INSTANCE;

    private ApplicationFrame() {}

    public void bootstrap() {
        setTitle("Magnum");
        setSize(1200, 900);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        HeaderPanel headerPanel = new HeaderPanel();
        TradeMessagePanel bodyPanel = new TradeMessagePanel();
        FooterPanel footerPanel = new FooterPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static synchronized ApplicationFrame getinstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new ApplicationFrame();
        }
        return INSTANCE;
    }
}
