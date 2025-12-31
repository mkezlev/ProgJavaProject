package game;

import java.util.Scanner;
import java.util.InputMismatchException;

// class UserInput
// methods
//  inputString
//  lengthConstrainedInputString
//  inputInteger
//  constrainedInputInteger

public class UserInput {
    private Scanner myScanner = new Scanner(System.in);

    /* Ask String input from user
    @Param userInfo : The message to the user
    @Param errorInfo: Message when the input is wrong
    #return user input
    */
    public String inputString (String userInfo, String errorInfo){
        boolean isString = false;
        String userInput="";
       System.out.print(userInfo + "\n");
        do
        {
            try {
                userInput = myScanner.next();
                isString = true;

            } catch (InputMismatchException e) {
                System.out.println(errorInfo);
                this.myScanner.next();
            }

        } while(!isString);
        return userInput;
    }

    /** Ask String at required length input from user
    * @param userInfo The message to the user
    * @param errorInfo Message when the input is wrong
    * @param length Max number characters user can provide
    * @return user input
    */

    public String lengthConstrainedInputString (String userInfo, String errorInfo, int length)
    {
        String userInput= inputString(userInfo, errorInfo);
        //while user input length is grater than length
        // ask user to enter input
        while (userInput.length()>length){
            userInput= inputString(userInfo, errorInfo);
        }
        return userInput;
    }

    /**
     * Ask integer input from user
     * @param userInfo The message to the user
     * @param errorInfo Message when the input is wrong
     * @return user input
     */
    public int inputInteger (String userInfo, String errorInfo){
        boolean isInteger = false;
        int userInput=0;
        System.out.print(userInfo + "\n");
        do
        {
            try {
                userInput = myScanner.nextInt();
                isInteger = true;

            } catch (InputMismatchException e) {
                System.out.println(errorInfo);
                this.myScanner.next();
            }

        } while(!isInteger);
        return userInput;
    }

    /* Ask integer input from user between min and max
@Param userInfo : The message to the user
@Param errorInfo: Message when the input is wrong
@min: minimum value user can enter
@max : Max value user can enter
#return user input
*/
    public int constrainedInputInteger (String userInfo, String errorInfo, int min, int max){
        int userInput= inputInteger(userInfo, errorInfo);
        while (userInput<=min || userInput>=max){
            userInput= inputInteger(userInfo, errorInfo);
        }
        return  userInput;
    }


    /* Ask integer at required length input from user
@Param userInfo : The message to the user
@Param errorInfo: Message when the input is wrong
@length: Max number characters user can provide
#return user input
*/
    public int lengthConstrainedInputInteger (String userInfo, String errorInfo, int length)
    {
        int userInput= inputInteger(userInfo, errorInfo);
        String str = Integer.toString(userInput);
        while (str.length()>length){
            userInput= inputInteger(userInfo, errorInfo);
            str = Integer.toString(userInput);
        }
        return userInput;
    }
}
