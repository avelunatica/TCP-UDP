import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class tcp1ser {
	public static void main(String[] args) {

		if (args.length != 1) // **Argument check up**
		{
			System.out.println("Sintaxis correcta: udpser port_numer"); // **Correct syntax in our message**
			System.exit(1);
		}
		long acc = 0;
		byte[] buffer = new byte[4];
		byte[] cadena = new byte[10];

		ServerSocket serverSocket = null;
		try {
			int port = Integer.parseInt(args[0]);
			serverSocket = new ServerSocket(port);
			do {
				acc = 0; // **ACC RESET**
				try {
					Socket socketAux = serverSocket.accept(); /*CONEXIÓN */
					do {
						DataInputStream inputStream = new DataInputStream(socketAux.getInputStream());
						DataOutputStream outputStream = new DataOutputStream(socketAux.getOutputStream());
						inputStream.readFully(buffer); // **We read & resolve the sum of the numb. the client has
														// written on keyboard

						if (buffer[0] == 1) {

							cadena[0] = 16;
							cadena[1] = 8;
							long operacion = buffer[2] + buffer[3];
							
							operacion+=acc;
							acc=operacion;
							
							System.out.println(buffer[2]+"+"+buffer[3]+"="+operacion);

							cadena[2] = (byte) (operacion >>> 56);
							cadena[3] = (byte) (operacion >>> 48);
							cadena[4] = (byte) (operacion >>> 40);
							cadena[5] = (byte) (operacion >>> 32);
							cadena[6] = (byte) (operacion >>> 24);
							cadena[7] = (byte) (operacion >>> 16);
							cadena[8] = (byte) (operacion >>> 8);
							cadena[9] = (byte) operacion;

							outputStream.write(cadena);
						}
						if (buffer[0] == 2) {

							cadena[0] = 16;
							cadena[1] = 8;
							long operacion = buffer[2] - buffer[3];
							
							operacion=operacion + acc;
							acc=operacion;

							System.out.println(buffer[2]+"-"+buffer[3]+"="+operacion);
							
							cadena[2] = (byte) (operacion >>> 56);
							cadena[3] = (byte) (operacion >>> 48);
							cadena[4] = (byte) (operacion >>> 40);
							cadena[5] = (byte) (operacion >>> 32);
							cadena[6] = (byte) (operacion >>> 24);
							cadena[7] = (byte) (operacion >>> 16);
							cadena[8] = (byte) (operacion >>> 8);
							cadena[9] = (byte) operacion;
						

							outputStream.write(cadena);
						}

						if (buffer[0] == 3) {

							cadena[0] = 16;
							cadena[1] = 8;
							long operacion = buffer[2] * buffer[3];
							
							operacion+=acc;
							acc=operacion;
							
							System.out.println(buffer[2]+"*"+buffer[3]+"="+operacion);

							cadena[2] = (byte) (operacion >>> 56);
							cadena[3] = (byte) (operacion >>> 48);
							cadena[4] = (byte) (operacion >>> 40);
							cadena[5] = (byte) (operacion >>> 32);
							cadena[6] = (byte) (operacion >>> 24);
							cadena[7] = (byte) (operacion >>> 16);
							cadena[8] = (byte) (operacion >>> 8);
							cadena[9] = (byte) operacion;

							outputStream.write(cadena);
						}

						if (buffer[0] == 4) {

							cadena[0] = 16;
							cadena[1] = 8;
							long operacion = buffer[2] / buffer[3];
							System.out.println(buffer[2]+"/"+buffer[3]+"="+operacion);
							operacion+=acc;
							acc=operacion;

							cadena[2] = (byte) (operacion >>> 56);
							cadena[3] = (byte) (operacion >>> 48);
							cadena[4] = (byte) (operacion >>> 40);
							cadena[5] = (byte) (operacion >>> 32);
							cadena[6] = (byte) (operacion >>> 24);
							cadena[7] = (byte) (operacion >>> 16);
							cadena[8] = (byte) (operacion >>> 8);
							cadena[9] = (byte) operacion;

							outputStream.write(cadena);
						}

						if (buffer[0] == 5) {

							cadena[0] = 16;
							cadena[1] = 8;
							long operacion = buffer[2] % buffer[3];
							
							operacion+=acc;
							acc=operacion;
							System.out.println(buffer[2]+"%"+buffer[3]+"="+operacion);

							cadena[2] = (byte) (operacion >>> 56);
							cadena[3] = (byte) (operacion >>> 48);
							cadena[4] = (byte) (operacion >>> 40);
							cadena[5] = (byte) (operacion >>> 32);
							cadena[6] = (byte) (operacion >>> 24);
							cadena[7] = (byte) (operacion >>> 16);
							cadena[8] = (byte) (operacion >>> 8);
							cadena[9] = (byte) operacion;

							outputStream.write(cadena);
						}

						if (buffer[0] == 6) {

							cadena[0] = 16;
							cadena[1] = 8;
							int numero = buffer[2];
							long factorial = 1;
							/*5!= 5*4*3*2*1=120. El 1 se puede ignorar, cálculo inútil */
							for (int i = 2; i <= numero; i++) {
								factorial =factorial*i;
							}

							factorial+=acc;
							acc=factorial;
							System.out.println(buffer[2]+"!"+"="+factorial);

							cadena[2] = (byte) (factorial >>> 56);
							cadena[3] = (byte) (factorial >>> 48);
							cadena[4] = (byte) (factorial >>> 40);
							cadena[5] = (byte) (factorial >>> 32);
							cadena[6] = (byte) (factorial >>> 24);
							cadena[7] = (byte) (factorial >>> 16);
							cadena[8] = (byte) (factorial >>> 8);
							cadena[9] = (byte) factorial;

							outputStream.write(cadena);
						}
					} while (socketAux.isBound());
				} catch (IOException s) {
					System.out.println("There was an interruption on the connection");
				}
			} while (true); // **Server will be forever waiting for new messages**
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}




