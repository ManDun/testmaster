import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @version 1.0 12/03/98
 */

class DecimalRenderer extends DefaultTableCellRenderer {
  DecimalFormat formatter;

  DecimalRenderer(String pattern) {
    this(new DecimalFormat(pattern));
  }

  DecimalRenderer(DecimalFormat formatter) {
    this.formatter = formatter;
    setHorizontalAlignment(JLabel.RIGHT);
  }

  public void setValue(Object value) {
    setText((value == null) ? "" : formatter.format(((Double) value)
        .doubleValue()));
  }
}

public class TotalRowExample extends JFrame {
  final private int TOTAL_ROW = 3;

  final private int TOTAL_COLUMN = 1;

  TotalRowExample() {
    super("Total Row Example");

    final DecimalFormat formatter = new DecimalFormat("###,##0.00");
    DefaultTableModel dm = new DefaultTableModel() {
      public void setValueAt(Object value, int row, int col) {
        Vector rowVector = (Vector) dataVector.elementAt(row);
        if (col == TOTAL_COLUMN) {
          Double d = null;
          if (value instanceof Double) {
            d = (Double) value;
          } else {
            try {
              d = new Double(((Number) formatter
                  .parse((String) value)).doubleValue());
            } catch (ParseException ex) {
              d = new Double(0.0);
            }
          }
          rowVector.setElementAt(d, col);
        } else {
          rowVector.setElementAt(value, col);
        }
      }

      public boolean isCellEditable(int row, int col) {
        if (row == TOTAL_ROW)
          return false;
        return true;
      }

      public Class getColumnClass(int col) {
        if (col == TOTAL_COLUMN)
          return Number.class;
        return String.class;
      }
    };

    dm.setDataVector(new Object[][] { { "coffee", new Double(0.0) },
        { "tea", new Double(0.0) }, { "cocoa", new Double(0.0) },
        { "total", new Double(0.0) } },
        new Object[] { "Item", "Price" });

    JTable table = new JTable(dm) {
      public void editingStopped(ChangeEvent e) {
        super.editingStopped(e);
        reCalcurate(getModel());
        repaint();
      }
    };

    table.getColumn("Price")
        .setCellRenderer(new DecimalRenderer(formatter));

    JScrollPane scroll = new JScrollPane(table);
    Container content = getContentPane();
    content.add(scroll);
    setSize(300, 120);
    setVisible(true);
  }

  private void reCalcurate(TableModel ml) {
    if (ml == null)
      return;
    double total = 0.0;
    for (int i = 0; i < TOTAL_ROW; i++) {
      total += ((Double) ml.getValueAt(i, TOTAL_COLUMN)).doubleValue();
    }
    ml.setValueAt(new Double(total), TOTAL_ROW, TOTAL_COLUMN);
  }

  public static void main(String[] args) {
    TotalRowExample frame = new TotalRowExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
}  
 
 
