import javax.swing.*;

public class ConvertidorUnidades {
    private String respuesta;
    String opcion = "999";
    private String mensaje = "|||||||||||||||||||| CONVERSOR DE UNIDADES |||||||||||||||||\n" +
            "|||||||| --> 1. Decimal a Binario                    |||||||||||||||||\n" +
            "|||||||| --> 2. Binario a Decimal                    |||||||||||||||||\n" +
            "|||||||| --> 3. Decimal a Hexadecimal         |||||||||||||||||\n" +
            "|||||||| --> 4. Hexadecimal a Decimal         |||||||||||||||||\n" +
            "|||||||| --> 5. Binario a Hexadecimal           |||||||||||||||||\n" +
            "|||||||| --> 6. Hexadecimal a Binario           |||||||||||||||||\n" +
            "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
            "|||||||| --> 0. Salir                                |||||||||||||||||||||||||||||\n" +
            "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";

    /**
     *
     * /////////////////////////////// OPCIONES MENU CLIENTE //////////////////////////////////////////////
     *
     */
    public String menu(){
        opcion = JOptionPane.showInputDialog(mensaje);
        switch (opcion) {
            case "1":
                respuesta = menuIngresoDatos("decimal", opcion);
                break;
            case "2":
                respuesta = menuIngresoDatos("binario", opcion);
                break;
            case "3":
                respuesta = menuIngresoDatos("decimal", opcion);
                break;
            case "4":
                respuesta = menuIngresoDatos("hexadecimal", opcion);
                break;
            case "5":
                respuesta = menuIngresoDatos("binario", opcion);
                break;
            case "6":
                respuesta = menuIngresoDatos("hexadecimal", opcion);
                break;
            case "0":
                respuesta = "Salida";
                break;
            default:
                System.out.println("Seleccione una opcion correcta");
        }
        return respuesta;
    }
    public String menuIngresoDatos(String unidad, String opcion){
        String decimal = JOptionPane.showInputDialog("Ingrese el numero " + unidad);
        String logitud = JOptionPane.showInputDialog("Ingrese la longitud de bits que requiere");
        return  opcion + ";" + decimal + ";" + logitud;
    }
    /**
     *
     * /////////////////////////////// OPCIONES MENU SERVIDOR //////////////////////////////////////////////
     *
     */
    public String convertidorUnidades(String opcion, String numero, String longitud){
        String operacion = "";
        String decimal = "";
        switch (opcion) {
            case "1":
                operacion = Integer.toBinaryString(Integer.parseInt(numero));
                break;
            case "2":
                operacion = Integer.toString(Integer.parseInt(numero,2));
                break;
            case "3":
                operacion = Integer.toHexString(Integer.parseInt(numero));
                break;
            case "4":
                operacion = Integer.toString(Integer.parseInt(numero,16));
                break;
            case "5":
                decimal = Integer.toString(Integer.parseInt(numero,2));
                operacion = Integer.toHexString(Integer.parseInt(decimal));
                break;
            case "6":
                decimal = Integer.toString(Integer.parseInt(numero,16));
                operacion = Integer.toBinaryString(Integer.parseInt(decimal));
                break;
            default:
                System.out.println("Seleccione una opcion correcta");
        }
        return agregarLongitud(operacion, longitud);
    }

    public String agregarLongitud(String operacion, String longitud){
        int longitudNum = Integer.parseInt(longitud);
        longitudNum = longitudNum - operacion.length();
        if (longitudNum > 0) {
            while(longitudNum>0){
                operacion = "0"+ operacion;
                longitudNum--;
            }
        }
        return operacion;
    }

}
