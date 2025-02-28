package magnum;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientManager {

    private static final ClientManager INSTANCE = new ClientManager();
    private static final int CHUNK_SIZE = 100;
    private final Map<Integer, Client> clients = new HashMap<>();
    private final List<String> tickers = new ArrayList<>();

    private ClientManager() {}

    public void initClients() {
        try {
            Scanner scanner = new Scanner(new File("symbols.txt"));
            while (scanner.hasNextLine()) {
                tickers.add(scanner.nextLine());
            }
            scanner.close();

            for (int i = 0; i < tickers.size(); i += CHUNK_SIZE) {
                List<String> tickersChunkList = tickers.subList(i, Math.min(i + 100, tickers.size()));
                this.registerClient(tickersChunkList);
            }

        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerClient(List<String> tickers) throws URISyntaxException {
        int id = clients.size() + 1;
        Client client = new Client(new URI("wss://streamer.finance.yahoo.com"));
        client.setId(id);
        client.addTickers(tickers);
        clients.put(id, client);
    }

    public void startClients() {
        clients.forEach((key, client) -> client.connect());
    }

    public static ClientManager getInstance() {
        return INSTANCE;
    }
}
