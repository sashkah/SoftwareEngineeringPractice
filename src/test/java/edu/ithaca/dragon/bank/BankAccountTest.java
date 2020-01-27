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
        //equivalence class - valid email (prefix and suffix valid), not a border case
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //invalid entry, no email
        assertFalse( BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid("a@@b.com"));
        //equivalence class - invalid email (invalid prefix, valid suffix) - not a border case?
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //equivalence class - invalid email(invalid prefix, valid suffix) - border case
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //equivalence class - invalid email(invalid prefix, valid suffix) - border case?
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //equivalence class - invalid email(invalid prefix, valid suffix) - border case?
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc##def@mail.com"));
        //equivalence class - valid email, border case
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        //equivalence class - valid email, not a border case
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        //equivalence class - valid email, not a border case
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
        //equivalence class - valid email, not a border case
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        //equivalence class - valid email, border case
        assertTrue( BankAccount.isEmailValid("abc.def@mail.cc"));
        //equivalence class - valid email, not a border case
        assertTrue( BankAccount.isEmailValid("abc.def@mail-archive.com"));
        //equivalence class - valid email, not a border case
        assertTrue( BankAccount.isEmailValid("abc.def@mail.org"));
        //equivalence class - valid email, not a border case
        assertTrue( BankAccount.isEmailValid("abc.def@mail.com"));
        //equivalence class - invalid email(valid prefix, invalid suffix) - border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //equivalence class - invalid email(valid prefix, invalid suffix) - not a border case?
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //equivalence class - invalid email(valid prefix, invalid suffix) - not a border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        //equivalence class - invalid email(valid prefix, invalid suffix - border case
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