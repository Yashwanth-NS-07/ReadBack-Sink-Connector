package org.example.sink;

import io.confluent.connect.jdbc.sink.JdbcSinkConfig;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.slf4j.Logger;

public class ReadBackSinkConfig {

    JdbcSinkConfig jdbcSinkConfig;
    ConfigDef CONFIG_DEF;

    private static final String SOURCE_CONNECTION = "source.connection";

    public static final String SOURCE_CONNECTION_URL = SOURCE_CONNECTION + ".url";
    private static final String SOURCE_CONNECTION_URL_DOC =
            "JDBC connection URL.\n"
                    + "For example: ``jdbc:oracle:thin:@localhost:1521:orclpdb1``, "
                    + "``jdbc:mysql://localhost/db_name``, "
                    + "``jdbc:sqlserver://localhost;instance=SQLEXPRESS;"
                    + "databaseName=db_name``";
    private static final String SOURCE_CONNECTION_URL_DISPLAY = "JDBC URL";

    public static final String SOURCE_CONNECTION_USER = SOURCE_CONNECTION + ".user";
    private static final String SOURCE_CONNECTION_USER_DOC = "JDBC connection user.";
    private static final String SOURCE_CONNECTION_USER_DISPLAY = "JDBC User";

    public static final String SOURCE_CONNECTION_PASSWORD = SOURCE_CONNECTION + ".password";
    private static final String SOURCE_CONNECTION_PASSWORD_DOC = "JDBC connection password.";
    private static final String SOURCE_CONNECTION_PASSWORD_DISPLAY = "JDBC Password";

    public static final String SOURCE_CONNECTION_ATTEMPTS = SOURCE_CONNECTION + ".attempts";
    private static final String SOURCE_CONNECTION_ATTEMPTS_DOC =
            JdbcSourceConnectorConfig.CONNECTION_ATTEMPTS_DOC;
    private static final String SOURCE_CONNECTION_ATTEMPTS_DISPLAY =
            JdbcSourceConnectorConfig.CONNECTION_ATTEMPTS_DISPLAY;
    public static final int SOURCE_CONNECTION_ATTEMPTS_DEFAULT =
            JdbcSourceConnectorConfig.CONNECTION_ATTEMPTS_DEFAULT;

    public static final String SOURCE_CONNECTION_BACKOFF = SOURCE_CONNECTION + ".backoff.ms";
    private static final String SOURCE_CONNECTION_BACKOFF_DOC =
            JdbcSourceConnectorConfig.CONNECTION_BACKOFF_DOC;
    private static final String SOURCE_CONNECTION_BACKOFF_DISPLAY =
            JdbcSourceConnectorConfig.CONNECTION_BACKOFF_DISPLAY;
    public static final long SOURCE_CONNECTION_BACKOFF_DEFAULT =
            JdbcSourceConnectorConfig.CONNECTION_BACKOFF_DEFAULT;

    public static final String SOURCE_TABLE_NAME_FORMAT = "source.table.name.format";
    private static final String SOURCE_TABLE_NAME_FORMAT_DEFAULT = "${topic}";
    private static final String SOURCE_TABLE_NAME_FORMAT_DOC =
            "A format string for the destination table name, which may contain '${topic}' as a "
                    + "placeholder for the originating topic name.\n"
                    + "For example, ``kafka_${topic}`` for the topic 'orders' will map to the table name "
                    + "'kafka_orders'.";
    private static final String SOURCE_TABLE_NAME_FORMAT_DISPLAY = "Table Name Format";

    public static final String SOURCE_MAX_RETRIES = "source.max.retries";
    private static final int SOURCE_MAX_RETRIES_DEFAULT = 5;
    private static final String SOURCE_MAX_RETRIES_DOC =
            "The maximum number of times to retry on errors before failing the task.";
    private static final String SOURCE_MAX_RETRIES_DISPLAY = "Maximum Retries";

    public static final String SOURCE_RETRY_BACKOFF_MS = "source.retry.backoff.ms";
    private static final int SOURCE_RETRY_BACKOFF_MS_DEFAULT = 3000;
    private static final String SOURCE_RETRY_BACKOFF_MS_DOC =
            "The time in milliseconds to wait following an error before a retry attempt is made.";
    private static final String SOURCE_RETRY_BACKOFF_MS_DISPLAY = "Retry Backoff (millis)";

    public ReadBackSinkConfig(Map<?, ?> props) {
        this(props, JdbcSinkConfig.CONFIG_DEF);
        jdbcSinkConfig = new JdbcSinkConfig(props);
    }
    public ReadBackSinkConfig(Map<?, ?> props, ConfigDef sinkConfigDef) {

    }

}
