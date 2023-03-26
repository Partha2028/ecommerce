
The application is designed to handle simultaneous connections and perform operations based on the incoming message type. 
It includes business logic for placing an order and tracking product availability.

Getting Started:
	To get started, we should have java 8 or above installed on the running machine. Clone this repository and run the ApplicationServer class. 
	This will get connected to the server using a TCP client and send messages according to the protocol described above.

Design Decisions:
	1. We chose a custom ALV protocol ( Action, Length, Value)  to ensure that the server receives the payload in an agreed format.
	2. Error handling to ensure that the server can handle unexpected input.
	3. The concurrency at the client side is achieved with ExecuterService with 10 concurrent clients.
	4. If the 10 client lime to be removed, we can remove the condition on the server side to accept n number of concurrent clients
	

	In the ALV protocol, the message format is defined as follows:

		Action code: Specifies the operation that needs to be performed on the data.
		Length: Specifies the length of the Payload data
		Value: Contains the data.
	
	
Future Improvements:
	1. can add implementations for more message types and integrate the server with a persistence layer for storing orders and product information. 
	2. We also plan to improve the application's security by adding authentication mechanisms.
	3. Quality: Scope for writing unit-test cases
 
 
 Execution Steps:
	Run the ApplicationServer class, this will start and keep running the E-commerce application to receive TCP Connections.
	Run the ShoppingClient class, this will create the input request and connect to ApplicationServer followed by the running server will process the incoming request.