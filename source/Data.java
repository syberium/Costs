package source;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class RowAscDataComparator implements Comparator<RowData> {
    @Override
    public int compare(RowData t, RowData t1) {
        if (t.equals(t1)) return 0;
        if ((t.getCathegory() == t1.getCathegory()) && (t.getSubcathegory() == t1.getSubcathegory()))
            if (t.getDate().after(t1.getDate())) return 1; else return -1;
        if (t.getCathegory() == t1.getCathegory()) 
            if (t.getSubcathegory() > t1.getSubcathegory()) return 1; else return -1;
        if (t.getCathegory() > t1.getCathegory()) return 1; else return -1;
    }
}
class RowDescDataComparator implements Comparator<RowData> {
    @Override
    public int compare(RowData t, RowData t1) {
        if (t.equals(t1)) return 0;
        if ((t.getCathegory() == t1.getCathegory()) && (t.getSubcathegory() == t1.getSubcathegory()))
            if (t.getDate().after(t1.getDate())) return -1; else return 1;
        if (t.getCathegory() == t1.getCathegory()) 
            if (t.getSubcathegory() > t1.getSubcathegory()) return -1; else return 1;
        if (t.getCathegory() > t1.getCathegory()) return -1; else return 1;
    }
}
class RowAscDateComparator implements Comparator<RowData> {
    @Override
    public int compare(RowData t, RowData t1) {
        String s, s1;
        s = DateUtil.dateToString(t.getDate());
        s1 = DateUtil.dateToString(t1.getDate());
        int r = s.compareTo(s1);
        return r;
    }
}
class RowDescDateComparator implements Comparator<RowData> {
    @Override
    public int compare(RowData t, RowData t1) {
        String s, s1;
        s = DateUtil.dateToString(t.getDate());
        s1 = DateUtil.dateToString(t1.getDate());
        int r = s.compareTo(s1)*(-1);
        return r;
    }
}

class RowData implements Serializable{
    public static final long serialVersionUID = 1001L;
    private int cathegory;
    private int subcathegory;
    private Calendar date;
    private float cost;
    private String comment;
    public RowData(int i, int i1, Calendar d, float f) {
        cathegory = i;
        subcathegory = i1;
        date = d;
        cost = f;
        comment = "";
        Library.update();
    }
    public RowData(int i, int i1, Calendar d, float f, String c) {
        cathegory = i;
        subcathegory = i1;
        date = d;
        cost = f;
        comment = c;
        Library.update();
    }
    public int getCathegory() { return cathegory; }
    public void setCathegory(int cathegory) { this.cathegory = cathegory; }

    public int getSubcathegory() { return subcathegory; }
    public void setSubcathegory(int subcathegory) { this.subcathegory = subcathegory; }

    public Calendar getDate() { return date; }
    public void setDate(Calendar date) { this.date = date; }

    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    public String print() {
        return DateUtil.dateFullString(date)
                + ", " + Library.getCat(cathegory) 
                + " : " + Library.getSubCat(cathegory, subcathegory)
                + (comment.length() == 0? "" : new String(" (").concat(comment).concat(")")) 
                + ", " + String.format(Locale.US,"%.2f", cost);
    }
    public String shortPrint() {
        return DateUtil.dateShortString(date)
                + ", " + Library.getSubCat(cathegory, subcathegory)
                + ": " + String.format(Locale.US,"%.2f", cost)
                + (comment.length() == 0? "" : new String(" (").concat(comment).concat(")")) ;
    }    
    public RowData(RowData r) {
        cathegory = r.getCathegory();
        subcathegory = r.getSubcathegory();
        date = r.getDate();
        cost = r.getCost();
        comment = r.getComment();
        Library.update();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RowData other = (RowData) obj;
        if (this.cathegory != other.cathegory) {
            return false;
        }
        if (this.subcathegory != other.subcathegory) {
            return false;
        }
        if ( !((date.get(Calendar.YEAR) == other.date.get(Calendar.YEAR)) && 
                (date.get(Calendar.DAY_OF_YEAR) == other.date.get(Calendar.DAY_OF_YEAR))) ) {
            return false;
        }
        if ( !comment.equals(other.comment) ) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.cathegory;
        hash = 97 * hash + this.subcathegory;
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Float.floatToIntBits(this.cost);
        return hash;
    }

}

class Library {
    private static TreeMap<Integer, String> h = new TreeMap<Integer, String>();
    public static void update() {
        //Категории
        h.put(1, "Необходимое");
        h.put(2, "Удобства");
        h.put(3, "Здоровье");
        h.put(4, "Развлечения");
        h.put(5, "Отдых");
        h.put(6, "Праздники");
        h.put(7, "Дом. животные");
        h.put(8, "Алиса");
        h.put(9, "Работа");
        //Подкатегории
        h.put(101, "Квартплата");
        h.put(102, "Интернет");
        h.put(103, "Телефон");
        h.put(104, "Обед");
        h.put(105, "Продукты");
        h.put(106, "Одежда");
        h.put(107, "Товары для дома");
        h.put(108, "Пошлины, налоги");
        h.put(109, "Проезд");        
        
        h.put(201, "Мебель");
        h.put(202, "Посуда");
        h.put(203, "Бытовая химия");
        h.put(204, "Гигиена");
        h.put(205, "Косметика");
        h.put(206, "Ремонт");
        h.put(207, "Аксессуары");
        h.put(208, "ПК-компоненты");
        h.put(209, "Такси");
        h.put(210, "Бытовая техника");
        
        h.put(301, "Аптека");
        h.put(302, "Анализы");
        h.put(303, "Лечение");
        h.put(304, "Контрацепция");
        
        h.put(401, "Кафе");
        h.put(402, "Кино");
        h.put(403, "Алкоголь");
        h.put(404, "Еда на дом");
        h.put(405, "Игры и т.п.");
        h.put(406, "Спорт");
        h.put(407, "Интим-товары");
        h.put(408, "Лотереи");
        h.put(409, "Сауна");
        h.put(410, "Кальян и тп");
        
        h.put(501, "Отпуск");
        h.put(502, "Путешествия");
        h.put(503, "Питание");
        
        h.put(601, "Новый год");
        h.put(602, "Праздничный стол");
        h.put(603, "Подарки");
        h.put(604, "Дни рождения");
        h.put(605, "Траурные события");
        
        h.put(701, "Питание");
        h.put(702, "Здоровье");
        h.put(703, "Гигиена");
        
        h.put(801, "Лекарства");
        h.put(802, "Одежда");
        h.put(803, "Гигиена");
        h.put(804, "Игрушки");
        h.put(805, "Питание");
        h.put(806, "Детский сад");
        
        h.put(901, "Праздники");
        h.put(902, "Корпоративы");
        h.put(903, "Подарки");
        h.put(904, "Стол");
        h.put(905, "Чай и сладкое");
        h.put(906, "Командировки");
    }
    public static String getSubCat(int c, int s) {
        int res = c*100 + s;
        return h.getOrDefault(res, "UNKNOWN id=" + Integer.toString(s));
    }
    public static String getCat(int c) {
        return h.getOrDefault(c, "UNKNOWN id=" + Integer.toString(c));
    }
    public static boolean validate(int c, int s) {
        if (h.get(c) == null) return false;
        if (h.get(c*100 +s) == null) return false;
        return true;
    }
    public static String[] getAllCathegories() {
        ArrayList<String> res = new ArrayList<String>();
        Set<Integer> s = h.keySet();
        Iterator<Integer> it = s.iterator();
        int i;
        while(it.hasNext()) {
            i = it.next();
            if (i > 99) break;
            res.add(h.get(i));
        }
        String[] result = new String[res.size()];
        for(int j = 0; j < res.size(); j++ ) {
            result[j] = res.get(j);
        }
        return result;
    }
    public static String[] getAllSubCathegories(int cat) {
        ArrayList<String> res = new ArrayList<String>();
        Set<Integer> s = h.keySet();
        Iterator<Integer> it = s.iterator();
        int i;
        while(it.hasNext()) {
            i = it.next();
            if (i < 100*cat) continue;
            if (i > 100*cat+99) break;
            res.add(h.get(i));
        }
        String[] result = new String[res.size()];
        for(int j = 0; j < res.size(); j++ ) {
            result[j] = res.get(j);
        }
        return result;
    }
    public static int getCatId(String name) {
        Set<Integer> s = h.keySet();
        Iterator<Integer> it = s.iterator();
        int i;
        while(it.hasNext()) {
            i = it.next();
            if (i > 99) break;
            if (h.get(i).equals(name)) return i;
        }
        return 0;
    }
    public static int getSubCatId(String name, int cat) {
        Set<Integer> s = h.keySet();
        Iterator<Integer> it = s.iterator();
        int i;
        while(it.hasNext()) {
            i = it.next();
            if (i < 100*cat) continue;
            if (i > 100*cat+99) break;
            if (h.get(i).equals(name)) return i - 100*cat;
        }
        return 0;
    }
    public static int getCountCathegory() {
        Set<Integer> s = h.keySet();
        Iterator<Integer> it = s.iterator();
        int i;
        int count = 0;
        while(it.hasNext()) {
            i = it.next();
            if (i > 99) break;
            count++;
        }
        return count;
    }
    public static int getCountSubCathegory(int cat) {
        Set<Integer> s = h.keySet();
        Iterator<Integer> it = s.iterator();
        int i;
        int count = 0;
        while(it.hasNext()) {
            i = it.next();
            if (i < 100*cat) continue;
            if (i > 100*cat+99) break;
            count++;
        }
        return count;
    }
}

public class Data extends ArrayList<RowData> implements Serializable {
    
    public static final long serialVersionUID = 1001L;
    public static final boolean SORT_ASC = true, SORT_DESC = false;
    private static Logger log = Logger.getLogger(Data.class.getName());
    public Data() {
        super();
        Library.update();
    }
    public RowData getByCathegory(int c) {
        Iterator<RowData> it = iterator();
        RowData r;
        while (it.hasNext()) {
            r = it.next();
            if (r.getCathegory() == c) return r;
        }
        return null;
    }
    public RowData getBySubCathegory(int c, int s) {
        Iterator<RowData> it = iterator();
        RowData r;
        while (it.hasNext()) {
            r = it.next();
            if ((r.getCathegory() == c) && (r.getSubcathegory() == s)) return r;
        }
        return null;
    }
    public RowData getByDate(int i, int i1, int i2) {
        Iterator<RowData> it = iterator();
        RowData r;
        Calendar date = Calendar.getInstance();
        date.set(i, i1, i2);
        while (it.hasNext()) {
            r = it.next();
            if (r.getDate().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)) return r;
        }
        return null;
    }
    public Data getAllByCathegory(int c) {
        Iterator<RowData> it = iterator();
        RowData r;
        Data d = new Data();
        while (it.hasNext()) {
            r = it.next();
            if (r.getCathegory() == c) d.add(r);
        }
        return d;
    }
    public Data getAllBySubCathegory(int c, int s) {
        Iterator<RowData> it = iterator();
        RowData r;
        Data d = new Data();
        while (it.hasNext()) {
            r = it.next();
           if ((r.getCathegory() == c) && (r.getSubcathegory() == s)) d.add(r);
        }
        return d;
    }
    public Data getAllByPeriod(Calendar d1, Calendar d2) {
        Data d = new Data();
        RowData r;
        Iterator<RowData> it = iterator();
        while (it.hasNext()) {
            r = it.next();
            if (r.getDate().after(d1) && r.getDate().before(d2)) d.add(r);
        }
        return d;
    }
    public Data getAllAfterDate(Calendar d1) {
        Data d = new Data();
        RowData r;
        Iterator<RowData> it = iterator();
        while (it.hasNext()) {
            r = it.next();
            if (r.getDate().after(d1)) d.add(r);
        }
        return d;
    }
    public boolean addCure(RowData e) {
        Library.update();
        if (!Library.validate(e.getCathegory(), e.getSubcathegory())) return false;
        Iterator<RowData> it = iterator();
        RowData r;
        while (it.hasNext()) {
           r = it.next();
           if (r.equals(e)) {
               RowData r2 = new RowData(
                       r.getCathegory(), r.getSubcathegory(), 
                       (e.getDate().after(r.getDate())?e.getDate():r.getDate()), 
                       r.getCost() + e.getCost(), r.getComment()
               );
               it.remove();
               remove(e);
               log.log(Level.INFO, "Add new element : Succesfully merged 2 records");
               return super.add(r2);
           }
           
        }        
        return super.add(e); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public boolean add(RowData e) {
        Library.update();
        if (!Library.validate(e.getCathegory(), e.getSubcathegory())) return false;
        Iterator<RowData> it = iterator();
        RowData r;
        while (it.hasNext()) {
           r = it.next();
           if (r.equals(e)) {
               r.setCost(Float.parseFloat(
                       String.format(Locale.US, "%.2f", r.getCost() + e.getCost()) 
               ));
               if (e.getDate().after(r.getDate())) r.setDate(e.getDate());
               log.log(Level.INFO, "Add new element : Succesfully merged 2 records");
               return true;
           }
           
        }        
        return super.add(e); //To change body of generated methods, choose Tools | Templates.
    }
    public Data sortOrdinar(boolean type) {
        Data d = (Data)this.clone();
        if (type) {
            d.sort(new RowAscDataComparator());
        } else {
            d.sort(new RowDescDataComparator());
        }
        return d;
    }
    public Data sortByDate(boolean type) {
        Data d = (Data)this.clone();
        if (type) {
            //d.sort(new RowAscDateComparator());
        } else {
            d.sort(new RowDescDateComparator());
        }
        return d;
    }
    public void print() {
        Library.update();
        this.forEach((RowData r) -> System.out.println(r.print()));
    }
    public String getText() {
        Library.update();
        String s = "";
        Iterator<RowData> it = iterator();
        while (it.hasNext()) {
            s += it.next().print() + "\n";
        }
        return s;
    }
    public String getText(int count) {
        Library.update();
        String s = "";
        int i = 0;
        Iterator<RowData> it = iterator();
        while ((it.hasNext()) && (i++ < count)) {
            s += it.next().print() + "\n";
        }
        return s;
    }
    public String getShortText(int count) {
        Library.update();
        String s = "";
        int i = 0;
        Iterator<RowData> it = iterator();
        while ((it.hasNext()) && (i++ < count)) {
            s += it.next().shortPrint() + "\n";
        }
        return s;
    }    
    public String summary() {
        float sum = 0.0f;
        Iterator<RowData> it = iterator();
        while (it.hasNext()) {
            sum += it.next().getCost();
        }
        return String.format(Locale.US, "%.2f",sum);
    }
    public float summaryFloat() {
        float sum = 0.0f;
        Iterator<RowData> it = iterator();
        while (it.hasNext()) {
            sum += it.next().getCost();
        }
        return sum;
    }
    public void validate() {
        Library.update();
        Iterator<RowData> it = iterator();
        RowData r;
        while (it.hasNext()) {
           r = it.next();
           if (!Library.validate(r.getCathegory(), r.getSubcathegory())) it.remove();
           }
    }
    
    public Data cureFromDuplicates() {
        Data n = new Data();
        Iterator<RowData> it = iterator();
        RowData r;
        while(it.hasNext()) {
            r = it.next();
            n.addCure(r);
        }
        return n;
    }
    public void cureFromProblemsWithDate() {
        Data d = new Data();
        Iterator<RowData> it = iterator();
        RowData r;
        while(it.hasNext()) {
            r = it.next();
            d.add(r);
            try {
                //r.getDate();
                d.sortByDate(SORT_ASC);
            } catch(Exception e) {
                System.out.println("PROBLEM FOUND " + r.print());
                //Calendar date = 
                        System.out.println(DateUtil.dateShortString(r.getDate()));
                //r.setDate(date);
                break;
//                Calendar date = r.getDate();
//                r.setDate(date);
            }
        }
    }
}


