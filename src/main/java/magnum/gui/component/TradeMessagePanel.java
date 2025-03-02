package magnum.gui.component;

import magnum.MessageListener;
import magnum.PricingDataOuterClass;
import magnum.eventbus.MessageEventBus;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;

public class TradeMessagePanel extends JPanel implements MessageListener {
    private JTable table;
    private JScrollPane scrollPane;
    private TradeTableModel tradeTableModel;

    public TradeMessagePanel() {
        setLayout(new BorderLayout());

        table = new JTable();
        table.setEnabled(false);

        table.putClientProperty("FlatLaf.styleClass", "h3.regular");

        tradeTableModel = new TradeTableModel();
        table.setModel(tradeTableModel);
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(4).setCellRenderer(dtcr);

        scrollPane = new JScrollPane(table);
        scrollPane.setAutoscrolls(true);
        add(scrollPane);

        // Register the panel as a listener
        MessageEventBus.getInstance().registerListener(this);
    }

    @Override
    public void onMessage(PricingDataOuterClass.PricingData pricingData) {
        SwingUtilities.invokeLater(() -> {
            tradeTableModel.addTrade(pricingData);
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        });
    }
}
