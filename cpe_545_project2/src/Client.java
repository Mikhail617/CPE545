// Client.java 
// CPE 545 Project 2
// Piotr Jarosz
// Mikhail Efroimson
//Import RMI packages
import java.rmi.*; /*Remote Method Invocation. It is a mechanism that enables an object on one 
Java virtual machine to invoke methods on an object in another Java virtual machine*/
import java.rmi.registry.*; //Provides a class and two interfaces for the RMI registry
import java.net.*; //Provides the classes for implementing networking applications

public class Client {
	public static void main(String args[]){
		QueryInterface Server;
		Registry registry; /* remote interface to a simple remote object registry that 
		provides methods for storing and retrieving remote object references bound with arbitrary string names */
		String serverAddr = args[0]; //String for IP address
		String serverPort = args[1]; //String for Port
		String text = args[2];		//String for First Name being searched

		System.out.println("sending " + text + " to " + serverAddr + ":" + serverPort); 
		//Prints Servers IPV4 Address and Port of the name being searched

		try{
			registry = LocateRegistry.getRegistry(serverAddr,(new Integer(serverPort)).intValue());
			Server = (QueryInterface) (registry.lookup("Server"));
			//call the remote method
			System.out.println( Server.queryClassRoster(text) );
			//Prints Found Server Names
		} catch(RemoteException e){
			e.printStackTrace();
		} catch(NotBoundException e){
			System.err.println(e);
		}
	}
}
