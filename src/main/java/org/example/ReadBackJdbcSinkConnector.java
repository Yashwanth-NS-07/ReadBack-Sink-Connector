package org.example;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.example.sink.ReadBackSinkTask;
import sun.java2d.marlin.Version;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadBackJdbcSinkConnector extends SinkConnector
{
    private static final Logger logger = LoggerFactory.getLogger(ReadBackSinkTask.class);
    private Map<String, String> configProps;

    @Override
    public void start(Map<String, String> props) {
        this.configProps = props;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        final List<Map<String, String>> configs = new ArrayList<>(maxTasks);
        for(int i = 0; i < maxTasks; i++) {
            configs.add(configProps);
        }
        return configs;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return ReadBackSinkTask.class;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return null;
    }

    @Override
    public String version() {
        return "1.0.0";
    }
}
