package magnum.gui.component;

import magnum.PricingDataOuterClass;

import javax.swing.table.AbstractTableModel;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TradeTableModel extends AbstractTableModel {

    private final String[] columnNames = new String[] {"Symbol", "Price", "Change", "Change %", "Time"};
    private final List<PricingDataOuterClass.PricingData> trades = new ArrayList<>();

    @Override
    public int getRowCount() {
        return trades.size();
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
        PricingDataOuterClass.PricingData pricingData = trades.get(rowIndex);
        Instant instant = Instant.ofEpochSecond(pricingData.getTime() / 1000);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        String time = dateTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return switch (columnIndex) {
            case 0 -> pricingData.getId();
            case 1 -> String.format("%.4f", pricingData.getPrice());
            case 2 -> String.format("%.2f", pricingData.getChange());
            case 3 -> String.format("%.2f", pricingData.getChangePercent());
            case 4 -> time;
            default -> null;
        };
    }

    public void addTrade(PricingDataOuterClass.PricingData pricingData) {
        trades.add(pricingData);
        fireTableRowsInserted(trades.size() - 1, trades.size() - 1);
    }

    public void removeStock(int rowIndex) {
        trades.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
