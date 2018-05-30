package psk.Utilities;

import psk.database.entities.Account;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class LoggerFile implements Logger, Serializable {
    public void log(String className, String methodName, Date logDate, Account account){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(System.getProperty("user.dir") + "\\logs.log", true);
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
