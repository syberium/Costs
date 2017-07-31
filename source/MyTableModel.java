package source;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MyTableModel implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private Data data;
    public MyTableModel(Data d) {
        data = d;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public String getColumnName(int i) {
        switch(i) {
            case 0: return "Дата";
            case 1: return "Категория"; 
            case 2: return "Подкатегория";
            case 3: return "Сумма";
            case 4: return "Комментарий";
            default: return "";
        }
    }
    @Override
    public Class<?> getColumnClass(int i) {
        switch(i) {
            case 0: return String.class;
            case 1: return String.class; 
            case 2: return String.class;
            case 3: return Float.class;
            case 4: return String.class;
            default: return String.class;
        }
    }
    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }
    @Override
    public Object getValueAt(int i, int i1) {
        RowData r = data.get(i);
        switch(i1) {
            case 0: return DateUtil.dateFullString(r.getDate());
            case 1: return Library.getCat(r.getCathegory()); 
            case 2: return Library.getSubCat(r.getCathegory(), r.getSubcathegory());
            case 3: return r.getCost();
            case 4: return r.getComment();
            default: return "";
        }
    }
    @Override
    public void setValueAt(Object o, int i, int i1) {
        RowData r = data.get(i);
        switch(i1) {
            case 0:
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM.yy");
                try {
                    cal.setTime(sdf.parse(o.toString()));
                } catch(ParseException e) {
                    System.err.println("Cannot parse this data");
                    o = DateUtil.dateFullString(r.getDate());
                }
                r.setDate(cal); 
                break;
            case 1: 
                //System.out.println(Integer.toString(i1) + " " + o.toString() + ", id = " + Integer.toString(Library.getCatId(o.toString()))); 
                break;
            case 2: 
                //System.out.println(Integer.toString(i1) + " " + o.toString() + ", id = " + Integer.toString(Library.getSubCatId(o.toString(), r.getCathegory()))); 
                break;
            case 3: 
//                System.out.println(Integer.toString(i1) + " " + o.toString()); 
                r.setCost(Float.parseFloat(o.toString()));
                break;
            case 4: r.setComment(o.toString()); break;
        }
    }
    @Override
    public void addTableModelListener(TableModelListener tl) {
        listeners.add(tl);
    }
    @Override
    public void removeTableModelListener(TableModelListener tl) {
        listeners.remove(tl);
    }
   
}
