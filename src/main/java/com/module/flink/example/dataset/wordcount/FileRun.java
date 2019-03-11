package com.module.flink.example.dataset.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;
import scala.Tuple2;

public class FileRun {

    public static void main(String[] args) throws Exception {


        ExecutionEnvironment environment =ExecutionEnvironment.createLocalEnvironment(getConfiguration(true));

        DataSource<String> dataSource = environment.readTextFile("/opt/n_001_workspaces/bigdata/flink/flink-maven-java/src/main/resources/data/line.txt");

        dataSource.flatMap((FlatMapFunction<String, Tuple2<String,Integer>>) (value, out) -> {
            for (String word : value.split(" ")) {
                out.collect(new Tuple2<>(word, 1));
            }
        }).print();




    }






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
