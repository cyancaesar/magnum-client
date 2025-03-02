package magnum.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class GZipDecompressor {
    public static String decompress(byte[] compressed) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed);
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            InputStreamReader reader = new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(reader);
            String decompressed = in.lines().collect(Collectors.joining("\n"));
            return decompressed;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
