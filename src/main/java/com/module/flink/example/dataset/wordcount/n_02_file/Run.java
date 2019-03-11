package com.module.flink.example.dataset.wordcount.n_02_file;

import com.module.flink.common.util.ConfigurationUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

public class Run {

    public static void main(String[] args) throws Exception {


        ExecutionEnvironment env =ExecutionEnvironment.createLocalEnvironment(ConfigurationUtil.getConfiguration(true));
        env.setParallelism(1);

        DataSource<String> dataSource = env.readTextFile("/opt/n_001_workspaces/bigdata/flink/flink-maven-java/src/main/resources/data/line.txt");

        DataSet<Tuple2<String, Integer>> wordCounts = dataSource
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                        for (String word : value.split(" ")) {
                            out.collect(new Tuple2<String, Integer>(word, 1));
                        }
                    }
                })
                ;

        wordCounts.groupBy(0).sum(1)
                .print();




    }








}
