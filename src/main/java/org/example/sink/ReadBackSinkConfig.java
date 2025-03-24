package org.example.sink;

import java.util.Map;

public class ReadBackSinkConfig {

    // core jdbc sink config
    public final String connectorName;
    public final String connectionUrl;
    public final String connectionUser;
    public final String connectionPassword;
    public final int connectionAttempts;
    public final long connectionBackoffMs;
    public final String tableNameFormat;
    public final int batchSize;
    /* in future
    public final boolean deleteEnabled;
    public final boolean replaceNullWithDefault; */

    public ReadBackSinkConfig(Map<?, ?> props) {
        /* may be in future
        super(CONFIG_DEF, props); */
        this.connectorName = (String)props.get("name");
        this.connectionUrl = (String)props.get("connection.url");
        this.connectionUser = (String)props.get("connection.user");
        this.connectionPassword = (String)props.get("connection.password");
        this.connectionAttempts = Integer.parseInt((String)(props.containsKey("connection.attempts")? props.get("connection.attempts"): "3"));
        this.connectionBackoffMs = Long.parseLong((String)(props.containsKey("connection.backoff.ms")? props.get("connection.backoff.ms"): "10000"));
        this.tableNameFormat = (String)props.get("table.name.format");
        this.batchSize = Integer.parseInt((String)(props.containsKey("batch.size")? props.get("batch.size"): "3000"));
    }
}
