package source;

import java.util.Calendar;
import java.util.Date;

public class Operator {
    private static Data mainData;
    public Operator(Data d) {
        mainData = d;
        Library.update();
    }
    public String getLastCosts(int i) {
        return mainData.sortByDate(false).getShortText(i);
    }
    public String getThisMonthSum() {
        Calendar c1, c2; 
        c2 = Calendar.getInstance();        
        c1 = new Calendar.Builder().setDate(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), 1).build();
        c2 = new Calendar.Builder().setDate(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1, 1).build();
        return mainData.getAllByPeriod(c1, c2).summary();
    }
    public String getLastMonthSum() {
        Calendar c1, c2; 
        c2 = Calendar.getInstance();        
        c1 = new Calendar.Builder().setDate(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) - 1, 1).build();
        c2 = new Calendar.Builder().setDate(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), 1).build();
        return mainData.getAllByPeriod(c1, c2).summary();
    }
    public String getLastYearMonthSum() {
        Calendar c1, c2; 
        c2 = Calendar.getInstance();        
        c1 = new Calendar.Builder().setDate(c2.get(Calendar.YEAR) - 1, c2.get(Calendar.MONTH), 1).build();
        c2 = new Calendar.Builder().setDate(c2.get(Calendar.YEAR) - 1, c2.get(Calendar.MONTH) + 1, 1).build();
        return mainData.getAllByPeriod(c1, c2).summary();
    }
    public static String[] getAllCathegories() {
        return Library.getAllCathegories();
    }
    public static String[] getAllSubCathegories(int i) {
        return Library.getAllSubCathegories(i);
    }
    public void addData(Calendar c, int cat, int subcat, float cost, String comment) {
        RowData r = new RowData(cat, subcat, c, cost, comment);
        mainData.add(r);
    }
    public Data getByMonthForTable(Date d) {
        Calendar c = new Calendar.Builder().setInstant(d).build();
        Calendar 
                c1 = DateUtil.getDay(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1),
                c2 = DateUtil.getDay(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1);
        return mainData.getAllByPeriod(c1, c2);
    }
    public Data getAll() {
        return mainData;
    }
    public void validate() {
        mainData.validate();
    }
    public void remove(Object r) {
        mainData.remove(r);
    }
    public String getStatisticCat(int year) {
        String s = "";
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.set(year, 0, 1, 0, 0, 0);
        c1.set(year, 11, 31, 23, 59, 59);
        for (int i = 1; i <= Library.getCountCathegory(); i++) {
            s += String.format("%15s : %s\n", 
                    Library.getCat(i), 
                    mainData.getAllByPeriod(c, c1).getAllByCathegory(i).summary());
        }
        return s;
    }
    public String getStatisticCat(int month, int year) {
        String s = "";
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        c1.set(year, month, 31, 23, 59, 59);
        for (int i = 1; i <= Library.getCountCathegory(); i++) {
            s += String.format("%15s : %s\n", 
                    Library.getCat(i), 
                    mainData.getAllByPeriod(c, c1).getAllByCathegory(i).summary());
        }
        return s;
    }
    public String getStatisticSubCat(int year) {
        String s = "";
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.set(year, 0, 1, 0, 0, 0);
        c1.set(year, 11, 31, 23, 59, 59);
        for (int i = 1; i <= Library.getCountCathegory(); i++) {
            s += Library.getCat(i) + " ( "+ mainData.getAllByPeriod(c, c1).getAllByCathegory(i).summary() +" )" + "\n";
            for (int j = 1; j <= Library.getCountSubCathegory(i); j++ ) {
                s += String.format("%16s : %s\n", 
                        Library.getSubCat(i, j), 
                        mainData.getAllByPeriod(c, c1).getAllBySubCathegory(i, j).summary());
            }
            if (i != Library.getCountCathegory()) s +="------------------------------\n";
        }
        return s;
    }
    public String getStatisticSubCat(int month, int year) {
        String s = "";
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        c1.set(year, month, 31, 23, 59, 59);
        for (int i = 1; i <= Library.getCountCathegory(); i++) {
            s += Library.getCat(i) + " ( "+ mainData.getAllByPeriod(c, c1).getAllByCathegory(i).summary() +" )" + "\n";
            for (int j = 1; j <= Library.getCountSubCathegory(i); j++ ) {
                s += String.format("%16s : %s\n", 
                        Library.getSubCat(i, j), 
                        mainData.getAllByPeriod(c, c1).getAllBySubCathegory(i, j).summary());
            }
            if (i != Library.getCountCathegory()) s +="------------------------------\n";
        }
        return s;
    }
    public float[] summaryFloatArray(Data d) {
        float[] a = new float[Library.getCountCathegory()];
        float f;
        for (int i = 0; i < a.length; i++) {
            f = d.getAllByCathegory(i+1).summaryFloat();
            a[i] = f;
        }
        return a;
    }
    public String[] legendText(Data d) {
        String[] res = getAllCathegories();
        float sum = d.summaryFloat();
        for (int i = 0; i < res.length; i++) {
            res[i] = String.format("%15s, %8s (%.2f%%)", res[i],
                    d.getAllByCathegory(i+1).summary(),
                    (d.getAllByCathegory(i+1).summaryFloat() / (sum==0?1:sum) * 100f) );
        }
        return res;
    }
    public Data getByMonth(int month, int year) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(year, month, 1, 0, 0, 0);
        c2.set(year, month, 31, 23, 59, 59);
        return mainData.getAllByPeriod(c1, c2);
    }
    public Data getByYear(int year) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(year, 0, 1, 0, 0, 0);
        c2.set(year, 11, 31, 23, 59, 59);
        return mainData.getAllByPeriod(c1, c2);
    }
}
