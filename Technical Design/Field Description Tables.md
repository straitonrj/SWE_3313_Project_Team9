# Field Description Tables

### User Entity 
| Property | Type | Default | Nullable | Relationship | Notes |
|----------|------|---------|----------|--------------|-------|
|  UserID  | Primary key, integer, identity | N/A | No | Related to Sales.UserID | Unique for every user |
|  Username  | String  | N/A   | No |              |       |
|  Password  | String  | N/A   | No |              |       |
|  Email     | String  | N/A   | No |              |       |
|  IsAdmin   | Boolean | False | No |              | Determines if a user is an admin or not |

### Inventory Entity 
| Property | Type | Default | Nullable | Relationship | Notes |
|----------|------|---------|----------|--------------|-------|
|  InventoryID  | Primary key, integer, identity | N/A | No | Related to Sales.InventoryID | Unique for every item |
|  Name      | String  | N/A  | No  |              |       |
|  Description | String  | Empty "" | Yes |              |       |
|  Price     | Double  | N/A  | No  |              |       |

### Shipping Entity 
| Property | Type | Default | Nullable | Relationship | Notes |
|----------|------|---------|----------|--------------|-------|
|  ShippingID  | Primary key, integer, identity | 3 | No | Related to Sales.ShippingID | 3 diffrent IDs are used to determine the preferred shipping method |
|  ShippingSpeed  | String  | "Ground"   | No |              |       |
|  ShippingCost   | String  | 0 | No |              |       |

### Sales Entity 
| Property | Type | Default | Nullable | Relationship | Notes |
|----------|------|---------|----------|--------------|-------|
|  SalesID  | Primary key, integer, identity | N/A | No |   | Unique for every sale |
|  UserID  | Foreign key, integer   | N/A   | No         | Related to User.UserID |       |
|  InventoryID     | Foreign key, integer  | N/A   | No | Related to Inventory.InventoryID |       |
|  ShippingID   | Foreign key, integer | N/A | No       | Related to Shipping.ShippingID |     |
|  TotalCost  | Double  | 0   | No |       |       |
