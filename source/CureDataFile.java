package source;

public class CureDataFile {
    public static void main(String[] args) {
         ReadAndWrite rnw = new ReadAndWrite("Cost.data");
         Data d = rnw.read();
         //d = d.cureFromDuplicates();
         
         rnw.write(d);
    }
}
