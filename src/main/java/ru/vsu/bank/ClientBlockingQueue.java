package ru.vsu.bank;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Blocked client queue
 * If the queue is empty when receiving a client, the thread will enter the wait state
 * If a client is added to the queue, one arbitrary thread will be awakened
 * @author Burdyug Pavel
 */

@Getter
public class ClientBlockingQueue {
    private Queue<Client> clients = new ArrayDeque<>();

    /**
     * Put client to blocking queue
     */
    public synchronized void put(Client client) {
        clients.offer(client);
        notify();
    }

    /**
     * Return first client in the queue or makes threads wait if queue id empty
     * @return first client in the queue or makes threads wait if queue id empty
     */
    public synchronized Client next() {
        while (clients.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return clients.poll();
    }

    /**
     * Return queue clients count
     * @return queue clients count
     */
    public int size() {
        return clients.size();
    }
}
