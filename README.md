# flink 开发笔记


### 创建flink maven java 项目

```aidl
mvn archetype:generate \
    -DarchetypeGroupId=org.apache.flink \
    -DarchetypeArtifactId=flink-quickstart-java \
    -DarchetypeVersion=1.7.1 \
    -DgroupId=com.opensourceteams \
    -DartifactId=flink-quickstart \
    -Dversion=0.1 \
    -Dpackage=com.module.flink.example.quickstart \
    -DinteractiveMode=false


```

### Java 版Flink WordCount单词统计
- https://github.com/opensourceteams/fink-maven-java/blob/master/md/wordCount-java.md
