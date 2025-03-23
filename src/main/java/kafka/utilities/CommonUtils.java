package kafka.utilities;

import java.util.UUID;

/**
 * this class contains the common utilities that i will be using in the kafka project
 */
public final class CommonUtils {

    private CommonUtils(){}

    // Method to generate a new unique key
    public static String generateUniqueKey() {
        return "key-" + UUID.randomUUID();
    }

}
