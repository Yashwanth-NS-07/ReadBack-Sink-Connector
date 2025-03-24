package org.example.sink;

import java.util.Map;

public class ReadBackSinkConfig {

    public enum InsertMode {
        INSERT,
        UPSERT,
        UPDATE;
    }
    public enum PrimaryKeyMode {
        NONE,
        //KAFKA, in future
        RECORD_KEY,
        RECORD_VALUE;
    }
    // core jdbc sink config
    public final String connectorName;
    public final String connectionUrl;
    public final String connectionUser;
    public final String connectionPassword;
    public final int connectionAttempts;
    public final long connectionBackoffMs;
    public final String tableNameFormat;
    public final int batchSize;
    public final int maxRetries;
    public final int retryBackoffMs;
    public final InsertMode insertMode;
    public final PrimaryKeyMode pkMode;

    /* in future
    public final boolean deleteEnabled;
    public final boolean replaceNullWithDefault;
    public final boolean autoCreate;
    public final boolean autoEvolve; */

    public ReadBackSinkConfig(Map<?, ?> props) {
        /* may be in future
        super(CONFIG_DEF, props); */
        connectorName = (String)props.get("name");
        this.connectionUrl = (String)props.get("connection.url");
        this.connectionUser = (String)props.get("connection.user");
        this.connectionPassword = (String)props.get("connection.password");
        this.connectionAttempts = Integer.parseInt((String)(props.containsKey("connection.attempts")
                ? props.get("connection.attempts")
                : "3"));
        this.connectionBackoffMs = Long.parseLong((String)(props.containsKey("connection.backoff.ms")
                ? props.get("connection.backoff.ms")
                : "10000"));
        this.tableNameFormat = (String)props.get("table.name.format");
        this.batchSize = Integer.parseInt((String)(props.containsKey("batch.size")
                ? props.get("batch.size")
                : "3000"));
        this.maxRetries = Integer.parseInt((String) (props.containsKey("max.retries")
                ? props.get("max.retries")
                : "10"));
        this.retryBackoffMs = Integer.parseInt((String) (props.containsKey("retry.backoff.ms")
                ? props.get("retry.backoff.ms")
                : "10"));
        this.insertMode = InsertMode.valueOf(((String)(props.containsKey("insert.mode")
                ? props.get("insert.mode")
                : "insert")).toUpperCase());
        this.pkMode = PrimaryKeyMode.valueOf(((String)(props.containsKey("pk.mode")
                ? props.get("pk.mode")
                : "none")).toUpperCase());
    }

}
