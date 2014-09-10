package com.nrift.banking.utility;

import java.util.concurrent.atomic.AtomicInteger;

public class TransactionRefGenerator {

    private AtomicInteger atomicInteger;  
    private static TransactionRefGenerator obj = null;  
    private TransactionRefGenerator(int initialValue){  
        this.atomicInteger = new AtomicInteger(initialValue);   
    }  

    public static TransactionRefGenerator getInstance(){  
        if(obj == null){  
            obj = new TransactionRefGenerator(1);  
        }  
        return obj;  
    }  

    public int getCounter() {  
        return atomicInteger.getAndIncrement();  
    }     
}
