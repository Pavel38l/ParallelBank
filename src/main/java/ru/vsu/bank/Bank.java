package ru.vsu.bank;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bank
 * @author Burdyug Pavel
 */

@Getter
public class Bank {
    private List<Thread> tellers;
    private CashBox cashBox;
    private Thread generator;

    public Bank(int tellerCount, CashBox cashBox, ClientsGenerator generator) {
        List<Teller> tellers = new ArrayList<>();
        for (int i = 0;i < tellerCount;i++) {
            tellers.add(new Teller(String.format("Teller%d", i + 1), cashBox));
        }
        generator.setTellers(tellers);
        this.tellers = tellers.stream().map(Thread::new).collect(Collectors.toList());
        this.cashBox = cashBox;
        this.generator = new Thread(generator);
    }

    /**
     * Starts the bank
     */
    public void start() {
        for (Thread teller : tellers) {
            teller.start();
        }
        generator.start();
    }
}
