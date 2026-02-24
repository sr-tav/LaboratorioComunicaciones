import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ConvertidorCliente {
    private final int puerto = 8080;
    private final String host = "localhost";
    protected String mensaje;
    protected Socket conexion_servidor;
    protected BufferedReader entrada;
    protected BufferedWriter salida;

    public void iniciarConexion(ConvertidorUnidades convertidorUnidades) throws IOException {
        System.out.println("Conectando con el servidor");
        conexion_servidor = new Socket(host,puerto);
        entrada = new BufferedReader(new InputStreamReader(conexion_servidor.getInputStream()));
        salida = new BufferedWriter(new OutputStreamWriter(conexion_servidor.getOutputStream()));
        System.out.println("Conexion con el servidor establecida");

        while(!conexion_servidor.isClosed()){
            mensaje = convertidorUnidades.menu();
            salida.write(mensaje);
            salida.newLine();
            salida.flush();
            System.out.println("Mensaje enviado al servidor" + mensaje);

            String respuesta = entrada.readLine();
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }
    public static void iniciarCliente() throws IOException {
        ConvertidorCliente cliente = new ConvertidorCliente();
        ConvertidorUnidades convertidorUnidades = new ConvertidorUnidades();
        cliente.iniciarConexion(convertidorUnidades);
    }
    public static void main(String[] args) throws IOException {
        iniciarCliente();
    }
}
