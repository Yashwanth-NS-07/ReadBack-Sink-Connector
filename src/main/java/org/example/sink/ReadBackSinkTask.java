package org.example.sink;

import io.confluent.connect.jdbc.sink.JdbcSinkConfig;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialects;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

public class ReadBackSinkTask extends SinkTask {

    //ErrantRecordReporter reporter; in future
    DatabaseDialect dialect;
    ReadBackSinkConfig readBackSinkconfig;
    JdbcSinkConfig config;
    int remainingRetries;
    boolean shouldTrimSensitiveLogs = true;

    @Override
    public String version() {
        return "";
    }

    @Override
    public void start(Map<String, String> props) {
        readBackSinkconfig = new ReadBackSinkConfig(parse(props));
        config = new JdbcSinkConfig(props);
        intiwriter();
        remainingRetries = config.maxRetries;
        /* in future
        try {
            reporter = context.errantRecordReporter();// helps with DLQ
        } catch (Exception e) {
            reporter = null; // for kafka version less than 2.6
        }*/
    }

    void intiwriter() {
        if(config.dialectName != null && !config.dialectName.trim().isEmpty()) {
            dialect = DatabaseDialects.create(config.dialectName, config);
        }
    }

    public Map<String, String> parse(Map<String, String> props) {
        Map<String, String> newProps = new Hashtable<>();
        for(Map.Entry<String, String> entry: props.entrySet()) {
            String key = entry.getKey();
            if(key.contains("source.")) {
                newProps.put(key.substring(7), entry.getValue());
            }
        }
        return newProps;
    }

    @Override
    public void put(Collection<SinkRecord> collection) {

    }

    @Override
    public void stop() {

    }
}
