package com.module.flink.example.dataset.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

public class Run {

    public static void main(String[] args) throws Exception {


        ExecutionEnvironment env =ExecutionEnvironment.createLocalEnvironment(getConfiguration(true));

        DataSet<String> text = env.fromElements(
                "Who's there?",
                "I think I hear them. Stand, ho! Who's there?");


        DataSet<Tuple2<String, Integer>> wordCounts = text
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                        for (String word : value.split(" ")) {
                            out.collect(new Tuple2<String, Integer>(word, 1));
                        }
                    }
                })
                ;

        wordCounts.groupBy(0).sum(1).print();




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
