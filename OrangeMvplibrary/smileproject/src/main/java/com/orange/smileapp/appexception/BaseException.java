package com.orange.smileapp.appexception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * 异常基类，练习自定义异常
 */
public class BaseException extends Exception{
    private static Logger logger=Logger.getLogger("BaseException");
    public BaseException(){

    }
    public BaseException(String err){
        super(err);
        StringWriter trace=new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    /**
     * 捕获其他的异常
     */
    protected void catchException(Exception e){
        StringWriter stringWriter=new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        logger.severe(stringWriter.toString());
    }
}
