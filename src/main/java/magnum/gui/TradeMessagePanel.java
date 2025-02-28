package magnum.gui;

import magnum.MessageEventBus;
import magnum.MessageListener;
import magnum.PricingDataOuterClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TradeMessagePanel extends JPanel implements MessageListener {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public TradeMessagePanel() {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setFocusable(false);
        textArea.putClientProperty("FlatLaf.styleClass", "h2.regular");
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(),new EmptyBorder(10,10,10,10)));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        // Register the panel as a listener
        MessageEventBus.getInstance().registerListener(this);
    }

    @Override
    public void onMessage(PricingDataOuterClass.PricingData pricingData) {
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
            this.textArea.append("\n" + message);
        });
    }
}
