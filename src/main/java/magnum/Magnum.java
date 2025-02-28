package magnum;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import magnum.gui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

public class Magnum {

    public static void main(String[] args) {
        ClientManager clientManager = ClientManager.getInstance();
        clientManager.initClients();
        clientManager.startClients();
        initAndStartGui();
    }

    public static void initAndStartGui() {
        FlatMacDarkLaf.setup();

        UIManager.put("TableHeader.font", new Font("Noto Sans", Font.PLAIN, 15));
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", true);

        ApplicationFrame.getinstance().bootstrap();
    }
}
