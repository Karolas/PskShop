package psk.Utilities;

import psk.database.entities.Account;

import java.util.Date;

public interface Logger {
   void log(String className, String methodName, Date logDate, Account account);
}
