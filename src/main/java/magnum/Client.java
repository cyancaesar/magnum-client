package magnum;

import com.google.protobuf.InvalidProtocolBufferException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Client extends WebSocketClient {
    private int id;
    private final List<String> tickers = new ArrayList<>();

    public Client(URI serverUri) {
        super(serverUri);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addTickers(List<String> tickers) {
        this.tickers.addAll(tickers);
    }

    public int numOfTickers() {
        return this.tickers.size();
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.printf("Client %d: Connection opened. Tickers: %d\n", id, numOfTickers());
        JSONObject message = new JSONObject();

        message.put("subscribe", tickers);
        send(message.toString());
    }

    @Override
    public void onMessage(String s) {
        try {
            // Decode Base64
            byte[] decoded = Base64.getDecoder().decode(s);
            // Parse the protobuf payload
            PricingDataOuterClass.PricingData pricingData = PricingDataOuterClass.PricingData.parseFrom(decoded);
            MessageEventBus.getInstance().emit(pricingData);
        } catch (InvalidProtocolBufferException e) {
            System.out.println(e);
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.printf("Client %d: Connection closed\n", id);
        reconnect();
    }

    @Override
    public void onError(Exception e) {
        System.out.printf("Client %d: Socket error\n", id);
    }
}
