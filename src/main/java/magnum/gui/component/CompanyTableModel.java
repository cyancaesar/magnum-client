package magnum.gui.component;

import magnum.security.Security;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CompanyTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] {"Symbol", "Trading Name (English)", "Trading Name (Arabic)", "Market Type"};
    private final List<Security> securities = new ArrayList<>();

    public CompanyTableModel(List<Security> securities) {
        this.securities.addAll(securities);
    }

    @Override
    public int getRowCount() {
        return securities.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Security security = securities.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> security.getSymbol();
            case 1 -> security.getTradingNameEn();
            case 2 -> security.getTradingNameAr();
            case 3 -> security.getMarket_type().contentEquals("M") ? "TASI" : security.getMarket_type();
            default -> null;
        };
    }

    public void addSecurity(Security security) {
        securities.add(security);
        fireTableRowsInserted(securities.size() - 1, securities.size() - 1);
    }

    public void removeSecurity(int rowIndex) {
        securities.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
