import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class udpser {

	public static void main(String[] args) {


		if (args.length != 2) // **Comprobacion de los argumentos**
		{
			System.out.println("Sintaxis correcta: udpser port_numer secretnum");// **mensaje con sintaxis correcta**
			System.exit(1);
		}

		int numero1 = 0;
		int numero2 = 0;
		int operacion = 0;
		int secreto=Integer.parseInt(args[1]);
		System.out.println("\n SERVIDOR UDP");
		try {
			DatagramSocket servidor = new DatagramSocket(Integer.parseInt(args[0]));
			byte[] array = new byte[6000];
			System.out.println("> Serv: Se crea el socket del servidor...\n\n");
			DatagramPacket informacionEntrada = new DatagramPacket(array, array.length);
 
			do {
				servidor.receive(informacionEntrada);
			    
				String datos = new String(informacionEntrada.getData(), 0, informacionEntrada.getLength());
				System.out.println("> Serv: Se recibe la operacion del cliente: "+datos);
				if (datos.indexOf("+") != -1) {
					Scanner cadenaNumeros = new Scanner(datos).useDelimiter("\\+");
					numero1 = cadenaNumeros.nextInt();
					numero2 = cadenaNumeros.nextInt();
					operacion = numero1+numero2;
					cadenaNumeros.close();
				} else if (datos.indexOf("-") != -1) {
					Scanner cadenaNumeros = new Scanner(datos).useDelimiter("-");
					numero1 = cadenaNumeros.nextInt();
					numero2 = cadenaNumeros.nextInt();
					operacion = numero1-numero2;
					cadenaNumeros.close();

				} else if (datos.indexOf("*") != -1) {

					Scanner cadenaNumeros = new Scanner(datos).useDelimiter("\\*");
					numero1 = cadenaNumeros.nextInt();
					numero2 = cadenaNumeros.nextInt();
					operacion = numero1*numero2;
					cadenaNumeros.close();
				} else if (datos.indexOf("/") != -1) {
					Scanner cadenaNumeros = new Scanner(datos).useDelimiter("/");
					numero1 = cadenaNumeros.nextInt();
					numero2 = cadenaNumeros.nextInt();
					operacion = numero1/numero2;
					cadenaNumeros.close();
				}
				operacion=operacion+secreto;
				System.out.println("> Serv: Operacion completada: "+operacion);
				String resultado = operacion+"";
				DatagramPacket informacionSalida = new DatagramPacket(resultado.getBytes(), resultado.getBytes().length,
						informacionEntrada.getAddress(), informacionEntrada.getPort());
				System.out.println("> Serv: Se envia la informacion al cliente. \n\n\n");
				servidor.send(informacionSalida); // envio al cliente el valor final del acumulador
			} while (true); // servidor permanece en espera de nuevos mensajes

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}