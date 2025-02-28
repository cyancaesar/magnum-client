package magnum.gui;

import magnum.eventbus.MessageEventBus;
import magnum.MessageListener;
import magnum.PricingDataOuterClass;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TradeMessagePanel extends JPanel implements MessageListener {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public TradeMessagePanel() {
        setLayout(new BorderLayout());

        String[] columnNames = new String[] {"Symbol", "Price", "Change", "Change %", "Time"};

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable();
        scrollPane = new JScrollPane(table);
        table.setFocusable(false);
        table.putClientProperty("FlatLaf.styleClass", "h3.regular");

        table.setModel(model);
        table.setFillsViewportHeight(true);
        scrollPane.setAutoscrolls(true);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(4).setCellRenderer(dtcr);

        add(scrollPane);
        // Register the panel as a listener
        MessageEventBus.getInstance().registerListener(this);
    }

    @Override
    public void onMessage(PricingDataOuterClass.PricingData pricingData) {
//        model.addRow(new Object[] {"1", "2", "3", "4", "5"});
        // Get the id (symbol), price, change,
        // change in percent and time.
        String symbol = pricingData.getId();
        String price = String.format("%.2f", pricingData.getPrice());
        String change = String.format("%.2f", pricingData.getChange());
        String changePercent = String.format("%.2f", pricingData.getChangePercent());

        // The incoming message is in milliseconds
        // convert it to seconds;
        Instant instant = Instant.ofEpochSecond(pricingData.getTime() / 1000);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());

        LocalDate date = dateTime.toLocalDate();
        String time = dateTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

        String message = String.format("%s\tPrice  %s\tChange  %s\tChange%s  %s\t\t%s %s", symbol, price, change, "%" ,changePercent, date, time);

        SwingUtilities.invokeLater(() -> {
            model.addRow(new Object[] {symbol, price, change, changePercent, time});
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        });
    }
}
