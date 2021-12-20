package com.company;

public class semaf {

    private int signal = 1;

    public synchronized void semafAcquire() throws InterruptedException
    {
        while(this.signal == 0)
            wait();
        this.signal--;
    }

    public synchronized void semafRelease()
    {
        this.signal++;
        notify();
    }

}
