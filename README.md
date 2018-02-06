Restaurant API
===============

_Author: Cher Huang (<xiaoxuah@uci.edu>)_

This application is a simple restaurant service that manages restaurants menus and items. It has three object types: restaurants, menus, and menu-items. Each restauratns has basic attributes such as name, rating, address, and a unique ID. 
Each restaurant can have a number of menus with attributes such as type (breakfast, lunch, dinner, drink. etc). Each menu can have a numer of items with attributes such as type, price, good-for-vegan etc.

| Table Name          | Attributes                                                                                                                                        | Notes                                                               |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| restaurants         | id:varchar(10) (primary key), name:varchar(100),rating:float,address:varchar(255)                                                                 | required,required,optional,required,"id" should be "AUTO_INCREMENT" |
| menu_in_restaurants | id:varchar(10) (primary key),rId:varchar(10), referencing restaurants.id, type:varchar(50)                                                        | all attributes required                                             |
| items_in_menu       | id: integer, menuId:integer, referencing menu_in_restaurants, rId:varchar(10), referencing restaurants.id,price: integer, vegan: varchar(50) | all attributes,required; "id" should be"AUTO_INCREMENT"             |


## Characteristics of this API:
1)	RestFUL 
2)	Scalable
3)	Secured: the API  will be accessible only in HTTPS mode 

## Resources and Actions

| URL                                         | HTTP | Method | Operation                                                                                     |
|---------------------------------------------|------|--------|-----------------------------------------------------------------------------------------------|
| /api/restaurants                            |      | Get    | Returns an array of restaurant                                                                |
| /api/restaurants/:id                        |      | Get    | Return the restaurant with id of :id                                                          |
| /api/restaurants/:id/menus                  |      | Get    | Return an array of menu associated with restaurant id of :id                                  |
| /api/restaurants/:rid/menus/:mid            |      | Get    | Return the menu associated with restaurant id of :rid and menu id of :mid                     |
| /api/restaurants/:rid/menus/:mid/items      |      | Get    | Return the items associated with restaurant id of :rid and menu id of :mid                    |
| /api/restaurants/:rid/menus/:mid/items/:iid |      | Get    | Return the item associated with restaurant id of :rid and menu id of :mid and item id of :iid |
|                                             |      |        |                                                                                               |



## How to use:
1) Git clone or download folder
2) Navigate to folder
3) mvn spring-boot:run


