package rmi.project;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantNameImpl implements RestaurantName {
	
	//Hash Map to store restaurant type and name
	public Map<Integer, ArrayList<String>> resName = new HashMap<Integer, ArrayList<String>>();

	@Override
	public ArrayList<String> getResName(int val) throws RemoteException {
		// TODO Auto-generated method stub
		return resName.get(val);
	}


	//Add restaurant names to the restaurant types
	public void addResName(){
		//Restaurant Names for Indian Type
		ArrayList<String> ResIndian = new ArrayList<String>();
		ResIndian.add("1.Gareeb Nawaj");
		ResIndian.add("2.Taj Mahal");
		resName.put(1, ResIndian);
		
		//Restaurant Names for Thai Type
		ArrayList<String> ResThai = new ArrayList<String>();
		ResThai.add("1.Yummy Thai");
		ResThai.add("2.Golden Thai");
		resName.put(2, ResThai);
		
		//Restaurant Names for Italian type
		ArrayList<String> ResItalian = new ArrayList<String>();
		ResItalian.add("1.Patio");
		ResItalian.add("2.Come Italy");
		resName.put(3, ResItalian);
		
	}

	public static void main(String[] args) {
		
		try{
			//Export Restaurant Name server and bind to the registry
			RestaurantNameImpl obj = new RestaurantNameImpl();
			obj.addResName();
			RestaurantName stub = (RestaurantName) UnicastRemoteObject.exportObject(obj,0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("RestaurantName", stub);
			System.err.println("Restaurant Name server bound");
		}catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		    e.printStackTrace();
		}
	}

	
}
