package com.fixme.router;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.fixme.router.routing.RoutingTable;
import com.fixme.router.sockets.*;

import com.fixme.commons.database.*;


public class App {
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format","[%1$tF %1$tT] [\u001b[32;1mROUTER\u001b[0m] [%4$-7s] %5$s %n");
    }
    public static final RoutingTable routingTable = new RoutingTable();
    public static final ExecutorService executor = Executors.newFixedThreadPool(100);
    public static final Logger log = Logger.getLogger("Router");


    public static void main( String[] args ) {

        try {
            Database.InitialiseDB();
            MarketResults marketResults = Database.GetMarketByName("Crypto");
            // System.out.println(marketResults.toString());

        } catch (Exception e){
            System.out.println(e);
        }
        
        log.info("Router Application Start");
        executor.submit(new FixmeSocketServer(5000, "broker"));
        executor.submit(new FixmeSocketServer(5001, "market"));

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            log.warning("Interupt requested : " + ex.getMessage());
            System.exit(1);
        }
    }
}
