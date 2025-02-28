package magnum.gui;

import magnum.eventbus.ClientConnectionEventBus;
import magnum.ClientConnectionListener;
import magnum.eventbus.MessageEventBus;
import magnum.MessageListener;
import magnum.PricingDataOuterClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FooterPanel extends JPanel implements MessageListener, ClientConnectionListener {
    private long counter;
    private long clients;
    private long tickers;
    private JLabel counterLabel;
    private JLabel tickersLabel;
    private JLabel clockLabel;
    private JLabel clientsLabel;

    public FooterPanel () {
        setLayout(new BorderLayout());

        counter = 0L;
        clients = 0L;
        tickers = 0L;

        counterLabel = new JLabel("Trades: " + counter);
        clockLabel = new JLabel("");
        clientsLabel = new JLabel("Clients: " + clients);
        tickersLabel = new JLabel("Tickers: " + tickers);
        clockLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");
        counterLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");
        clientsLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");
        tickersLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");


        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        new Timer(100, e -> {
            Calendar cal = Calendar.getInstance();
            String ti1 = df.format(cal.getTime());
            clockLabel.setText(ti1);
        }).start();

        JPanel westPanel = new JPanel(new FlowLayout());
        westPanel.add(clientsLabel);
        westPanel.add(Box.createHorizontalStrut(30));
        westPanel.add(tickersLabel);
        westPanel.add(Box.createHorizontalStrut(30));
        westPanel.add(counterLabel);

        add(westPanel, BorderLayout.WEST);
        add(clockLabel, BorderLayout.EAST);

        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(10,10,10,10)));
        // Register the panel as a listener
        MessageEventBus.getInstance().registerListener(this);
        ClientConnectionEventBus.getInstance().registerListener(this);
    }

    public void updateCounter() {
        this.counterLabel.setText("Trades: " + ++counter);
    }

    public void updateCounterLater() {
        SwingUtilities.invokeLater(this::updateCounter);
    }

    public void updateClients() {
        this.clientsLabel.setText("Clients: " + ++clients);
    }

    public void updateClientsLater() {
        SwingUtilities.invokeLater(this::updateClients);
    }

    public void updateTickers() {
        this.tickersLabel.setText("Tickers: " + tickers);
    }

    public void updateTickersLater() {
        SwingUtilities.invokeLater(this::updateTickers);
    }

    @Override
    public void onMessage(PricingDataOuterClass.PricingData pricingData) {
        updateCounterLater();
    }

    @Override
    public void onConnect(int tickers) {
        this.tickers += tickers;
        updateClientsLater();
        updateTickersLater();
    }
}
