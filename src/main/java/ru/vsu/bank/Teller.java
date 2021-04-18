package ru.vsu.bank;

import lombok.Getter;
import lombok.Setter;

import static java.lang.Thread.sleep;

/**
 * Teller
 * @author Burdyug Pavel
 */

@Getter
@Setter
public class Teller implements Runnable {
    private String name;
    private CashBox cashBox;
    private ClientBlockingQueue clients;

    public Teller(String name, CashBox cashBox) {
        this.name = name;
        this.cashBox = cashBox;
        this.clients = new ClientBlockingQueue();
    }

    public Teller() {
    }

    private void serviceClient(Client client) {
        System.out.printf(
                "Starting %s service %s %s %.2f%n",
                name,
                client.getName(),
                client.getOperation().toString(),
                client.getSum()
        );
        switch (client.getOperation()) {
            case PUT:
                cashBox.put(client.getSum());
                break;
            case WITHDRAW:
                try {
                    cashBox.withdraw(client.getSum());
                } catch (RuntimeException ex) {
                    System.out.printf(
                            "Finish %s service %s %s\nCash: %.2f%n",
                            name,
                            client.getName(),
                            "Refuse",
                            cashBox.getCash()
                    );
                    return;
                }
                break;
        }
        try {
            sleep(client.getServTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf(
                "Finish %s service %s %s\nCash: %.2f%n",
                name,
                client.getName(),
                "Ok",
                cashBox.getCash()
        );
    }

    @Override
    public void run() {
        while (true) {
            serviceClient(clients.next());
        }
    }
}
