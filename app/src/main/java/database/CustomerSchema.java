package database;

/**
 * Created by mike on 9/10/17.
 */

public class CustomerSchema {

    // table name
    public static final class CustomerTable{
        public static final String NAME = "customers";

        // column descriptions
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String CUST_NAME = "name";
            public static final String PHONE_NUMBER = "phoneNumber";
            public static final String ADDRESS = "address";
            public static final String EMAIL = "email";
        }
    }
}
