	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.InetAddress;
	import java.net.SocketTimeoutException;
	import java.util.Scanner;

	public class udpcli {
		//udpcli ip_address port_numer operacion
		
		public static void main(String[] args) {
			
			System.out.println("\n CLIENTE UDP");
			if (args.length != 3) //**Comprobacion de los argumentos**
			{
				System.out.println("Sintaxis correcta: udpcli ip_address port_numer operacion");//**mensaje con sintaxis correcta**
				System.out.println("La operacion se escribir de la forma: 'num1_op_num2' (cadena sin espacios)");
				System.out.println("Ejemplo: 3+2");
				System.exit(1);
			}
			
			try {
				byte[] array=new byte[10000];
				DatagramSocket socketCliente = new DatagramSocket();
				socketCliente.setSoTimeout(10000);//** 10sx1000(Timeout en ms)**
				array=args[2].getBytes();
		      	DatagramPacket datagramaEnvio = new DatagramPacket(array, array.length, InetAddress.getByName(args[0]),Integer.parseInt(args[1]));
				System.out.println("> Cli: Se crea el socket del cliente...\n\n");
				socketCliente.send(datagramaEnvio);
				System.out.println("> Cli: Se envia la operacion al servidor: "+args[2]);
				byte[] buffer = new byte[10000];
				DatagramPacket datagramaRecibido = new DatagramPacket(buffer, buffer.length);
				socketCliente.receive(datagramaRecibido); // Se bloquea hasta que recibe la respuesta
				System.out.println("> Cli: Esperando respuesta del servidor...");
				String acc = new String(datagramaRecibido.getData(), 0, datagramaRecibido.getLength());
				System.out.println("\n\n> Cli: El resultado seria:" + acc+"\n\n");
				socketCliente.close();
				
			} catch (SocketTimeoutException e) {
				System.out.println("> Cli: No hay respuesta del servidor pasados 10 segundos");
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}