/**************************************************
 * Server.java 
 * CPE 545 Project 2
 * Piotr Jarosz
 * Mikhail Efroimson
**************************************************/

// Import RMI packages
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.*;

public class Server extends java.rmi.server.UnicastRemoteObject implements QueryInterface{
	ArrayList<String> classRoster = new ArrayList<>(Arrays.asList("Touqeer Ahmad", "Madhavi Chaudhary", "Jiahui Chen" 
			, "Alessandro Della Seta", "Yuhao Dong", "Mikhail Efroimson", "Kyle Emanuele", "Aakanksha Gokhe", "Piotr Jarosz", "Madjid Mousavi", "Bhavin Patel"
			, "Dippan Patel", "Anna Petruczynik", "Andrew Polnik", "Yifan Qu", "Brian Silver", "Hansheng Tian", "Zihao Wang", "Yunhan Wen", "Guang Yang"
			, "Yang Zeng", "Qixuan Zhou", "Erwei Zhu", "Tianhao Zhu", "Piotr Efroimson"));

	String addr;		// Server IP address
	Registry registry;	// RMI registry

	/******************************************************************
	 * This is the remote method that gets called by the client. It takes
	 * one argument; a string of the first name and returns a String  
	 * representation of the array of last names that match the first
	 * name in the class roster array list. If the name is not found in
	 * the class roster, it returns a string message informing the client.
	 * Per Java RMI library remote interface requirements, it throws a
	 * remote exception. 
	 *******************************************************************/
	public String queryClassRoster(String x) throws RemoteException{
		ArrayList<String> lastNamesFound = new ArrayList<String>();
		for (int i = 0; i<classRoster.size(); i++){
			if(x.equals(classRoster.get(i).split("\\s+")[0])){
				lastNamesFound.add(classRoster.get(i).split("\\s+")[1]);
			}
		}

		if(lastNamesFound.size() < 1){
			return "this name is not in the class roster";
		} else{
			return Arrays.toString(lastNamesFound.toArray());
		}
	}

	/******************************************************************
	 * This is the server constructor. It creates the RMI registry that 
	 * listens on the specified port and binds the server name with the
	 * object. For convenience, it obtains the local IP address from the
	 * OS and prints it. Per Java RMI library remote interface requirements,
	 * it throws a remote exception.
	 *******************************************************************/	
	public Server() throws RemoteException{
		try{
			addr = (InetAddress.getLocalHost()).toString();
		} catch(Exception e){
			System.out.println("cant get inet address");
		}
		int port = 9001;
		System.out.println("this address= " + addr + ", port= " + port);

		try{
			registry = LocateRegistry.createRegistry(port);
			registry.rebind("Server", this);
		} catch(RemoteException e){
			System.out.println("remote exception " + e);
		}
	}

	/******************************************************************
	 * This is the main method. All it does is it creates the new server.
	 *******************************************************************/
	public static void main(String args[]){
		try{
			Server server = new Server();
		} catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
