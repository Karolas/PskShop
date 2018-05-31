package psk.Utilities;

import psk.database.entities.Account;

import javax.enterprise.inject.Alternative;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Alternative
public class LoggerFile implements Logger, Serializable {
    public void log(String className, String methodName, Date logDate, Account account){
        FileOutputStream fos = null;
        try {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String fileName = "\\logs\\" + formatter.format(logDate) + ".log";
            fos = new FileOutputStream(System.getProperty("user.dir") + fileName, true);
            fos.write(("Date: " + logDate.toString()).getBytes());
            fos.write((" Class name: " + className).getBytes());
            fos.write((" Method name: " + methodName).getBytes());
            if(account == null) {
                fos.write(" User: not logged in".getBytes());
            } else {
                fos.write((" User email: " + account.getEmail()).getBytes());
                fos.write((" User role: " + account.getRole()).getBytes());
            }
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
