# Merchant-Onboarding
In this project thier is an admin entry in database which can add several merchants , every merchant may have their multiple sub-merchants .The Merchant Onboarding Project is a comprehensive solution designed to streamline and simplify the process of adding, updating, and editing merchant data within a MySQL database. This project revolves around the creation of RESTful APIs, which serve as the backbone for managing merchant information efficiently.

Key Features :-

1. Login Api :- The admin and merchant can login through email and password . If the admin logins then he can see all merchants in response or if the merchant logins then he can see his submerchants ,
   if email and password doesn't match with database  then we throws error.
   
2. Add Api :- The admin can add merchants with given paramters (email,phone,merchantType,businessName) in the request body and it will get username and password in response by which he can login further,
              The Merchant can add Sub-merchants with given paramters (email,phone,merchantType,businessName) in the request body. Both of the data will store in Mysql database.

3. Delete Api :- The admin can delete any merchant with this api , after deleting random merchant the sub-merchants of that merchant will automatically deletes . Also a merchant can delete any single sub-merhant.

4. Get Api :- We can get any merchant , sub-merchant data by searching thier business name Or we can get all merchants , submerchants list with this api. 

