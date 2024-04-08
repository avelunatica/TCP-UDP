import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class tcp1cli {
	public static void main(String[] args) {
		if (args.length != 2) // **Argument check up**
		{
			System.out.println("Correct syntax: tcp1cli ip_address port_numer"); // **Correct syntax in our message**
			System.exit(1);
		}
		Scanner keyboard = new Scanner(System.in);
		Boolean serverEnds = false;
		byte  valor;
		byte valor2;
		byte[] cadena=new byte[4];
		byte[] cadenaresultaux=new byte[8];
		byte[] cadenaresult2=new byte[10];
		long aux=0;
		

		//Boolean correct = false; // **Information about the server**
		String IP = args[0];
		int port = Integer.parseInt(args[1]);
		try {// **Creation the TCP connection**
			Socket socket = new Socket(IP, port); // **connection btw client socket and the server**
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.flush(); // **Socket includes socket,bind and connect functions**
			
			do { // **Introducing numbers through the keyboard**
				System.out.format("Introduce the numbers you want to sum (introduce 'QUIT' if you do not want to)\n");
				String numbers = keyboard.nextLine(); //*coger hasta salto de lï¿½nea */
				if (numbers.startsWith("QUIT")||(numbers.startsWith("quit"))) {
					socket.close();
					System.out.println("This client has been closed");
					System.exit(0);
				} else {
						if (numbers.indexOf("+") != -1) {
													
							String[] parts = numbers.split("\\+");
							valor=Byte.parseByte(parts[0]);
							valor2=Byte.parseByte(parts[1]);
							cadena[0]=1;
							cadena[1]=2;
							cadena[2]=valor;
							cadena[3]=valor2;
							outputStream.write(cadena);
							
							

						}  else if (numbers.indexOf("-") != -1) {
							String[] parts = numbers.split("\\-");
							valor=Byte.parseByte(parts[0]);
							valor2=Byte.parseByte(parts[1]);
							cadena[0]=2;
							cadena[1]=2;
							cadena[2]=valor;
							cadena[3]=valor2;
							outputStream.write(cadena);

						}
						
						else if (numbers.indexOf("*") != -1) {
							String[] parts = numbers.split("\\*");
							valor=Byte.parseByte(parts[0]);
							valor2=Byte.parseByte(parts[1]);
							cadena[0]=3;
							cadena[1]=2;
							cadena[2]=valor;
							cadena[3]=valor2;
							outputStream.write(cadena);		
						} 
						
						else if (numbers.indexOf("x")!=-1) {
							String[] parts = numbers.split("x");
							valor=Byte.parseByte(parts[0]);
							valor2=Byte.parseByte(parts[1]);
							cadena[0]=3;
							cadena[1]=2;
							cadena[2]=valor;
							cadena[3]=valor2;
							outputStream.write(cadena);	
						}
						
						else if (numbers.indexOf("X")!=-1) {
							String[] parts = numbers.split("X");
							valor=Byte.parseByte(parts[0]);
							valor2=Byte.parseByte(parts[1]);
							cadena[0]=3;
							cadena[1]=2;
							cadena[2]=valor;
							cadena[3]=valor2;
							outputStream.write(cadena);	
						}
						
						else if (numbers.indexOf("/") != -1) {
							String[] parts = numbers.split("/");
							valor=Byte.parseByte(parts[0]);
							valor2=Byte.parseByte(parts[1]);
							cadena[0]=4;
							cadena[1]=2;
							cadena[2]=valor;
							cadena[3]=valor2;
							outputStream.write(cadena);	
						
							
						} 
						
						else if (numbers.indexOf("%") != -1) {
							String[] parts = numbers.split("%");
							valor=Byte.parseByte(parts[0]);
							valor2=Byte.parseByte(parts[1]);
							cadena[0]=5;
							cadena[1]=2;
							cadena[2]=valor;
							cadena[3]=valor2;
							outputStream.write(cadena);	
						
						
						} else if (numbers.indexOf("!")!= -1){
							String parts = numbers.replace("!","");
							valor=Byte.parseByte(parts);
							cadena[0]=6;
							cadena[1]=1;
							cadena[2]=valor;
							cadena[3]=0;
							outputStream.write(cadena);	
						

						}

					/*Messages we need to write on console */

					inputStream.read(cadenaresult2);
					
					for(int i=2;i<10;i++)
					{
						cadenaresultaux[i-2]=cadenaresult2[i];
					}
					aux = ByteBuffer.wrap(cadenaresultaux).getLong();
				
					System.out.println("Accumulated value "+aux);
					
				}
			} while (!serverEnds);
			keyboard.close();
		} 
		//catch (SocketTimeoutException e) {
		// 	System.out.println("No hay respuesta del servidor pasados 10 segundos");
			
		// } 
	       catch (Exception e) {
			try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupción: " + ex.getMessage());
            }
            System.out.println("Timeout de 15 segundos");
        }
		}

	}
