package magnum.gui.component;


import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

public class ContentPane extends JPanel {
    private JTabbedPane tabbedPane;
    private TradeMessagePanel tradeMessagePanel;
    private CompanyPanel companyPanel;

    public ContentPane() {
        setLayout(new BorderLayout());

        tradeMessagePanel = new TradeMessagePanel();
        companyPanel = new CompanyPanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trades", tradeMessagePanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Companies", companyPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10,10,10,10)));
        add(tabbedPane);
    }

}
