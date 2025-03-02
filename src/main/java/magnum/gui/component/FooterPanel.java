package magnum.gui.component;

import magnum.ClientManager;
import magnum.eventbus.ClientConnectionEventBus;
import magnum.ClientConnectionListener;
import magnum.eventbus.MessageEventBus;
import magnum.MessageListener;
import magnum.PricingDataOuterClass;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class FooterPanel extends JPanel implements MessageListener, ClientConnectionListener {
    private long counter;
    private long clients;
    private long tickers;
    private long volumes;
    private JLabel counterLabel;
    private JLabel tickersLabel;
    private JLabel clientsLabel;
    private JButton connectButton;
    private JButton disconnectButton;

    public FooterPanel () {
        setLayout(new BorderLayout());

        counter = 0L;
        clients = 0L;
        tickers = 0L;

        counterLabel = new JLabel("Trades: " + counter);
        clientsLabel = new JLabel("Clients: " + clients);
        tickersLabel = new JLabel("Tickers: " + tickers);
        counterLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");
        clientsLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");
        tickersLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");

        connectButton = new JButton("Connect");

        disconnectButton = new JButton("Disconnect");

        connectButton.putClientProperty("FlatLaf.styleClass", "h3.regular");
        disconnectButton.putClientProperty("FlatLaf.styleClass", "h3.regular");

        JPanel eastPanel = new JPanel(new FlowLayout());

        eastPanel.add(connectButton);
        eastPanel.add(disconnectButton);

        JPanel westPanel = new JPanel(new FlowLayout());
        westPanel.add(clientsLabel);
        westPanel.add(Box.createHorizontalStrut(30));
        westPanel.add(tickersLabel);
        westPanel.add(Box.createHorizontalStrut(30));
        westPanel.add(counterLabel);

        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);

        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(10,10,10,10)));

        // Register the panel as a listener
        MessageEventBus.getInstance().registerListener(this);
        ClientConnectionEventBus.getInstance().registerListener(this);

        connectButton.addActionListener(l -> ClientManager.getInstance().startClients());
        disconnectButton.addActionListener(l -> ClientManager.getInstance().stopClients());
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
