package com.module.flink.common.util;

import org.apache.flink.configuration.Configuration;

public class ConfigurationUtil {

    public static Configuration getConfiguration(Boolean isDebug){

        Configuration configuration = new Configuration();

        if(isDebug){
            int timeoutValue = 100000 ;
            String timeout = "100000 s" ;
            String timeoutHeartbeatPause = "1000000 s" ;
            configuration.setString("akka.ask.timeout",timeout); ;
            configuration.setString("akka.client.timeout",timeout) ;
            configuration.setString("akka.lookup.timeout",timeout) ;
            configuration.setString("akka.tcp.timeout",timeout) ;
            configuration.setInteger("web.timeout",timeoutValue) ;
            configuration.setString("akka.transport.heartbeat.interval",timeout) ;
            configuration.setString("akka.transport.heartbeat.pause",timeoutHeartbeatPause) ;
            configuration.setString("akka.watch.heartbeat.pause",timeout) ;
            configuration.setInteger("heartbeat.interval",10000000) ;
            configuration.setInteger("heartbeat.timeout",50000000) ;
        }


        return configuration ;
    }
}
