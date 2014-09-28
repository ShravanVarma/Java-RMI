Restaurant Menu extraction based on the type and name of the restaurant selected

The main purpose of this application is to provide the users, the accessibility to extract and view the food menu of a particular type and name of the restaurant. The user has the option to select the type of restaurant that he/she wants the information about. Additionally the users can get all the restaurants available under a particular type of restaurant.

The following distributed system consists of three servers and a client.  The below is the sequence to depict the functionality of the distributed system.

        User                  Restaurant Type      Restaurant Name        Restaurant menu

The following provides the communication between the distributed objects.

For Restaurant Type
The user/Client gets the restaurant type from the Restaurant Type server.

For Restaurant Name
The User/Client gets the restaurant names from restaurant Name server through Restaurant Type server. The user contacts the restaurant Type Server for the Restaurant names. So the restaurant type server now becomes the client for the restaurant name server. It obtains the results through the remote method and in turn passes the information to the client.

For Restaurant Menu
The User/Client directly interacts with the Restaurant menu server for the information about the restaurant menu list.

For Validating Referential integrity
Referential Integrity: If two references to an object are passed from one JVM to another JVM in parameters (or in the return value) in a single remote method call and those references refer to the same object in the sending JVM, those references will refer to a single copy of the object in the receiving JVM. More generally stated: within a single remote method call, the RMI system maintains referential integrity among the objects passed as parameters or as a return value in the call.

To validate the referential integrity, the client object sends two references to the client object through parameters to Restaurant Type server, which in turn sends these two references as parameters to the Restaurant menu Server. In the restaurant menu server we retrieve the object ids of two references and pass them back to client to get printed as a string.
The objects Ids are found to be the same, which verifies referential integrity.

Technical Specifications:

List of remote Interfaces used:
1.	Restaurant Type Server: RestaurantType
2.	Restaurant Name Server: RestaurantName
3.	Restaurant Menu Server: RestaurantMenu

List of remote Methods used:
1.	RestaurantType: getRestaurantType(), getRestaurantNames(int), refIntCheck(Client,Client)
2.	RestaurantName: getResName(int)
3.	RestaurantMenu: getMenu(String), checkRefInt(Client,Client)

List of remote objects used:
1.	Restaurant Type Server: RestaurantTypeImpl
2.	Restaurant Name Server: RestaurantNameImpl
3.	Restaurant Menu Server: RestaurantMenuImpl


Running the program
--------------------

Unzip the zip folder cs441_RMI.zip
This folder contains rmi, readme file and design document
 

 

Compile the classes (javac Client.java RestaurantMenu.java RestaurantMenuImpl.java RestaurantName.java RestaurantNameImpl.java RestaurantType.java RestaurantTypeImpl.java)

 

Now run the RestaurantMenu server (java -classpath /Users/shravan1/Desktop/CS441_RMI  -Djava.rmi.server.codebase=file:/Users/shravan1/Desktop/CS441_RMI/ rmi.project.RestaurantMenuImpl)

The output would be 
Restaurant Menu Server Ready

Open a new instance of the terminal. Go to the folder which contains the class files. 
Now start the RestaurantName server(java -classpath /Users/shravan1/Desktop/CS441_RMI  -Djava.rmi.server.codebase=file:/Users/shravan1/Desktop/CS441_RMI/ rmi.project.RestaurantNameImpl)

The output would be
Restaurant Name server bound

Open a new instance of the terminal. Go to the folder which contains the class files. 
Now start the RestaurantType server(java -classpath /Users/shravan1/Desktop/CS441_RMI  -Djava.rmi.server.codebase=file:/Users/shravan1/Desktop/CS441_RMI/ rmi.project.RestaurantTypeImpl)

The output would be
Restaurant Type server Ready

Open a new instance of the terminal. Go to the folder which contains the class files. 
Now start the Client server(java -classpath /Users/shravan1/Desktop/CS441_RMI  -Djava.rmi.server.codebase=file:/Users/shravan1/Desktop/CS441_RMI/ rmi.project.Client)

 

The output would be
****Restaurant Applicaton*******
Available Restaurant Types
***************************
1.Indian
2.Thai
3.Italian
What is your preference ? Type 1 for Indian, 2 for Thai and 3 for Italian
--------------------------------------------------------------------------

Type the restaurant number of your preference

The output should be like this if you select 3
**********************************************************
Available restaurants under your preference
*******************************************
1.Patio
2.Come Italy
Enter the number of your preferred restaurant to get the corresponding Menu
--------------------------------------------------------------------------

Type the number of the restaurant of your preference.

The output should be like this if you type 2
********************************
Menu list of Selected Restaurant
********************************
Fudge - $3.99
Salad - $4.99
********************************************************
********** Validate refrential Integrity****************
1st Object Id :6ad9156  2nd Object Id:6ad9156
Two references to the same object have the same Object ID 
 


Summary: After the menu is fetched, a client object is created with two references to it. The references are then passed to the RestaurantType server method as two parameters. Making the client object to implement serializable makes this possible. The two references are then passed as parameters to RestaurantMenu server and the object ids of these respective references are extracted and the text is passed as a string to client object, which prints the text on its console.

As the two references have the same object id, Referential integrity is maintained.

