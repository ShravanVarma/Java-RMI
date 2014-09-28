package rmi.project;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantMenuImpl implements RestaurantMenu {

	//Hash Map to store the Menu of each restaurant
	public Map<String, ArrayList<String>> restaurantMenu = new HashMap<String, ArrayList<String>>();
	
	public void addMenu(){
		ArrayList<String> Gareeb = new ArrayList<String>();
		//Menu of Gareeb Niwaj
		Gareeb.add("Chicken Biryani - $5.99");
		Gareeb.add("Lamb Biryani    - $6.99");
		restaurantMenu.put("1.Gareeb Nawaj", Gareeb);
		
		ArrayList<String> Taj = new ArrayList<String>();
		//Menu of Taj Mahal
		Taj.add("Veg Biryani     - $5.99");
		Taj.add("Shrimp Biryani  - $6.99");
		restaurantMenu.put("2.Taj Mahal", Taj);
		
		ArrayList<String> YThai = new ArrayList<String>();
		//Menu of Yummy Thai
		YThai.add("Pad Thai   - $8.99");
		YThai.add("Fried Rice - $7.99");
		restaurantMenu.put("1.Yummy Thai", YThai);
		
		ArrayList<String> GThai = new ArrayList<String>();
		//Menu of Golden Thai
		GThai.add("Crab Rangoon - $4.99");
		GThai.add("Roasted Duck - $6.99");
		restaurantMenu.put("2.Golden Thai", GThai);
		
		ArrayList<String> Patio = new ArrayList<String>();
		//Menu of Patio
		Patio.add("Pizza  - $4.99");
		Patio.add("Ravioli- $8.99");
		restaurantMenu.put("1.Patio", Patio);
		
		ArrayList<String> CItaly = new ArrayList<String>();
		//Menu of Come Italy
		CItaly.add("Fudge - $3.99");
		CItaly.add("Salad - $4.99");
		restaurantMenu.put("2.Come Italy", CItaly);
	}
	
	//Method used to get the menu list for the restaurants
	public ArrayList<String> getMenu(String val){
		return restaurantMenu.get(val);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Exports the Restaurant menu remote object and binds to the registry
		try{
			RestaurantMenuImpl obj = new RestaurantMenuImpl();
			obj.addMenu();
			RestaurantMenu stub = (RestaurantMenu) UnicastRemoteObject.exportObject(obj,0);
			LocateRegistry.createRegistry(1099);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("RestaurantMenu", stub);
			System.err.println("Restaurant Menu Server Ready");
		}catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		    e.printStackTrace();
		}

	}

	@Override
	public String checkRefInt(Client client1, Client client2)
			throws RemoteException {
		// TODO Auto-generated method stub
		String message;
		message = "1st Object Id :" + Integer.toHexString(System.identityHashCode(client1)) +  
					" "+
						" "+
							"2nd Object Id:" + Integer.toHexString(System.identityHashCode(client2));
		return message;
	}

	
}
