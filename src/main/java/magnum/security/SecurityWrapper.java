package magnum.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import magnum.Magnum;
import magnum.util.GZipDecompressor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class SecurityWrapper {
    private static SecurityWrapper INSTANCE;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Security> securities = new ArrayList<>();

    private SecurityWrapper() {}

    public static SecurityWrapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SecurityWrapper();
            INSTANCE.refreshSecurities(); // Refresh securities on lazy loading
        }
        return INSTANCE;
    }

    private List<Security> mapValues(String values) {
        try {
            return objectMapper.readValue(values, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest createRequest() {
        String url = Magnum.getProperty("url");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:134.0) Gecko/20100101 Firefox/134.0")
                .setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .setHeader("Cache-Control", "no-cache")
                .setHeader("Accept-Encoding", "gzip, deflate, br")
                .build();
        return request;
    }

    public void refreshSecurities() {
        try {
        List<Security> securities = HttpClient.newHttpClient()
                .sendAsync(this.createRequest(), HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(HttpResponse::body)
                .thenApply(GZipDecompressor::decompress)
                .thenApply(this::mapValues)
                .get();
        this.securities = securities;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public List<Security> getSecurities() {
        return securities;
    }

    public List<Security> getMainMarketSecurities() {
        return securities.stream()
                .filter(security -> security.getMarket_type().equals("M"))
                .sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getSymbol())))
                .collect(Collectors.toList());
    }
}
