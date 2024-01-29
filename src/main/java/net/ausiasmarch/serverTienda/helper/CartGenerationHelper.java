package net.ausiasmarch.serverTienda.helper;

import java.util.Random;

public class CartGenerationHelper {

    public static int getRandomAmount() {
        return new Random().nextInt(30) + 1;
    }
    
}
