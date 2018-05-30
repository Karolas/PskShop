package psk;

import psk.Utilities.Logger;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.entities.Account;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@Interceptor
@InterceptorLog
public class InterceptorLogImpl implements Serializable {

    @LoggedIn
    @Inject
    Account account;

    @Inject
    Logger logger;

    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        System.out.println("----Intercepted----");
        Date now = new Date();
        new Thread(() -> logger.log(ctx.getTarget().getClass().toString(), ctx.getMethod().getName(), now, account)).start();
        return ctx.proceed();
    }

}
