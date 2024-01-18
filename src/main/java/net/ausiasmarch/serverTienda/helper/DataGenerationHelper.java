package net.ausiasmarch.serverTienda.helper;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.time.LocalDate;

import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import net.ausiasmarch.serverTienda.exception.CannotPerformOperationException;

public class DataGenerationHelper {

    /* RANDOMS PARA EL USER */
    private static final String[] aNames = { 
            "Pepe", "Juan", "Antonio", "Manuel", "Jose", "Francisco", "David", "Jose Antonio", "Jose Luis",
            "Javier", "Jesus", "Francisco Javier", "Carlos", "Daniel", "Miguel", "Rafael", "Pedro", "Jose Manuel",
            "Angel", "Alejandro", "Miguel Angel", "Jose Maria", "Fernando", "Luis", "Sergio", "Pablo", "Jorge",
            "Alberto", "Juan Carlos", "Juan Jose", "Diego", "Ramon", "Adrian", "Alvaro", "Enrique", "Ivan", "Oscar",
            "Vicente", "Ruben", "Juan Antonio", "Andres", "Raul", "Juan Manuel", "Jose Miguel", "Ismael", "Santiago",
            "Eduardo", "Victor", "Mario", "Roberto", "Jaime", "Francisco Jose", "Ignacio", "Marcos", "Julian",
            "Tomas", "Agustin", "Juan Francisco", "Juan Luis", "Guillermo", "Sergi", "Salvador", "Joan", "Marc",
            "Jose Ramon", "Jose Carlos", "Jose Ignacio", "Mariano", "Hugo", "Aitor", "Josep", "Lorenzo", "Emilio",
            "Jose Francisco", "Jose Angel", "Jose Andres", "Jose Alberto", "Jose Alfredo", "Jose Antonio",
            "Jose Augusto", "Jose Daniel", "Jose David", "Jose Enrique", "Jose Fernando", "Jose Ignacio",
            "Jose Javier", "Jose Joaquin", "Jose Jorge", "Jose Luis", "Jose Manuel", "Jose Maria", "Jose Miguel",
            "Jose Pablo", "Jose Pedro", "Jose Ramon", "Jose Vicente", "Juan Antonio", "Juan Carlos", "Juan Diego"
    };

    private static final String[] aLastName = { "Rodríguez", "Pérez", "Gómez", "Fernández", "López", "García",
            "Martínez", "Sánchez", "Díaz", "Torres", "Ramírez", "Vázquez", "Castro", "Ruiz", "Herrera", "Jiménez",
            "Moreno", "Navarro", "Medina", "Morales", "Ortega", "Silva", "Romero", "Flores", "Álvarez", "Cordero",
            "Peralta", "Quintero", "Mendoza", "Espinoza"
    };

    public static String getRandomDni() {
        String dni = "";
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = (int) (Math.random() * 100000000);
        int modulo = numero % 23;
        dni = numero + letras.substring(modulo, modulo + 1);
        return dni;
    }

    public static String getRandomName() {
        return aNames[(int) (Math.random() * aNames.length)];
    }

    public static String getRandomLastName() {
        return aLastName[(int) (Math.random() * aLastName.length)];
    }

    public static String doNormalize(String cadena) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String cadenaSinAcentos = cadena;
        for (int i = 0; i < original.length(); i++) {
            cadenaSinAcentos = cadenaSinAcentos.replace(original.charAt(i), ascii.charAt(i));
        }
        return cadenaSinAcentos;
    }

    // ¿¿MANEJO AÑOS BISIESTOS??
    public static LocalDate getRandomBirthDate() {
        // Calcular fecha actual
        LocalDate currentDate = LocalDate.now();

        // Generar un año de nacimiento entre hace 18 y 65 años de la fecha actual
        int minYear = currentDate.getYear() - 65;
        int maxYear = currentDate.getYear() - 18;
        int year = (int) (Math.random() * (maxYear - minYear + 1) + minYear);

        // Generar un mes y un día aleatorio
        int month = (int) (Math.random() * 12) + 1;
        int day = (int) (Math.random() * 28) + 1;

        return LocalDate.of(year, month, day);
    }

    public static String getRandomNumberPhone() {
        String numberPhone = String.valueOf((int) (Math.random() * 2) + 6);

        for (int i = 1; i < 9; i++) {
            numberPhone += (int) (Math.random() * 10);
        }
        return numberPhone;
    }

    public static String getRandomAddress() {
        String[] aAddress = { "Calle", "Avenida", "Plaza", "Paseo", "Camino", "Callejón", "Travesía", "Pasaje", "Ronda" };
        String[] aStreet = { "Mayor", "Real", "San Juan", "San Pedro", "San José", "San Miguel", "San Pablo"};
        String[] aNumber = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" };
        String[] aLetter = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
        String address = aAddress[(int) (Math.random() * aAddress.length)] + " " + aStreet[(int) (Math.random() * aStreet.length)] + " " + aNumber[(int) (Math.random() * aNumber.length)] + aLetter[(int) (Math.random() * aLetter.length)];
        return address;
    }

    public static String getRandomCity() {
        String[] aCity = {"Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", 
        "Málaga", "Murcia", "Palma", "Las Palmas de Gran Canaria", 
        "Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", 
        "Gijón", "L'Hospitalet de Llobregat", "A Coruña", "Vitoria", 
        "Granada", "Elche", "Oviedo", "Badalona", "Cartagena", 
        "Terrassa", "Jerez de la Frontera", "Sabadell", "Alcalá de Henares", 
        "Pamplona", "Fuenlabrada"};
        String city = aCity[(int) (Math.random() * aCity.length)];
        return city;
    }

    public static int getRandomPostalCode() {
        int postalCode = (int) (Math.random() * 90000) + 10000;
        return postalCode;
    }    

    /* RANDOM PARA EL CAPTCHA */
    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String getSHA256(String strToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(strToHash.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            throw new CannotPerformOperationException("no such algorithm: sha256");
        }
    }
}
