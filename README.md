# PSK shop project
## Setting up the project:
### Project files:
1. Follow [Vaidas instructions](http://klevas.mif.vu.lt/~vaidasj/tp/pasiruosimas.html) first point *(Programinės įrangos diegimas)*
2. Copy `/login.config` file to `{TomEE-install-dir}/conf/` directory  
3. Copy `/setenv.bat` file to `{TomEE-install-dir}/bin/` directory
### Database:
The database used is PostgreSQL.  
It can be downloaded [here.](https://www.postgresql.org/download/)  
The SQLs to create tables are under src > main > resources > sqlScripts


To add test users:   
##### Add normal user
1. In the *accounts* table insert row:  

| id  | email| hashed_password          |
| --- | ---- | ------------------------ |
| 1   | test | aMr9Uy1fDNsGJStF4vWZ9Q== |    

2. In the *account_groups* table insert row:

| account_id | group_name |
| ---------- | ---------- |
| 1          | User       |

3. The login info for this user:
    * Email: test
    * Password: test
    
##### Add admin user
1. In the *accounts* table insert row:  

| id  | email | hashed_password          |
| --- | ----- | ------------------------ |
| 2   | admin | gwBdINtuCcv9k+V3I2z1vQ== |    

2. In the *account_groups* table insert row:

| account_id | group_name |
| ---------- | ---------- |
| 2          | Admin      |

3. The login info for this user:
    * Email: admin
    * Password: admin