package source;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class CatCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private Data d;
    private RowData r;

    public CatCellEditor(Data d) {
        this.d = d;
    }
    
    @Override
    public Object getCellEditorValue() {
        return Library.getCat(r.getCathegory());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        r = d.get(i);
        JComboBox<String> cat = new JComboBox<String>();
        for (String s : Library.getAllCathegories())
            cat.addItem(s);
        cat.setSelectedIndex(r.getCathegory() - 1);
        cat.addActionListener(this);
        if (bln) {
            cat.setBackground(jtable.getSelectionBackground());
        } else {
            cat.setBackground(jtable.getSelectionForeground());
        }
        return cat;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox<String> cat = (JComboBox<String>)ae.getSource();
        this.r.setCathegory(cat.getSelectedIndex() + 1);
        this.r.setSubcathegory(1);
    }
    
}
