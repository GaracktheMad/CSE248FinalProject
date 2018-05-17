# CSE248FinalProject
##ReStore inventory viewing and item reservation system.

###Important Info
	-Data.bin is required to run the program and store data. Removing this file will result in loss of all data. If it does not exist, the program creates a new one
	-To login for the first time, use the user name: A0000000001 and password: Password
	-New accounts initialize as a customer, and as such only have customer privelages.
	-This program offers no payment processing. It assumed the customer is reserving an item for pickup to be purchased at the reStore location
	
###Description:

####Login
	-Password is case sensative.
	-Use the create account button to do what is implied
	-User names are the unique ID code given upon account creation

####User Features
	-Access to product list
	  -Select products from the list
	  -Selected products display available quantities, price, and an image
	  -Selected products can be ordered with a button click.
	-Access to list of orders
	  -See order details
	  -Cancel an order
	  -See your invoice
	-Password changing option

####Clerk Features
	-All user features
	-Ability to add items to catalogue
	-Ability to edit items in catalogue
	-Ability to cancel orders of other users
	-Ability to toggle completion status of orders
	-Ability to generate a sales report

####Admin Features
	-All clerk features
	-Ability to create users of any rank
	-Ability to edit user emails
	-Ability to promote or demote users
	-Ability to force change other users passwords
	-Ability to browse a list of all users