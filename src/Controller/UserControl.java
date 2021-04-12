package Controller;

import Database.Trainee;
import java.util.Date;

/**
 * This is the controller of trainee.
 * 
 * @author Norris Zhou
 * @version 1.0
 * @serial
 */

public class UserControl {
    Trainee trainee;

    /**
     * This is the constructor.
     */
    public UserControl() {
    }

    /**
     * Method to register. This method firstly get the parameters including user
     * name, password, email, age and sex. Secondly convert the sex into integer and
     * generate a user ID according to the system time. Thirdly pass the information
     * to the database and wait for a response. Finally according to the response,
     * generate a response to the client and return it to the Controller.
     * 
     * @param argv all String arguments including userName, password, email, age and
     *             sex
     * @return the response to the client from server
     */
    public String register(String... argv) {
        /**
         * If the number of arguments is not enough, the method will return an error
         * code with 1.
         */
        if (argv.length < 5) {
            return "error_code:1\nmsg:Missing parameter.";
        }

        /**
         * argv is expective as (String username, String password, String email, String
         * age, String sex). The age should include an integer. The sex should include 0
         * for male, 1 for female, or 2 for unkown. Parameter age and sex will be
         * converted into integers.
         */
        String userName = argv[0];
        String password = argv[1];
        String email = argv[2];
        int age = Integer.parseInt(argv[3]);
        int sex = 2; // This default value means "unknown".
        String userID = "";
        String response = "";

        /**
         * Convert the sex into an integer and generate the userID according to the
         * system time.
         */
        if (argv[4].equals("male")) {
            sex = 0;
        } else if (argv[4].equals("female")) {
            sex = 1;
        }
        userID = String.valueOf(new Date().getTime());

        System.out.println("userName: "+userName+"\npassword: ");
        /**
         * Pass these data to database and wait for a response from database.
         */
        trainee = new Trainee(userName, password, email, age, sex, userID);
        System.out.println("register: "+trainee.getUserID());
        if (trainee.getUserID().equals("-1")) {
            /**
             * userID = -1 means the user name has been used and this method will return an
             * error code with 2.
             */
			 System.out.println("failed register");
            response = "error_code:2\nmsg:The name has been already used.";
        } else {
            /**
             * If the userID is a normal String, the user successfully register. This method
             * will return an error code with 0.
             */
            System.out.println("sent");
            response = "error_code:0\nuserName:" + trainee.getUserName() + "\nemail:" + trainee.getEmail() + "\nAge:"
                    + trainee.getAge() + "\nsex:" + trainee.getSex() + "\nuserID:" + trainee.getUserID();
        }

        return response;
    }

    /**
     * Method for login. This method should get the parameters including userName
     * and password and then return a result.
     * 
     * @param argv all String arguments including userName and password
     * @return the response to the client from server
     */
    public String login(String... argv) {
        /**
         * If the number of arguments is not enough, the method will return an error
         * code with 1.
         */
        if (argv.length < 2) {
            return "error_code:1\nmsg:Missing parameter.";
        }

        /**
         * argv is expective as (String username, String password).
         */
        String userName = argv[0];
        String password = argv[1];

        String response = null;

        trainee = new Trainee(userName, password);
        if (trainee.getUserID().equals("-1")) {
            /**
             * userID = -1 means the user name does not exist and this method will return an
             * error code with 2.
             */
            response = "error_code:2\nmsg:This user does not exist.";
        } else if (trainee.getUserID().equals("-2")) {
            /**
             * userID = -2 means the password does not match and this method will return an
             * error code with 3.
             */
            response = "error_code:3\nmsg:The password dose not match.";
        } else {
            /**
             * If the userID is a normal String, the user successfully login. This method
             * will return an error code with 0.
             */
            response = "error_code:0\nuserName:" + trainee.getUserName();
        }

        return response;
    }
}