package magnum;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import magnum.gui.ApplicationFrame;

import javax.swing.UIManager;
import java.awt.Font;
import java.util.Properties;

public class Magnum {
    private static final Properties properties = new Properties();

    public static void main(String[] args) {
        if (args.length > 0) {
            properties.setProperty("url", args[0]);
        }
        else {
            System.out.println("URL is not provided");
            System.out.println("Use: java -jar magnum.jar <URL>");
            System.exit(1);
        }

        ClientManager clientManager = ClientManager.getInstance();
        clientManager.initClients();
        createAndShowGUI();
    }

    public static void createAndShowGUI() {
        FlatMacDarkLaf.setup();

        UIManager.put("TableHeader.font", new Font("Open Sans", Font.PLAIN, 15));
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.rowHeight", 32);

        ApplicationFrame.getinstance().bootstrap();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
