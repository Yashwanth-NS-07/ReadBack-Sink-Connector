package org.example.sink;

import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.util.DatabaseDialectRecommender;
import io.confluent.connect.jdbc.util.DefaultJdbcCredentialsProvider;
import io.confluent.connect.jdbc.util.JdbcCredentialsProvider;
import io.confluent.connect.jdbc.util.JdbcCredentialsProviderValidator;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class ReadBackSinkConfigDisplay extends AbstractConfig{

    public static ConfigDef CONFIG_DEF = addConfigDef(new ConfigDef());

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

    public static final String SOURCE_DIALECT_NAME_CONFIG = "dialect.name";
    private static final String SOURCE_DIALECT_NAME_DISPLAY = "Database Dialect";
    public static final String SOURCE_DIALECT_NAME_DEFAULT = "";
    private static final String SOURCE_DIALECT_NAME_DOC =
            "The name of the database dialect that should be used for this connector. By default this "
                    + "is empty, and the connector automatically determines the dialect based upon the "
                    + "JDBC connection URL. Use this if you want to override that behavior and use a "
                    + "specific dialect. All properly-packaged dialects in the JDBC connector plugin "
                    + "can be used.";

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

    public static final String SOURCE_CREDENTIALS_PROVIDER_CONFIG_PREFIX = "source.jdbc.credentials.provider.";

    public static final String SOURCE_CREDENTIALS_PROVIDER_CLASS_CONFIG = SOURCE_CREDENTIALS_PROVIDER_CONFIG_PREFIX
            + "class";
    public static final Class<? extends JdbcCredentialsProvider> SOURCE_CREDENTIALS_PROVIDER_CLASS_DEFAULT =
            DefaultJdbcCredentialsProvider.class;

    public static final String SOURCE_CREDENTIALS_PROVIDER_CLASS_DISPLAY = "JDBC Credentials Provider Class";

    public static final String SOURCE_CREDENTIALS_PROVIDER_CLASS_DOC = "Credentials provider or provider "
            + "chain to use for authentication to database. By default the connector uses ``"
            + DefaultJdbcCredentialsProvider.class.getName() + "``.";


    private static final String SOURCE_CONNECTION_GROUP = "Source Connection";
    private static final String SOURCE_WRITES_GROUP = "Source Writes";


    public ReadBackSinkConfigDisplay(Map<?, ?> props) {
        super(CONFIG_DEF, props);
    }

    public static ConfigDef addConfigDef(ConfigDef configDef) {
        configDef.define(
            SOURCE_CONNECTION_URL,
            ConfigDef.Type.STRING,
            ConfigDef.NO_DEFAULT_VALUE,
            ConfigDef.Importance.HIGH,
            SOURCE_CONNECTION_URL_DOC,
            SOURCE_CONNECTION_GROUP,
            1,
            ConfigDef.Width.LONG,
            SOURCE_CONNECTION_URL_DISPLAY
        )
        .define(
            SOURCE_CONNECTION_USER,
            ConfigDef.Type.STRING,
            null,
            ConfigDef.Importance.HIGH,
            SOURCE_CONNECTION_USER_DOC,
            SOURCE_CONNECTION_GROUP,
            2,
            ConfigDef.Width.MEDIUM,
            SOURCE_CONNECTION_USER_DISPLAY
        )
        .define(
            SOURCE_CONNECTION_PASSWORD,
            ConfigDef.Type.PASSWORD,
            null,
            ConfigDef.Importance.HIGH,
            SOURCE_CONNECTION_PASSWORD_DOC,
            SOURCE_CONNECTION_GROUP,
            3,
            ConfigDef.Width.MEDIUM,
            SOURCE_CONNECTION_PASSWORD_DISPLAY
        )
        .define(
            SOURCE_CREDENTIALS_PROVIDER_CLASS_CONFIG,
            ConfigDef.Type.CLASS,
            SOURCE_CREDENTIALS_PROVIDER_CLASS_DEFAULT,
            new JdbcCredentialsProviderValidator(),
            ConfigDef.Importance.LOW,
            SOURCE_CREDENTIALS_PROVIDER_CLASS_DOC,
            SOURCE_CONNECTION_GROUP,
            4,
            ConfigDef.Width.LONG,
            SOURCE_CREDENTIALS_PROVIDER_CLASS_DISPLAY
        ).define(
            SOURCE_DIALECT_NAME_CONFIG,
            ConfigDef.Type.STRING,
            SOURCE_DIALECT_NAME_DEFAULT,
            DatabaseDialectRecommender.INSTANCE,
            ConfigDef.Importance.LOW,
            SOURCE_DIALECT_NAME_DOC,
            SOURCE_CONNECTION_GROUP,
            5,
            ConfigDef.Width.LONG,
            SOURCE_DIALECT_NAME_DISPLAY,
            DatabaseDialectRecommender.INSTANCE
        )
        .define(
            SOURCE_CONNECTION_ATTEMPTS,
            ConfigDef.Type.INT,
            SOURCE_CONNECTION_ATTEMPTS_DEFAULT,
            ConfigDef.Range.atLeast(1),
            ConfigDef.Importance.LOW,
            SOURCE_CONNECTION_ATTEMPTS_DOC,
            SOURCE_CONNECTION_GROUP,
            6,
            ConfigDef.Width.SHORT,
            SOURCE_CONNECTION_ATTEMPTS_DISPLAY
        ).define(
            SOURCE_CONNECTION_BACKOFF,
            ConfigDef.Type.LONG,
            SOURCE_CONNECTION_BACKOFF_DEFAULT,
            ConfigDef.Importance.LOW,
            SOURCE_CONNECTION_BACKOFF_DOC,
            SOURCE_CONNECTION_GROUP,
            7,
            ConfigDef.Width.SHORT,
            SOURCE_CONNECTION_BACKOFF_DISPLAY
        );
        return configDef;
    }

}
