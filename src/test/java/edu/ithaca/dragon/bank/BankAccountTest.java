package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //valid equivalence class
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());
    }



    @Test
    void withdrawTest() {
        //valid withdrawal equivalence class - not a border case
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //valid withdrawal equivalence class - border case
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        bankAccount2.withdraw(200);
        assertEquals(0, bankAccount2.getBalance());

        //valid withdrawal equivalence class - border case
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        bankAccount3.withdraw(199);
        assertEquals(1, bankAccount3.getBalance());

        //valid withdrawal equivalence class - border case
        BankAccount bankAccount4 = new BankAccount("a@b.com", 200);
        bankAccount4.withdraw(0);
        assertEquals(200, bankAccount4.getBalance());

        //invalid withdrawal, too large equivalence class -  not a border case
        BankAccount bankAccount5 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount5.withdraw(300));
        assertEquals(200, bankAccount5.getBalance());

        //invalid withdrawal, too large equivalence class - border case
        BankAccount bankAccount6 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount6.withdraw(201));
        assertEquals(200, bankAccount6.getBalance());

        //invalid withdrawal, negative equivalence class -  not a border case
        BankAccount bankAccount7 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount7.withdraw(-100));
        assertEquals(200, bankAccount7.getBalance());

        //invalid withdrawal, negative equivalence class -  border case
        BankAccount bankAccount8 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount8.withdraw(-1));
        assertEquals(200, bankAccount8.getBalance());

        //valid decimal withdrawal equivalence class - not a border case
        BankAccount bankAccount9 = new BankAccount("a@b.com", 200);
        bankAccount9.withdraw(100.00);
        assertEquals(100, bankAccount9.getBalance());

        //valid decimal withdrawal equivalence class - border case
        BankAccount bankAccount10 = new BankAccount("a@b.com", 200);
        bankAccount10.withdraw(0.01);
        assertEquals(199.99, bankAccount10.getBalance());

        //invalid withdrawal, more than 2 decimal equivalence class -  not a border case
        BankAccount bankAccount11 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount11.withdraw(100.005));
        assertEquals(200, bankAccount11.getBalance());

        //invalid withdrawal, more than 2 decimal equivalence class -  border case
        BankAccount bankAccount12 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount12.withdraw(0.001));
        assertEquals(200, bankAccount12.getBalance());

        //invalid withdrawal, negative more than 2 decimal equivalence class -  not a border case
        BankAccount bankAccount13 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount13.withdraw(-100.005));
        assertEquals(200, bankAccount13.getBalance());

        //test withdrawl multiple times on one bank account
        BankAccount bankAccount14 = new BankAccount("a@b.com", 200);
        bankAccount14.withdraw(100);
        assertEquals(100, bankAccount14.getBalance());
        bankAccount14.withdraw(49.99);
        assertEquals(50.01, bankAccount14.getBalance());

    }


    @Test
    void isEmailValidTest(){
        //equivalence class - valid email
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //invalid entry
        assertFalse( BankAccount.isEmailValid(""));
        //equivalence class - contains exactly 1 @ symbol (border case)
        assertFalse(BankAccount.isEmailValid("a@@b.com"));
        //equivalence class - contains valid symbol, but right before @ sign (border case)
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //equivalence class - contains valid symbol, but more than one consecutively (border case)
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //equivalence class - contains valid symbol, but at beginning (border case)
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //equivalence class - contains invalid symbol (border case)
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        //equivalence class - contains invalid symbol
        assertFalse(BankAccount.isEmailValid("abc##def@mail.com"));
        //equivalence class - valid email (border case)
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        //equivalence class - valid email (border case)
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        //equivalence class - valid email (border case)
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
        //equivalence class - valid email (border case)
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        //equivalence class - valid email (border case)
        assertTrue( BankAccount.isEmailValid("abc.def@mail.cc"));
        //equivalence class - valid email (border case)
        assertTrue( BankAccount.isEmailValid("abc.def@mail-archive.com"));
        //equivalence class - valid email (border case)
        assertTrue( BankAccount.isEmailValid("abc.def@mail.com"));
        //equivalence class - last portion of domain is shorter than two characters (border case)
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //equivalence class - should be one period after @ sign (border case)
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        //equivalence class - should be one period after @ sign (border case)
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));


    }

    @Test
    void isAmountValidTest(){
        //Negative equivalence class, middle case
        assertFalse(BankAccount.isAmountValid(-100));
        //Negative equivalence class, border case
        assertFalse(BankAccount.isAmountValid(-1));
        //Valid equivalence class, border case
        assertTrue(BankAccount.isAmountValid(0));
        //Valid equivalence class, kind of also a border case? Confusing
        assertTrue(BankAccount.isAmountValid(1));
        //Valid equivalence class, middle case
        assertTrue(BankAccount.isAmountValid(100));
        //Valid equivalence class, border case
        assertTrue(BankAccount.isAmountValid(0.01));
        //Valid equivalence class, middle case
        assertTrue(BankAccount.isAmountValid(1000.90));
        //Over 2 decimal places equivalence class, border case
        assertFalse(BankAccount.isAmountValid(0.001));
        //Over 2 decimal places equivalence class, middle case
        assertFalse(BankAccount.isAmountValid(100.505));
        //Both decimal and negative??, border
        assertFalse(BankAccount.isAmountValid(-0.001));
    }


    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        /*
        CONFUSED FINISH THIS
        //Negative starting balance equivalence class, border case
        BankAccount bankAccount2 = new BankAccount("a@b.com", -1);
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
         */
    }

}

