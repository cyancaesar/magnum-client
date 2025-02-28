package magnum;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import magnum.gui.ApplicationFrame;

public class Magnum {

    public static void main(String[] args) {
        ClientManager clientManager = ClientManager.getInstance();
        clientManager.initClients();
        clientManager.startClients();
        initAndStartGui();
    }

    public static void initAndStartGui() {
        FlatMacDarkLaf.setup();
        ApplicationFrame.getINSTANCE().bootstrap();
    }
}
