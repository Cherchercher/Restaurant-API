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

| URL                                     | HTTP | Method | Operation                                                                                     |
|-----------------------------------------|------|--------|-----------------------------------------------------------------------------------------------|
| /restaurants                            |      | Get    | Returns an array of restaurant                                                                |
| /restaurants/:id                        |      | Get    | Return the restaurant with id of :id                                                          |
| /restaurants/:id/menus                  |      | Get    | Return an array of menu associated with restaurant id of :id                                  |
| /restaurants/:rid/menus/:mid            |      | Get    | Return the menu associated with restaurant id of :rid and menu id of :mid                     |
| /restaurants/:rid/menus/:mid/items      |      | Get    | Return the items associated with restaurant id of :rid and menu id of :mid                    |
| /restaurants/:rid/menus/:mid/items/:iid |      | Get    | Return the item associated with restaurant id of :rid and menu id of :mid and item id of :iid |
| /menus                                  |      | Get    | Return an array of menus                                                                      |
| /menus/:id                              |      | Get    | Return a menu with id of :id                                                                  |
| /menus/:id/items                        |      | Get    | Return all items associated with menu id of :id                                               |
| /menus/:mid/items/:id                   |      | Get    | Return an item associated with menu id of :mid and item id of :id                             |
| /items                                  |      | Get    | Return an array of items                                                                      |
| /items/:id                              |      | Get    | Return an item with id of :id                                                                 |
| /items/:id                              |      | Delete | Delete an item with id of :id                                                                 |
| /menus/:id                              |      | Delete | Delete a menu with id of :id                                                                  |
| /restaurants/:id                        |      | Delete | Delete a restaurant with id of :id                                                            |
| /restaurants                            |      | Post   | Add a new restaurant                                                                          |
| /restaurants/:rId/menus                 |      | Post   | Add a new menu to restaurant with restaurant id of :rId                                       |
| /restaurants/:rId/menus/:iId            |      | Post   | Add a new item to a menu with restaurant id of :rId and item id of :iId                       |
| /menus/                                 |      | Post   | Add a new menu                                                                                |
| /menus/:mid/items                       |      | Post   | Add a new item to menu with menu id of :mid                                                   |
| /items/                                 |      | Post   | Add a new item                                                                                |                                                       |

## How to use:
1) Git clone or download folder
2) Navigate to folder
3) mvn spring-boot:run


