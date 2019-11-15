import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TesteHttp {

    public static void main(String[] args) {
        System.out.println("iniciando.");
        String hostname = "google.com.br";
        int port = 80;
 
        try (Socket socket = new Socket(hostname, port)) {
 
	    // AS 3 LINHAS ABAIXO CRIAM UM PONTO NA MEMÓRIA, INSTANCIAM UM HELPER DE ESCRITA EM MEMÓRIA
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            System.out.println("antes de enviar");
            writer.println("GET / HTTP/1.1"); // NESTE PONTO O CLIENTE ESTÁ ESCREVENDO PARA PLACA DE REDE
            writer.println("Host: www.google.com.br"); // NESTE PONTO O CLIENTE ESTÁ ESCREVENDO PARA PLACA DE REDE
            writer.println("User-Agent:curl"); // NESTE PONTO O CLIENTE ESTÁ ESCREVENDO PARA PLACA DE REDE
            writer.println(); // NESTE PONTO O CLIENTE ESTÁ ESCREVENDO PARA PLACA DE REDE
            
            // AGORA É SÓ PEGAR O RETORNO.
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            // AQUI ELE VAI LER ATÉ NÃO EXISTIR LINHA.
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }


}