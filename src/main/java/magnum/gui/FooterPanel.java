package magnum.gui;

import magnum.MessageEventBus;
import magnum.MessageListener;
import magnum.PricingDataOuterClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FooterPanel extends JPanel implements MessageListener {
    private long counter;
    private JLabel counterLabel;
    private JLabel clockLabel;

    public FooterPanel () {
        setLayout(new BorderLayout());

        counter = 0L;
        counterLabel = new JLabel("Trades: " + counter);
        clockLabel = new JLabel("");
        clockLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");
        counterLabel.putClientProperty("FlatLaf.styleClass", "h3.regular");

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        new Timer(100, e -> {
            Calendar cal = Calendar.getInstance();
            String ti1 = df.format(cal.getTime());
            clockLabel.setText(ti1);
        }).start();

        add(counterLabel, BorderLayout.WEST);
        add(clockLabel, BorderLayout.EAST);

        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(10,10,10,10)));
        // Register the panel as a listener
        MessageEventBus.getInstance().registerListener(this);
    }

    public void updateCounter() {
        this.counterLabel.setText("Trades: " + ++counter);
    }

    public void updateCounterLater() {
        SwingUtilities.invokeLater(this::updateCounter);
    }

    @Override
    public void onMessage(PricingDataOuterClass.PricingData pricingData) {
        updateCounterLater();
    }
}
