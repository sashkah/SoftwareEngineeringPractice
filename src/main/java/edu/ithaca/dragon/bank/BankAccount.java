package edu.ithaca.dragon.bank;
import java.lang.Math;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     * @throws IllegalArgumentException if starting balance is negative or has more than 2 decimal places
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
        }
        else if (!isEmailValid(email)){
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        else if (!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if amount is negative or larger than balance, should throw an exception and state that the amount is invalid
     */
    public void withdraw (double amount)  {
        if(amount > balance || amount < 0) {
            throw new IllegalArgumentException("Amount is invalid, cannot withdraw.");
        }
        else if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount is invalid, cannot withdraw.");
        }
        else {
            balance -= amount;
        }

    }


    public static boolean isEmailValid(String email){
        //checks if email contains no '@' or if it contains an underscore, period, or hyphen directly before the '@'
        if (email.indexOf('@') == -1 || email.charAt(email.indexOf('@')-1) == '-' || email.charAt(email.indexOf('@')-1) == '.'|| email.charAt(email.indexOf('@')-1) == '_') {
            return false;
        }
        //checks if email contains a period followed by a period, underscore, or hyphen
        if(email.indexOf('.') != -1) {
            if(email.charAt(email.indexOf('.')+1) == '-' || email.charAt(email.indexOf('.')+1) == '.' || email.charAt(email.indexOf('.')+1) == '_') {
                return false;
            }
        }
        //checks if email contains two @ symbols in a row
        if(email.charAt(email.indexOf('@') + 1) == '@') {
            return false;
        }
        //checks if email contains a hyphen followed by a period, underscore, or hyphen
        if(email.indexOf('-') != -1) {
            if(email.charAt(email.indexOf('-')+1) == '-' || email.charAt(email.indexOf('-')+1) == '.' || email.charAt(email.indexOf('-')+1) == '_') {
                return false;
            }
        }
        //checks if email contains an underscore followed by a period, underscore, or hyphen
        if(email.indexOf('_') != -1) {
            if(email.charAt(email.indexOf('_')+1) == '-' || email.charAt(email.indexOf('_')+1) == '.' || email.charAt(email.indexOf('_')+1) == '_') {
                return false;
            }
        }
        //checks if email begins with a period, underscore, or hyphen
        if(email.charAt(0) == '.' || email.charAt(0) == '_' || email.charAt(0) == '-') {
            return false;
        }
        //checks if email contains an invalid character
        if(email.indexOf('!') != -1 || email.indexOf('#') != -1 || email.indexOf('$') != -1 || email.indexOf('%') != -1 || email.indexOf('%') != -1) {
            return false;
        }
        //checks if last portion of domain is missing a period
        if(email.indexOf('.', email.indexOf('@')) == -1) {
            return false;
        }
        //checks if last portion of domain contains more than one period
        if(email.charAt(email.indexOf('.',email.indexOf('@'))+1) == '.') {
            return false;
        }
        //checks if last portion of domain is shorter than two characters
        if(email.substring(email.lastIndexOf('.')+1).length() < 2) {
            return false;
        }
            return true;
    }


    /**
     * Takes a double amount and returns true if the amount is positive
     * and has two decimal points or less, and false otherwise.
     */
   public static boolean isAmountValid(double amount){
       double rounded = Math.round(amount *100.0)/100.0;
        if (amount < 0){
            return false;
        }
        else if (rounded != amount){
            return false;
       }
        else {return true;}
    }


    /**
     * @post increases the balance by amount if amount is non-negative
     * if amount is negative or invalid (decimals), should throw an exception and state that the amount is invalid
     */
    public void deposit (double amount)  {
        //TODO

    }

}
