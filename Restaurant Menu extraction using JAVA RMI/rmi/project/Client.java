package rmi.project;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* Method that displays the available restaurant types */
	public static int listResTypes(ArrayList<String> resTypes){
		Scanner in = new Scanner(System.in);
		System.out.println("****Restaurant Applicaton*******");
		System.out.println("Available Restaurant Types");
		System.out.println("***************************");
			for(String type : resTypes){
				System.out.println(type);
			}
		System.out.println("What is your preference ? Type 1 for Indian, 2 for Thai and 3 for Italian");
		System.out.println("--------------------------------------------------------------------------");
		String pref = in.next();
		//Input validation for the preference entered by the user
		if(Integer.parseInt(pref)<1 || Integer.parseInt(pref)>3){
			System.out.println("You have entered an incorrect preference");
			System.out.println("Please try again");
			System.out.println("Application restart");
			return 0;
		}
		return Integer.parseInt(pref);
	}
	
	/* Method that displays the names of the restaurant based on the type of the restaurant user preferred */
	public static int listResNames(ArrayList<String> resNames){
		Scanner in = new Scanner(System.in);
		System.out.println("**********************************************************");
		System.out.println("Available restaurants under your preference");
		System.out.println("*******************************************");
			for(String name: resNames){
				System.out.println(name);
			}
		System.out.println("Enter the number of your preferred restaurant to get the corresponding Menu");
		System.out.println("--------------------------------------------------------------------------");
		String pref = in.next();
		//Input validation for the restaurant preferred
		if(Integer.parseInt(pref) > resNames.size() || Integer.parseInt(pref) < 1){
			System.out.println("You have entered a wrong choice");
			System.out.println("Please try again");
			System.out.println("Application Restart");
			return 0;
		}
		return Integer.parseInt(pref);
	}
	
	/*Method that displays the menu items of the selected restaurant name*/
	public static void listResMenu(ArrayList<String> resMenu){
		System.out.println("********************************");
		System.out.println("Menu list of Selected Restaurant");
		System.out.println("********************************");
		for(String menu: resMenu){
			System.out.println(menu);
		}
		
	}
	

	public static void main(String[] args) {
	
		// Get the stub of the remote object Restaurant Type
		try{
			Registry registry = LocateRegistry.getRegistry(null);
			RestaurantType stub = (RestaurantType) registry.lookup("RestaurantType");
			
			//Call the getRestaurantType() of restaurantType method to get the type of restaurants available
			ArrayList<String> resTypes = (ArrayList<String>) stub.getRestaurantType();
			//Call a local method to display the restaurant types
			int typePref = 0; 
			while (typePref == 0){
				typePref = listResTypes(resTypes);
			}
			
			//Call the getRestaurantNames() method of RestaurantType server to get the names of the restaurant
			ArrayList<String> resNames = (ArrayList<String>) stub.getRestaurantNames(typePref);
			//Call a local method to display the restaurant names based on the pref variable which has the user preference
			int namePref = 0;
			while (namePref == 0){
				namePref = listResNames(resNames);
			}
			
			//Call the getMenu() method of RestaurantMenu server to get the menu of the selected restaurant
			RestaurantMenu menuStub = (RestaurantMenu) registry.lookup("RestaurantMenu");
			ArrayList<String> resMenu = (ArrayList<String>) menuStub.getMenu(resNames.get(namePref-1));
			//Call a local method to display the selected restaurant menu
			listResMenu(resMenu);
			
			//Referential Integrity check
			Client client1 = new Client();
			Client client2 = client1;
			String message = (String)stub.refIntCheck(client1, client2);
			System.out.println("********************************************************");
			System.out.println("********** Validate refrential Integrity****************");
			System.out.println(message);
			System.out.println("Two references to the same object have the same Object ID");
			
		}catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	}

}
