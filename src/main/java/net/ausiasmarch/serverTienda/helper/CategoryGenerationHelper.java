package net.ausiasmarch.serverTienda.helper;

import java.util.Random;

public class CategoryGenerationHelper {

    /*
     * Random names for categories
     */
    private static final String[] aNames = {
        "Boligrafos", "Lápices", "Cuadernos", "Blocs", "Paple", "Adhesivos", "Pegamento",
        "Organización", "Artículos de escritura", "Herramientas de corte", "Material de oficina",
        "Accesorios de escritorio", "Calculadoras", "Agendas", "Material arte", "Tecnología"
    };

    /*
     * Get random name for categories
     * 
     * @return random name
     */
    public static String getRandomName() {
        Random random = new Random();
        int randomName = random.nextInt(aNames.length);
        return aNames[randomName];
    }
    
}
