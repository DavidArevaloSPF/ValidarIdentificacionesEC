package org;

public class Main {

    public static void main(String[] args) {

        try {
            if (ValidarIdentificaciones.validarCedulaNatural("0925348229")) {
                System.out.println("Es Valida");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}