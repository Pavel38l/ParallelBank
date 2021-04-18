package ru.vsu.bank;

/**
 * @author Burdyug Pavel
 */
public class Client {
    private String name;
    private ClientOperation operation;
    private double sum;
    private int servTime;

    public Client(ClientOperation operation, int servTime, String name, double sum) {
        this.operation = operation;
        this.servTime = servTime;
        this.name = name;
        this.sum = sum;
    }

    public ClientOperation getOperation() {
        return operation;
    }

    public void setOperation(ClientOperation operation) {
        this.operation = operation;
    }

    public int getServTime() {
        return servTime;
    }

    public void setServTime(int servTime) {
        this.servTime = servTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
