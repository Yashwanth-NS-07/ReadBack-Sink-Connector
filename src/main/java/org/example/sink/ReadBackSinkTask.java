package org.example.sink;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.util.Collection;
import java.util.Map;

public class ReadBackSinkTask extends SinkTask {

    ReadBackSinkConfig config;
    @Override
    public String version() {
        return "";
    }

    @Override
    public void start(Map<String, String> props) {
        config = new ReadBackSinkConfig(props);
    }

    @Override
    public void put(Collection<SinkRecord> collection) {

    }

    @Override
    public void stop() {

    }
}
