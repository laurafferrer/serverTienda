package net.ausiasmarch.serverTienda.helper;

import java.util.Random;

public class PurchaseDetailGenerationHelper {

    public static int getRandomAmount() {
        return new Random().nextInt(30) + 1;
    }

    public static int getRandomPrice() {
        return new Random().nextInt(30) + 1;
    }
    
}
