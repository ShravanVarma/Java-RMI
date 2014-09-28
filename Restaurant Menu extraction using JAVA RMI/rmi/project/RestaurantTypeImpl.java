package rmi.project;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RestaurantTypeImpl implements RestaurantType {
	
	public ArrayList<String> restaurantType = new ArrayList<String>(); 
	
	public RestaurantTypeImpl() {}
	

	@Override
	public ArrayList<String> getRestaurantType() throws RemoteException {
		return restaurantType;
	}

	@Override
	public ArrayList<String> getRestaurantNames(int numVal)
			throws RemoteException {
		//Array List for Restaurant Names
		ArrayList<String> resName = new ArrayList<String>();
		try{
			Registry resNameReg = LocateRegistry.getRegistry(null);
			RestaurantName nameStub = (RestaurantName) resNameReg.lookup("RestaurantName");
			resName = (ArrayList<String>) nameStub.getResName(numVal);
		}catch(Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
		return resName;
	}
	
	//Add Restaurant type to the restaurantType list
	public void addRestaurantType(){
		restaurantType.add("1.Indian");
		restaurantType.add("2.Thai");
		restaurantType.add("3.Italian");
	}

	public static void main(String[] args) {
		try{
		RestaurantTypeImpl obj = new RestaurantTypeImpl();
		obj.addRestaurantType();
		RestaurantType stub = (RestaurantType) UnicastRemoteObject.exportObject(obj,0);
		Registry registry = LocateRegistry.getRegistry();
		registry.bind("RestaurantType", stub);
		System.err.println("Restaurant Type Server Ready");
		}catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		    e.printStackTrace();
		}

	}


	@Override
	public String refIntCheck(Client client1, Client client2)
			throws RemoteException {
	//Pass the obtained Client object to RestaurantMenu server
	// Obtain the registry for the Restaurant Menu Server
		String message = new String();
		try{
		Registry registry = LocateRegistry.getRegistry(null);
		RestaurantMenu menuStub = (RestaurantMenu) registry.lookup("RestaurantMenu");
		message = (String) menuStub.checkRefInt(client1, client2);
	}catch (Exception e) {
	    System.err.println("Referential Integrity check exception: " + e.toString());
	    e.printStackTrace();
	}
return message;
}
}