import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConvertidorServer {
    private final int puerto = 8080;
    private final String host = "localhost";
    protected String mensaje;
    protected ServerSocket servidor;
    protected BufferedReader entrada_cliente;
    protected BufferedWriter salida_cliente;

    public void iniciarConexion(ConvertidorUnidades convertidorUnidades) throws IOException {
        servidor = new ServerSocket(puerto);
        while(!servidor.isClosed()){
            Socket cliente = servidor.accept();
            entrada_cliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida_cliente = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            System.out.println("Cliente conectado con exito");

            int bucle = 0;
            while(bucle == 0){
                mensaje = entrada_cliente.readLine();

                String [] datos = mensaje.split(";");
                String opcion = datos[0];
                String num =  datos[1];
                String longitud = datos [2];
                if (opcion.equals("0")){
                    bucle = 1;
                }else {
                    String respuesta = convertidorUnidades.convertidorUnidades(opcion, num, longitud);
                    salida_cliente.write(respuesta);
                    salida_cliente.newLine();
                    salida_cliente.flush();
                }
            }
            servidor.close();
        }
    }
    public static void iniciarServidor() throws IOException {
        ConvertidorServer server = new ConvertidorServer();
        ConvertidorUnidades convertidor = new ConvertidorUnidades();
        server.iniciarConexion(convertidor);
    }

    public static void main(String[] args) throws IOException {
        iniciarServidor();
    }
}
