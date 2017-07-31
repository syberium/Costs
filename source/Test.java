package source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.LogManager;

public class Test {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream(new File("logging.properties"))){
            LogManager.getLogManager().readConfiguration(in); 
        } catch(IOException e) {
            System.err.println("Could not setup logger configuration: " + e.getMessage());
        }
        
/*        Data d = new Data();
        d.add(new RowData(1, 2, Calendar.getInstance(), 54.7f));
        d.add(new RowData(2, 2, DateUtil.getDayAndTime(2014, 8, 9, 9, 55, 8), 3.49f));
        d.add(new RowData(2, 1, DateUtil.getDayAndTime(2015, 9, 8, 14, 10, 23), 124.7f));
        d.add(new RowData(1, 1, DateUtil.getDayAndTime(2016, 10, 1, 14, 10, 23), 4.7f));
        d.add(new RowData(2, 2, DateUtil.getDayAndTime(2015, 10, 21, 10, 13, 41), 450.0f));
        d.add(new RowData(3, 1, DateUtil.getDayAndTime(2015, 5, 31, 21, 12, 07), 800.99f));
        d.add(new RowData(2, 2, DateUtil.getDayAndTime(2014, 8, 9, 6, 22, 8), 4.49f));
        d.add(new RowData(1, 3, DateUtil.getDayAndTime(2014, 6, 28, 13, 01, 29), 1054.49f));
        
        System.out.println("Вывод начальных данных");
        d.forEach((RowData r) -> System.out.println(r.print()));
        System.out.println("------------------------------");
        
        rnw.write(d);*/
        ReadAndWrite rnw = new ReadAndWrite("Cost.data");
        Data d2;
        d2 = rnw.read();
        System.out.println("Вывод результата сохранения в файл и загрузки из файла");
//        if (d2 != null) d2.forEach((RowData r) -> System.out.println(r.print()));
//        else System.out.println("Can't read it :(");
        System.out.println("------------------------------");
        /*
        Data d3 = d2.getAllByCathegory(1);
        
        System.out.println("Выборка всех по категории");
        d3.forEach((RowData r)-> System.out.println(r.print()) );
        System.out.println("------------------------------");
        
        System.out.println("Выборка всех по датам");
        d2.getAllByPeriod(DateUtil.getDay(2015, 5, 1), DateUtil.getDay(2015, 11, 1)).print();
        System.out.println("------------------------------");
        
        System.out.println("Проверка классической сортировки: по возрастающей");
//        d2.sortStandart(true).print();
        d2.sortOrdinar(true).print();
        System.out.println("------------------------------");
        System.out.println("Проверка классической сортировки: по убывающей");
        d2.sortOrdinar(false).print();
        System.out.println("------------------------------");
        System.out.println("Проверка сортировки по дате: по возрастающей");
        d2.sortByDate(true).print();
        System.out.println("------------------------------");
        System.out.println("Проверка сортировки по дате: по убывающей");
        d2.sortByDate(false).print();
        System.out.println("------------------------------");
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        System.out.println(df.format(Calendar.getInstance().getTime()));
        for (String s : Library.getAllCathegories())
            System.out.println(s);
        System.out.println("------------------------------");
        for (String s : Library.getAllSubCathegories(3))
            System.out.println(s);
        System.out.println(String.format("%12s", Library.getCat(1)));
        System.out.println(String.format("%12s", Library.getCat(2)));
        System.out.println(String.format("%12s", Library.getCat(3)));
        System.out.println(new RowData(2, 2, DateUtil.getDayAndTime(2014, 8, 9, 9, 55, 8), 3.49f).equals(
                new RowData(2, 2, DateUtil.getDayAndTime(2014, 8, 9, 9, 55, 8), 8.49f)) );
        d2.add(new RowData(2, 2, DateUtil.getDayAndTime(2014, 8, 9, 9, 55, 8), 8.49f));
        d2.add(new RowData(2, 2, DateUtil.getDayAndTime(2014, 8, 9, 11, 55, 9), 12.00f));
        System.out.println(
                String.format(Locale.US,"%.2f", 3.496f)
        );
        
        System.out.println(
                new RowData(2,2,DateUtil.getDay(2014, 10, 22),75.2f).equals(
                        new RowData(2,2,DateUtil.getDay(2014, 10, 22),12.4f)
                )
        );
                */ 
        System.out.println(DateUtil.dateToString(Calendar.getInstance()));
        Calendar c = Calendar.getInstance();
        c.set(2016, 0, 24, 10, 24, 2);
        System.out.println(DateUtil.dateToString(c));
        System.out.println(DateUtil.dateToString(Calendar.getInstance()).compareTo(DateUtil.dateToString(c)));
        //rnw.write(d2);
    }
}
