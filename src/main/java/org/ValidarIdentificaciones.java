package org;

import java.util.Arrays;
import java.util.List;

public class ValidarIdentificaciones {

    public static boolean validarCedulaNatural(String identificacion) throws Exception {

        System.out.println("Validando -> " + identificacion);

        validacionInicial(identificacion, 10);

        Integer noDigits = Integer.valueOf(identificacion.substring(0, 2));
        if (noDigits <= 0 || noDigits > 24) {
            throw new Exception("La provincia de emisión es incorrecta.");
        }

        validarTercerDigito(Integer.valueOf(identificacion.substring(2, 3)), "cedula_nacional");

        return algModulo10(identificacion.substring(0, 9), Integer.valueOf(identificacion.substring(9, 10)));

    }

    static void validacionInicial(String noIdentificacion, Integer noDigitos) throws Exception {
        if (noIdentificacion == null) {
            throw new Exception("El número de identificación no puede ir nulo.");
        }
        if (noIdentificacion.isEmpty()) {
            throw new Exception("El número de identificación no puede vacío.");
        }
        if (noIdentificacion.length() != noDigitos) {
            throw new Exception("La cédula debe tener " + noDigitos + " caracteres.");
        }
        if (!noIdentificacion.chars().allMatch(Character::isDigit)) {
            throw new Exception("El número de identificación deben ser solo números.");
        }
    }

    static void validarTercerDigito(Integer numero, String tipoIdentificacion) throws Exception {
        switch (tipoIdentificacion) {
            case "cedula_nacional":
            case "ruc_natural_nacional":
                if (numero < 0 || numero > 5) {
                    throw new Exception("El tercer dígito debe ser mayor o igual a 0 y menor a 6 para cédulas y RUC de persona natural.");
                }
                break;
            case "cedula_extranjero":
            case "ruc_natural_extranjero":
                if (numero >= 6) {
                    throw new Exception("El tercer dígito debe ser mayor o igual a 6 para cédulas y RUC de persona naturales nacionalizadas.");
                }
                break;
            case "ruc_privada":
                if (numero != 9) {
                    throw new Exception("Tercer dígito debe ser igual a 9 para sociedades privadas");
                }
                break;

            case "ruc_publica":
                if (numero != 6) {
                    throw new Exception("Tercer dígito debe ser igual a 6 para sociedades públicas");
                }
                break;
            default:
                throw new Exception("Tipo de Identificacion no existe.");
        }
    }

    static boolean algModulo10(String identificacion, Integer digitoVerificador) throws Exception {

        List<Integer> coeficientes = Arrays.asList(2, 1, 2, 1, 2, 1, 2, 1, 2);
        Integer valorTemporal = 0, valorAcumulado = 0;

        for (int i = 0; i < coeficientes.size(); i++) {
            valorTemporal = coeficientes.get(i) * Integer.valueOf(identificacion.substring(i, i + 1));
            valorAcumulado += valorTemporal >= 10 ? valorRecalculado(valorTemporal) : valorTemporal;
        }

        if ((10 - (valorAcumulado % 10)) != digitoVerificador) {
            throw new Exception("La identificación ingresada no es válida");
        }

        return Boolean.TRUE;
    }

    static Integer valorRecalculado(Integer valorTemporal) {
        String valorStr = valorTemporal.toString();
        return Integer.valueOf(valorStr.substring(0, 1)) + Integer.valueOf(valorStr.substring(1, 2));
    }

}
