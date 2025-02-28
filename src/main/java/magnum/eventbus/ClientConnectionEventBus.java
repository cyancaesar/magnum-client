package magnum.eventbus;

import magnum.ClientConnectionListener;

import java.util.ArrayList;
import java.util.List;

public class ClientConnectionEventBus {
    private static final ClientConnectionEventBus INSTANCE = new ClientConnectionEventBus();
    private final List<ClientConnectionListener> listeners = new ArrayList<>();

    private ClientConnectionEventBus() {}

    public static ClientConnectionEventBus getInstance() {
        return INSTANCE;
    }

    /**
     * Register a consumer
     * @param listener
     */
    public void registerListener(ClientConnectionListener listener) {
        listeners.add(listener);
    }

    /**
     * Emit the number of tickers
     * that a client subscribed to
     * @param tickers
     */
    public void emit(int tickers) {
        listeners.forEach(listener -> listener.onConnect(tickers));
    }

}
