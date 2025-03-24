package org.example.sink;

import org.apache.kafka.connect.sink.ErrantRecordReporter;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.util.Collection;
import java.util.Map;

public class ReadBackSinkTask extends SinkTask {

    //ErrantRecordReporter reporter; in future
    ReadBackSinkConfig config;
    int remainingRetries;
    boolean shouldTrimSensitiveLogs = true;

    @Override
    public String version() {
        return "";
    }

    @Override
    public void start(Map<String, String> props) {
        config = new ReadBackSinkConfig(props);
        intiwriter();
        remainingRetries = config.maxRetries;
        /* in future
        try {
            reporter = context.errantRecordReporter();
        } catch (Exception e) {
            reporter = null; // for kafka version less than 2.6
        }*/
    }
    void intiwriter() {

    }

    @Override
    public void put(Collection<SinkRecord> collection) {

    }

    @Override
    public void stop() {

    }
}
