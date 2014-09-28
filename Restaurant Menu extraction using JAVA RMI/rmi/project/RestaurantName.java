package rmi.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RestaurantName extends Remote {

	//Remote method for getting the restaurant names
	ArrayList<String> getResName(int val) throws RemoteException;
	//Remote method for getting the restaurant menu
	//ArrayList<String> getResMenu(String val) throws RemoteException;
}
