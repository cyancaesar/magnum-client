package magnum;

import java.util.LinkedHashSet;
import java.util.Set;

public class MessageEventBus {
    private static MessageEventBus instance = new MessageEventBus();
    private Set<MessageListener> listeners = new LinkedHashSet<>();

    private MessageEventBus() {}

    public static MessageEventBus getInstance() {
        return instance;
    }

    /**
     * Register a new consumer
     * @param listener
     */
    public void registerListener(MessageListener listener) {
        this.listeners.add(listener);
        System.out.println("Registered " + listener.getClass().getName());
    }

    /**
     * Emit an even to consumers
     * @param pricingData
     */
    public void emit(PricingDataOuterClass.PricingData pricingData) {
        this.listeners.forEach(listener -> listener.onMessage(pricingData));
    }

}
