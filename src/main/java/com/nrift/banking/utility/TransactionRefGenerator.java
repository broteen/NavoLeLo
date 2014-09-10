package com.nrift.banking.utility;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Class TransactionRefGenerator.
 */
public class TransactionRefGenerator {

    private AtomicInteger atomicInteger;  
    private static TransactionRefGenerator obj = null;

    /**
     * Instantiates a new transaction ref generator.
     *
     * @param initialValue the initial value
     */
    private TransactionRefGenerator(int initialValue){  
        this.atomicInteger = new AtomicInteger(initialValue);   
    }  

    /**
     * Gets the single instance of TransactionRefGenerator.
     *
     * @return single instance of TransactionRefGenerator
     */
    public static TransactionRefGenerator getInstance(){  
        if(obj == null){  
            obj = new TransactionRefGenerator(1);  
        }  
        return obj;  
    }  

    /**
     * Gets the counter.
     *
     * @return the counter
     */
    public int getCounter() {  
        return atomicInteger.getAndIncrement();  
    }     
}
