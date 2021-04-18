package ru.vsu.bank;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Generates a client and adds to the shortest queue
 * @author Burdyug Pavel
 */

public class ClientsGenerator implements Runnable {
    private List<Teller> tellers;
    private int avgServiceTime;
    private int avgClientSum;
    private double clientsPerSecond;
    private Random rnd = new Random();
    private int clientId = 1;

    public ClientsGenerator(List<Teller> tellers, int avgServiceTime, int avgClientSum, double clientsPerSecond) {
        this.tellers = tellers;
        this.avgServiceTime = avgServiceTime;
        this.avgClientSum = avgClientSum;
        this.clientsPerSecond = clientsPerSecond;
    }

    public ClientsGenerator(int avgServiceTime, int avgClientSum, double clientsPerSecond) {
        this(null, avgServiceTime, avgClientSum, clientsPerSecond);
    }

    private Client generateClient() {
        Client client = new Client(
                ClientOperation.values()[rnd.nextInt(2)],
                rnd.nextInt(2*avgServiceTime) + 1,
                String.format("Client-%d", clientId++),
                rnd.nextInt(2*avgClientSum) + 1
        );
        return client;
    }

    private void addClient(Client client) {
        Teller teller = findTellerWithShortestQueue();
        teller.getClients().put(client);
        System.out.printf("Added %s%n", client.getName());
    }

    private Teller findTellerWithShortestQueue() {
        Teller opt = tellers.get(0);
        for (Teller teller : tellers) {
            if (teller.getClients().size() < opt.getClients().size()) {
                opt = teller;
            }
        }
        return opt;
    }

    public void setTellers(List<Teller> tellers) {
        this.tellers = tellers;
    }

    @Override
    public void run() {
        while(true) {
            int timeline = (int)(2*1000/ clientsPerSecond);
            addClient(generateClient());
            try {
                sleep(timeline);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
