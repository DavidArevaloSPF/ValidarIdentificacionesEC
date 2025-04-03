package org;

public class Main {

    public static void main(String[] args) {

        try {
            if (ValidarIdentificaciones.validarCedulaNatural("1350420204")) {
                System.out.println("Es Valida");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
