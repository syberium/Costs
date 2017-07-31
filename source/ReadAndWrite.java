package source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadAndWrite {
    private String fname;
    private static Logger log = Logger.getLogger(ReadAndWrite.class.getName());
    public ReadAndWrite(String file) {
        fname = file;
    }
    public boolean write(Data d) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(fname)))) {
            out.writeObject(d);
            log.log(Level.INFO, "Succesfull writing data in \"{0}\"", fname);
        } catch(IOException e) {
            log.log(Level.SEVERE, "Can't write data", e);
            return false;
        }
        return true;
    }
    public Data read() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(fname)))) {
            Data d;
            d = (Data)in.readObject();
            log.log(Level.INFO, "Succesfull reading data from \"{0}\"", fname);
            return d;
        } catch(IOException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Can't read data", e);
            return new Data();
        }
    }
}
