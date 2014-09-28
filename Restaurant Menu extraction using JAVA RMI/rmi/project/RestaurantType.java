package rmi.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/* This interface is implemented by RestaurantTypeImpl 
 * class to invoke the below mentioned remote methods*/
public interface RestaurantType extends Remote {

	//Remote method to get the list of available restaurant types
	ArrayList<String> getRestaurantType() throws RemoteException;
	//Remote method to get the restaurant names
	ArrayList<String> getRestaurantNames(int numVal) throws RemoteException;
	//Remote method for passing an object to prove referential integrity
	String refIntCheck(Client client, Client client2) throws RemoteException;
	
}
