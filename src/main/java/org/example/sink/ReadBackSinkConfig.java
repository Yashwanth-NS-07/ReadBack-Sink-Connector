package org.example.sink;

import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.util.DatabaseDialectRecommender;
import io.confluent.connect.jdbc.util.JdbcCredentialsProvider;
import io.confluent.connect.jdbc.util.JdbcCredentialsProviderValidator;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class ReadBackSinkConfig extends AbstractConfig {


    public static final String CONNECTION_URL = JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG;
    private static final String CONNECTION_URL_DOC =
            "JDBC connection URL.\n"
                    + "For example: ``jdbc:oracle:thin:@localhost:1521:orclpdb1``, "
                    + "``jdbc:mysql://localhost/db_name``, "
                    + "``jdbc:sqlserver://localhost;instance=SQLEXPRESS;"
                    + "databaseName=db_name``";
    private static final String CONNECTION_URL_DISPLAY = "JDBC URL";

    public static final String CONNECTION_USER = JdbcSourceConnectorConfig.CONNECTION_USER_CONFIG;
    private static final String CONNECTION_USER_DOC = "JDBC connection user.";
    private static final String CONNECTION_USER_DISPLAY = "JDBC User";

    public static final String CONNECTION_PASSWORD =
            JdbcSourceConnectorConfig.CONNECTION_PASSWORD_CONFIG;
    private static final String CONNECTION_PASSWORD_DOC = "JDBC connection password.";
    private static final String CONNECTION_PASSWORD_DISPLAY = "JDBC Password";

    public static final String CONNECTION_ATTEMPTS =
            JdbcSourceConnectorConfig.CONNECTION_ATTEMPTS_CONFIG;
    private static final String CONNECTION_ATTEMPTS_DOC =
            JdbcSourceConnectorConfig.CONNECTION_ATTEMPTS_DOC;
    private static final String CONNECTION_ATTEMPTS_DISPLAY =
            JdbcSourceConnectorConfig.CONNECTION_ATTEMPTS_DISPLAY;
    public static final int CONNECTION_ATTEMPTS_DEFAULT =
            JdbcSourceConnectorConfig.CONNECTION_ATTEMPTS_DEFAULT;

    public static final String CONNECTION_BACKOFF =
            JdbcSourceConnectorConfig.CONNECTION_BACKOFF_CONFIG;
    private static final String CONNECTION_BACKOFF_DOC =
            JdbcSourceConnectorConfig.CONNECTION_BACKOFF_DOC;
    private static final String CONNECTION_BACKOFF_DISPLAY =
            JdbcSourceConnectorConfig.CONNECTION_BACKOFF_DISPLAY;
    public static final long CONNECTION_BACKOFF_DEFAULT =
            JdbcSourceConnectorConfig.CONNECTION_BACKOFF_DEFAULT;

    public static final String DIALECT_NAME_CONFIG = "dialect.name";
    private static final String DIALECT_NAME_DISPLAY = "Database Dialect";
    public static final String DIALECT_NAME_DEFAULT = "";
    private static final String DIALECT_NAME_DOC =
            "The name of the database dialect that should be used for this connector. By default this "
                    + "is empty, and the connector automatically determines the dialect based upon the "
                    + "JDBC connection URL. Use this if you want to override that behavior and use a "
                    + "specific dialect. All properly-packaged dialects in the JDBC connector plugin "
                    + "can be used.";

    public static final String TABLE_NAME_FORMAT = "table.name.format";
    private static final String TABLE_NAME_FORMAT_DEFAULT = "${topic}";
    private static final String TABLE_NAME_FORMAT_DOC =
            "A format string for the destination table name, which may contain '${topic}' as a "
                    + "placeholder for the originating topic name.\n"
                    + "For example, ``kafka_${topic}`` for the topic 'orders' will map to the table name "
                    + "'kafka_orders'.";
    private static final String TABLE_NAME_FORMAT_DISPLAY = "Table Name Format";

    public static final String MAX_RETRIES = "max.retries";
    private static final int MAX_RETRIES_DEFAULT = 5;
    private static final String MAX_RETRIES_DOC =
            "The maximum number of times to retry on errors before failing the task.";
    private static final String MAX_RETRIES_DISPLAY = "Maximum Retries";

    public static final String RETRY_BACKOFF_MS = "retry.backoff.ms";
    private static final int RETRY_BACKOFF_MS_DEFAULT = 3000;
    private static final String RETRY_BACKOFF_MS_DOC =
            "The time in milliseconds to wait following an error before a retry attempt is made.";
    private static final String RETRY_BACKOFF_MS_DISPLAY = "Retry Backoff (millis)";

    public static final String BATCH_SIZE = "batch.size";
    private static final int BATCH_SIZE_DEFAULT = 3000;
    private static final String BATCH_SIZE_DOC =
            "Specifies how many records to attempt to batch together for insertion into the destination"
                    + " table, when possible.";
    private static final String BATCH_SIZE_DISPLAY = "Batch Size";

    public static final String CREDENTIALS_PROVIDER_CONFIG_PREFIX =
            JdbcSourceConnectorConfig.CREDENTIALS_PROVIDER_CONFIG_PREFIX;
    public static final String CREDENTIALS_PROVIDER_CLASS_CONFIG =
            JdbcSourceConnectorConfig.CREDENTIALS_PROVIDER_CLASS_CONFIG;
    public static final Class<? extends JdbcCredentialsProvider> CREDENTIALS_PROVIDER_CLASS_DEFAULT =
            JdbcSourceConnectorConfig.CREDENTIALS_PROVIDER_CLASS_DEFAULT;

    public static final String CREDENTIALS_PROVIDER_CLASS_DISPLAY =
            JdbcSourceConnectorConfig.CREDENTIALS_PROVIDER_CLASS_DISPLAY;

    public static final String CREDENTIALS_PROVIDER_CLASS_DOC =
            JdbcSourceConnectorConfig.CREDENTIALS_PROVIDER_CLASS_DOC;


    private static final String CONNECTION_GROUP = "Connection";

    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(
            CONNECTION_URL,
            ConfigDef.Type.STRING,
            ConfigDef.NO_DEFAULT_VALUE,
            ConfigDef.Importance.HIGH,
            CONNECTION_URL_DOC,
            CONNECTION_GROUP,
            1,
            ConfigDef.Width.LONG,
            CONNECTION_URL_DISPLAY
        )
        .define(
            CONNECTION_USER,
            ConfigDef.Type.STRING,
            null,
            ConfigDef.Importance.HIGH,
            CONNECTION_USER_DOC,
            CONNECTION_GROUP,
            2,
            ConfigDef.Width.MEDIUM,
            CONNECTION_USER_DISPLAY
        )
        .define(
            CONNECTION_PASSWORD,
            ConfigDef.Type.PASSWORD,
            null,
            ConfigDef.Importance.HIGH,
            CONNECTION_PASSWORD_DOC,
            CONNECTION_GROUP,
            3,
            ConfigDef.Width.MEDIUM,
            CONNECTION_PASSWORD_DISPLAY
        ).define(
            CREDENTIALS_PROVIDER_CLASS_CONFIG,
            ConfigDef.Type.CLASS,
            CREDENTIALS_PROVIDER_CLASS_DEFAULT,
            new JdbcCredentialsProviderValidator(),
            ConfigDef.Importance.LOW,
            CREDENTIALS_PROVIDER_CLASS_DOC,
            CONNECTION_GROUP,
            4,
            ConfigDef.Width.LONG,
            CREDENTIALS_PROVIDER_CLASS_DISPLAY
      ).define(
            DIALECT_NAME_CONFIG,
            ConfigDef.Type.STRING,
            DIALECT_NAME_DEFAULT,
            DatabaseDialectRecommender.INSTANCE,
            ConfigDef.Importance.LOW,
            DIALECT_NAME_DOC,
            CONNECTION_GROUP,
            5,
            ConfigDef.Width.LONG,
            DIALECT_NAME_DISPLAY,
            DatabaseDialectRecommender.INSTANCE
        )
        .define(
            CONNECTION_ATTEMPTS,
            ConfigDef.Type.INT,
            CONNECTION_ATTEMPTS_DEFAULT,
            ConfigDef.Range.atLeast(1),
            ConfigDef.Importance.LOW,
            CONNECTION_ATTEMPTS_DOC,
            CONNECTION_GROUP,
            6,
            ConfigDef.Width.SHORT,
            CONNECTION_ATTEMPTS_DISPLAY
        ).define(
            CONNECTION_BACKOFF,
            ConfigDef.Type.LONG,
            CONNECTION_BACKOFF_DEFAULT,
            ConfigDef.Importance.LOW,
            CONNECTION_BACKOFF_DOC,
            CONNECTION_GROUP,
            7,
            ConfigDef.Width.SHORT,
            CONNECTION_BACKOFF_DISPLAY
        );

    public ReadBackSinkConfig(Map<?, ?> props) {
        super(CONFIG_DEF, props);
    }
}
