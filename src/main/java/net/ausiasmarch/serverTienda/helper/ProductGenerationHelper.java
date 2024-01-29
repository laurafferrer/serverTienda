package net.ausiasmarch.serverTienda.helper;

import java.util.Random;

public class ProductGenerationHelper {

    /*
     * Random names for products
     */
    private static final String[] aNames = {
        "Boligrafos de tinta gel", "Bolígrafos de tinta de aceite", "Bolígrafos de tinta líquida", 
        "Plumillas y portaplumillas para caligrafía", "Lápices de grafito", "Lápices de colores", 
        "Portaminas", "Gomas de borrar", "Cuadernos de notas", "Blocs de dibujo", "Cuaderno de espiral",
        "Cuadernos de cuadrícula", "Cuadernos de rayas", "Cuadernos blancos", "Hojas sueltas", "Blocs de papel",
        "Papel de colores", "Papel pergamino", "Cintas adhesivas", "Pegamento en barra", "Pegamento líquido",
        "Pegatinas decorativas", "Carpetas", "Archivadores", "Separadores", "Clips y pinzas", "Marcadores",
        "Resaltadores", "Rotuladores permanentes", "Marcadores de pizarra blanca", "Tijeras", "Cúters", "Reglas", 
        "Escuadras y transportadores", "Grapas y grapadoras", "Clips metálicos y de papel", "Gomas elásticas",
        "Sellos y tintas", "Organizadores de escritorio", "Portaplumas", "Bandejas de papel", "Soportes para tarjetas",
        "Claculadoras báscias y científicas", "Agendas y planificadores", "Notas adhesivas y recordatorios",
        "Pinceles", "Pinturas", "Lápices de acuarela", "Blocs de papel para acuarelas", "USB y memorias externas",
        "Cables de carga", "Baterías", "Auriculares"
    };

    /*
     * Generate random name.
     */
    public static String getRandomName() {
        Random random = new Random();
        int randomName = random.nextInt(aNames.length);
        return aNames[randomName];
    }

    /*
     * Random description for products
     */
    private static final String[] aDescription = {
        "Bolígrafos de tinta gel para escritura suave.",
        "Lápices de colores para expresar tu creatividad.",
        "Cuadernos de notas ideales para apuntes diarios.",
        "Papel pergamino para proyectos artísticos elegantes.",
        "Pegamento en barra para unir papel de manera fácil.",
        "Carpetas organizadoras para documentos importantes.",
        "Rotuladores permanentes para etiquetar de forma duradera.",
        "Tijeras de precisión para cortes limpios y seguros.",
        "Clips metálicos para mantener tus documentos ordenados.",
        "Portaplumas elegante para dar estilo a tu escritorio.",
        "Calculadora científica para resolver problemas con precisión.",
        "Auriculares para disfrutar de tu música favorita.",
        "Bloc de papel para acuarelas, ideal para artistas.",
        "Separadores de colores para organizar tu información.",
        "Gomas elásticas para agrupar y sujetar tus documentos.",
        "Soportes para tarjetas que mantienen todo a la vista.",
        "Pinceles de calidad para dar vida a tus obras.",
        "Memorias externas para almacenar tus datos importantes.",
        "Notas adhesivas perfectas para recordatorios rápidos."
    };

    /*
     * Generate random description.
     * 
     * @return A random description.
     */
    public static String getRandomDescription() {
        Random random = new Random();
        int randomDescription = random.nextInt(aDescription.length);
        return aDescription[randomDescription];
    }

    /*
     * Get random price for products, rounded to two decimals.
     * 
     * @return A random price.
     */
    public static double getRandomPrice() {
        return Math.round(Math.random() * 10000) / 100.0;
    }

    /*
     * Get random stock for products
     * 
     * @return A random stock.
     */
    public static int getRandomStock() {
        return (int) (Math.random() * 10000);
    }
    
}
