# Example and Seed Data Tables

## Example Data Tables
### Users Entity  
| UserID PK | Email | Username | Password |  IsAdmin   |
|-----------|---------|----------|--------------|--------------|
| 1 | AdminGuy@email.fake | Admin | P45SW0RD123 | True   |
| 2 | RichManBill@email.fake | RMBill | M0n3y | False   |
| 3 | SalaryWorker@email.fake | CrashedCarAtWork | ImBr0k3 | False   |
| 4 | TeenDriver@email.fake | SpendingDadsCash | ICra5h3D | False   |
| 5 | WantsACar@email.fake | ICantWalkAnymore | C4rP13a5e | False  |

### Inventory Entity  
| InventoryID PK | Name | Description  | Price | 
|-----------|---------|----------|--------------|
| 1 | 2012 Mazda CX-7 | Great condition, used brakes, and new tires| 21190 |
| 2 | 2023 Acura Integra | Okay condition, old brakes, and used tires | 31300 |
| 3 | 2022 Ford F-150 | Poor condition, old brakes, and bald tires | 31520 |
| 4 | 2003 Honda Accord | Okay condition, used brakes, and used tires | 23000 |
| 5 | 2016 BMW Gran Turismo | Like new condition, new brakes, and new tires | 49200 |

### Shipping Entity  
| ShippingID PK | Name | Cost |
|-----------|---------|----------|
| 1 | Overnight | 29 | 
| 2 | 3-Day | 19 | 
| 3 | Ground | 0 | 

### Sales Entity  
| SalesID PK | UserID FK | InventoryID FK | ShippingID FK |  TotalCost   |
|-----------|---------|----------|--------------|--------------|
| 1 | 1 | 5 | 1 | 52182.74  |
| 2 | 3 | 1 | 3 | 22461.40   |
| 3 | 4 | 2 | 2 | 33198.14  |
| 4 | 5 | 3 | 1 | 33411.2   |




## Seed Data Tables

### Users Entity  
| UserID PK | Email | Username | Password |  IsAdmin   |
|-----------|---------|----------|--------------|--------------|
| 1 | AdminGuy@email.fake | Admin | P45SW0RD123 | True   |

### Shipping Entity  
| ShippingID PK | Name | Cost |
|-----------|---------|----------|
| 1 | Overnight | 29 | 
| 2 | 3-Day | 19 | 
| 3 | Ground | 0 | 
