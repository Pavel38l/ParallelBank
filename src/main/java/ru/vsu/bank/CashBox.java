package ru.vsu.bank;

/**
 * Bank cashbox
 * @author Burdyug Pavel
 */

public class CashBox {
    private double cash;

    public CashBox(double cash) {
        this.cash = cash;
    }

    public CashBox() {
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    /**
     * Withdraw money from cash box
     * @param sum money to withdraw from the cash box
     */
    public synchronized void withdraw(double sum) {
        if (sum > cash) {
            throw new RuntimeException("Not enough money at the box office");
        }
        cash -= sum;
    }

    /**
     * Put money in the cash box
     * @param sum money to put in the cash box
     */
    public synchronized void put(double sum) {
        cash += sum;
    }
}
