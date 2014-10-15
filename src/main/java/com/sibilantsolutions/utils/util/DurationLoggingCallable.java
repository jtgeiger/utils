package com.sibilantsolutions.utils.util;

import java.text.Format;
import java.text.NumberFormat;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DurationLoggingCallable<V> implements Callable<V>
{
    final static private Logger log = LoggerFactory.getLogger( DurationLoggingCallable.class );

    private Callable<V> target;
    private String debugId;

    static private Format format = NumberFormat.getInstance();

    public DurationLoggingCallable( Callable<V> target, String debugId )
    {
        this.target = target;
        this.debugId = debugId;
    }

    @Override
    public V call() throws Exception
    {
        if ( debugId != null )
            log.info( "Starting execution in thread={} for {}.", Thread.currentThread(), debugId );
        else
            log.info( "Starting execution in thread={}.", Thread.currentThread() );

        final long startMs = System.currentTimeMillis();

        V ret;

        try
        {
            ret = target.call();
        }
        catch ( Exception e )
        {
            long endMs = System.currentTimeMillis();

            long duration = endMs - startMs;

                //Goal: know WHERE the exception occurred (the cause) and WHO caught it (us).
            RuntimeException re = new RuntimeException( e );

            if ( debugId != null )
            {
                log.error( "Trouble running thread=" + Thread.currentThread() + " for " + debugId +
                        ", duration=" + format.format( duration ) + " ms:", re );
            }
            else
            {
                log.error( "Trouble running thread=" + Thread.currentThread() +
                        ", duration=" + format.format( duration ) + " ms:", re );
            }

            throw re;
        }

        long endMs = System.currentTimeMillis();

        long duration = endMs - startMs;

        if ( debugId != null )
        {
            log.info( "Finished execution in thread={} for {}, duration={} ms.",
                    Thread.currentThread(), debugId, format.format( duration ) );
        }
        else
        {
            log.info( "Finished execution in thread={}, duration={} ms.",
                    Thread.currentThread(), format.format( duration ) );
        }

        return ret;
    }

}
