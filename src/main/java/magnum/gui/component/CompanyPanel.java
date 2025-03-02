package magnum.gui.component;

import magnum.security.Security;
import magnum.security.SecurityWrapper;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import java.util.List;

public class CompanyPanel extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private CompanyTableModel companyTableModel;

    public CompanyPanel() {
        setLayout(new BorderLayout());

        List<Security> securities = SecurityWrapper.getInstance().getMainMarketSecurities();

        table = new JTable();
        table.putClientProperty("FlatLaf.styleClass", "h3.regular");

        companyTableModel = new CompanyTableModel(securities);
        table.setModel(companyTableModel);
        table.setFillsViewportHeight(true);

        table.setFocusable(false);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        table.getColumnModel().getColumn(3).setCellRenderer(dtcr);

        scrollPane = new JScrollPane(table);
        scrollPane.setAutoscrolls(true);
        add(scrollPane);
    }
}
