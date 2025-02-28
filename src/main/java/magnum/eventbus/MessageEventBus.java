package magnum.eventbus;

import magnum.MessageListener;
import magnum.PricingDataOuterClass;

import java.util.LinkedHashSet;
import java.util.Set;

public class MessageEventBus {
    private static MessageEventBus INSTANCE = new MessageEventBus();
    private final Set<MessageListener> listeners = new LinkedHashSet<>();

    private MessageEventBus() {}

    public static MessageEventBus getInstance() {
        return INSTANCE;
    }

    /**
     * Register a new consumer
     * @param listener
     */
    public void registerListener(MessageListener listener) {
        listeners.add(listener);
        System.out.println("Registered " + listener.getClass().getName());
    }

    /**
     * Emit an even to consumers
     * @param pricingData
     */
    public void emit(PricingDataOuterClass.PricingData pricingData) {
        listeners.forEach(listener -> listener.onMessage(pricingData));
    }

}
