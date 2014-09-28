package rmi.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RestaurantMenu extends Remote {
	//Remote method for getting the menu of the restaurant by name
	ArrayList<String> getMenu(String val) throws RemoteException;
	//Remote method to receive client objects from Restaurant Type server/client to check referential Integrity
	public String checkRefInt(Client client1 , Client client2) throws RemoteException;
}
