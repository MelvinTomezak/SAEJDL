package interfaces.saelavrai;

import java.util.Random;

public class LeDe {
    private int de1;
    private Random random = new Random();

    public int getDe1() {
        return this.de1;
    }

    public int LancerDe () {
        this.de1 = 1+this.random.nextInt(6);

        return getDe1();
    }
}
