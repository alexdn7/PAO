The project simulates an online store, in Java, applying OOP concepts.

It contains the classes:

Product, which contains general data about a product:
  - name (String)
  - description (String)
  - price (double)
  - stock (int)
  - reviews (list) - initially without elements.

ElectronicProduct - inherits Product. Brings extra:
  - warranty (int, expressed in number of months)
  - power (String)

FoodProduct - inherits Product. Brings extra:
  - prodDate(String)
  - expDate(String)

ClothingProduct - inherits Product. Brings extra:
  - size (String)

Customer, which contains data about users, such as:
  - name(String)
  - address(String)
  - phone number (String)
  - email (String)
  - a ShoppingCart type object

ShoppingCart, which contains information about each customer's shopping cart. These contain a map<String, Integer> in which data of the form [Product name = quantity] is retained.

StoreService - a Singleton through which all the methods in the classes are called. It contains a list of products and one of costumers.

Main - contains an interactive menu for the user. It contains several switches, within which the called methods are chosen depending on the user inputs.

At first, the user is asked if he has an account on the site. Otherwise, he has a choice if he wants to make one or not. If he doesn't want to, the program stops.

If he wants, he enters his data and his account is created, after which he has access to the rest of the orders.

These are:

  1. List all products
  2. Add a product
  3. Add review to a product
  4. Change the stock of a product
  5. List all customers
  6. Change the price of a product
  7. Add an item to my shopping cart
  8. View items from my cart
  9. Change my account email
  10. Change my phone number
  11. Change my address

The console menu showing as follows:
![meniu](https://user-images.githubusercontent.com/51855097/224302195-467a7a75-73ae-4514-873d-e02058db7739.png)

Data structures used: List and Map.
