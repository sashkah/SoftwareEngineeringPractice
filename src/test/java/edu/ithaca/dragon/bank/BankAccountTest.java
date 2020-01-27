package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        //equivalence class - valid withdrawal, not a border case
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //equivalence class - invalid withdrawal (too large), not a border case
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        bankAccount2.withdraw(300);
        assertEquals(200, bankAccount.getBalance());

        //equivalence class - invalid withdrawal (too small), not a border case
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        bankAccount3.withdraw(-100);
        assertEquals(200, bankAccount.getBalance());
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
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}