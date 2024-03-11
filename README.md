## Introduction
This server-side project aims to build a data API for a supermarket stock management database. 
This API is for a fictional warehouse which contains stock batches of particular items. Products in stock can be sorted into a list of ordered items, that is then added to an order placed by a supermarket.

## Trello Board
https://trello.com/b/g0Koxjmj/backend-server-project

## MVP and Extension Description

### MVP Key Functionality
1. Add products to the stock database.

2. Remove products to the stock database.

3. Update products in the stock database to show the change in stock.

4. Display all items in the stock database.

5. Filter Items in the stock database based on their properties. E.g. (showing items that only exist in the dairy aisle)

### Extension Key Functionality 

1. Enable orders containing ordered items to be created from the stock.

2. Add a transactional method to remove stock from the warehouse stock to the supermarket stock.

3. Add a method to calculate the order cost.

## Step-by-step Setup Instructions

1. Fork repository.

2. Create a database named inventory_db.

## Tech Stack 

1. A Postgres Database

2. Java

3. Spring Boot

## User Notes

1. Before deleting an orderedItem, to avoid deleting the associted orderedQuantity, please patch the orderedQuantity to be 0 first. This way the quantity of stock in the orderedItem will return to the quantity attribute in the stock.

2. We would recommend not adding multiple orderedItems of the same stock to the same order. It is better to use a patch request to update the orderedItems quantity. We would have ideally error handled this to prevent duplication of orderedItems of the same stock, but unfortunately ran out of time.

3. We had some trouble formatting the JSON segments in the tables below. Postman request bodies and response bodies can also be found in the exported postman file here: https://github.com/H8S8/BackendProject/blob/main/BackendProject.postman_collection.json

## ERD and UML Diagram 

### ERD Diagram 

![alt text](https://github.com/H8S8/BackendProject/blob/main/ERD_Diagram.png?raw=true)

### UML Diagram
![alt text](https://github.com/H8S8/BackendProject/blob/main/UML_Diagram.png?raw=true)

## Endpoints

### Stock
| | URL | | DESCRIPTION | EXAMPLE REQUEST BODY | EXAMPLE RESPONSE BODY |
---|---|---|---|---|---
|INDEX | localhost:8080/stocks | GET | Return a list of all stock | | |
|SHOW | localhost:8080/stocks/:id | GET | Returns a specific stock object | | |
|CREATE | localhost:8080/stocks/:id| POST | Creates a new stock object |```{ "itemId" : 3, "quantity" : 40, "expiryDate" : "23/05/2024", "unitPrice" : 20} ```| ``` {"id":4, "orderedItems":[], "item":{"id":3, "name":"Mint Chocolote Caramel", "productType":"DAIRY"},"quantity":40, "expiryDate":"23/05/24", "unitPrice":20}```|
|DELETE | localhost:8080/stocks/:id | DELETE | Removes a specific stock object | | |
|UPDATE | localhost:8080/stocks/:id| PATCH | Updates an existing stock object | ```{"quantity" : 60 } ```|``` {"id":3, "orderedItems":[], "item":{"id":3, "name":"Mint Chocolote Caramel", "productType":"DAIRY"}, "quantity":60, "expiryDate":"30/09/2024", "unitPrice":20}``` |


### Item
| | URL | | DESCRIPTION | EXAMPLE REQUEST BODY | EXAMPLE RESPONSE BODY |
---|---|---|---|---|---
|INDEX | localhost:8080/items | GET | Returns a list of all items | | |
|SHOW | localhost:8080/items/:id | GET | Returns details of a specific item | | |
|CREATE | localhost:8080/items| POST | Creates a new item object | ```{"name" : Broccoli, "productType" : "FRUITANDVEG"} ```| ``` {"id":4, "name":"Broccoli", "productType":"FRUITANDVEG", "stocks":null}```|
|DELETE | localhost:8080/items/:id | DELETE | Deletes an item object | | |
|UPDATE | localhost:8080/items/:id| PATCH | Updates an specific detail about an item | ```{"name" : Mint Chocolate }``` | ``` {"id":3, "name":"Mint Chocolate", "productType":"DAIRY", "stocks":[{"id":4, "orderedItems":[], "quantity":40, "expiryDate":"23/05/24", "unitPrice":20}, {"id":3, "orderedItems":[], "quantity":60, "expiryDate":"30/09/2024", "unitPrice":20}]}```|

### Order
| | URL | | DESCRIPTION | EXAMPLE REQUEST BODY | EXAMPLE RESPONSE BODY |
---|---|---|---|---|---
|INDEX | localhost:8080/orders | GET | Return a list of all orders | | |
|SHOW | localhost:8080/orders/:id | GET | Returns a specific order object | | |
|SHOW | localhost:8080/orders/:id/order-cost | GET | Returns a specific order cost for an order object | | |
|CREATE | localhost:8080/orders/:id| POST | Creates a new order object | ```{"supermarketId" : 3 }``` | ``` {"id":1, "orderStatus":"PENDING", "orderedItems":[], "supermarket":{"id":3, "name":"Sainsburys", "location":"Birmingham"}}``` |
|DELETE | localhost:8080/orders/:id | DELETE | Removes a specific order object | | |
|UPDATE | localhost:8080/orders/:id| PATCH | Updates a specific detail about an order | ```{"orderStatus" : OUT_FOR_DELIVERY } ```| ``` {"id":1, "orderStatus":"OUT_FOR_DELIVERY", "orderedItems":[], "supermarket":{"id":3, "name":"Sainsburys", "location":"Birmingham"}}```|

### Ordered Item
| | URL | | DESCRIPTION | EXAMPLE REQUEST BODY | EXAMPLE RESPONSE BODY |
---|---|---|---|---|---
|INDEX | localhost:8080/ordered-items | GET | Return a list of all orderd items| | |
|SHOW | localhost:8080/ordered-items/:id | GET | Returns a specific ordered item object | | |
|CREATE | localhost:8080/ordered-items/:id| POST | Creates a new ordered item object | ```{"stockId" : 3, "orderId" : 1, "orderQuantity" : 30} ```| ``` {"id":1, "order":{"id":1, "orderStatus":"PENDING", "supermarket":{"id":3, "name":"Sainsburys", "location":"Birmingham"}}, "stock":{"id":1, "item":{"id":1, "name":"Semi-skimmed milk", "productType":"DAIRY"}, "quantity":48, "expiryDate":"11/03/2024", "unitPrice":5}, "orderQuantity":2}```|
|DELETE | localhost:8080/ordered-items/:id | DELETE | Removes a specific ordered item object | | |
|UPDATE | localhost:8080/ordered-items/:id| PATCH | Updates an existing ordered item object | ```{"orderQuantity" : 90  }``` | ``` {"id":1, "order":{"id":1, "orderStatus":"PENDING", "supermarket":{"id":3, "name":"Sainsburys", "location":"Birmingham"}}, "stock":{"id":1, "item":{"id":1, "name":"Semi-skimmed milk", "productType":"DAIRY"}, "quantity":46, "expiryDate":"11/03/2024", "unitPrice":5}, "orderQuantity":4}```|

### Supermarket
| | URL | | DESCRIPTION | EXAMPLE REQUEST BODY | EXAMPLE RESPONSE BODY |
---|---|---|---|---|---
|SHOW | localhost:8080/supermarkets/:id | GET | Returns the details of a specific market | | |




