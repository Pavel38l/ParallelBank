package ru.vsu.bank;

/**
 * @author Burdyug Pavel
 */
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(
                5,
                new CashBox(1000),
                new ClientsGenerator(100, 1000, 50)
        );
        bank.start();
    }
}




