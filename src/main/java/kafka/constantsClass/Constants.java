package kafka.constantsClass;

public final class Constants {

    private Constants(){}

    public static final String BOOTSTRAP_SERVER = "bootstrap.servers";
    public static final String LOCALHOST_29092 = "localhost:29092";
    public static final String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String KEY_SERIALIZER = "key.serializer";
    public static final String VALUE_SERIALIZER = "value.serializer";
    public static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String KEY_DESERIALIZER = "key.deserializer";
    public static final String VALUE_DESERIALIZER = "value.deserializer";
    public static final String PARTITIONER_CLASS = "partitioner.class";
}
