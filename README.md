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
In src > main > webapp > WEB-INF > resources.xml make sure that username and password matches the one you use for your database.


To add test users:   
##### Add normal user
1. In the *accounts* table insert row:  

| id  | email| hashed_password          | role |
| --- | ---- | ------------------------ | ---- |
| 0   | test | aMr9Uy1fDNsGJStF4vWZ9Q== | User |  

2. The login info for this user:
    * Email: test
    * Password: test
    
##### Add admin user
1. In the *accounts* table insert row:  

| id  | email | hashed_password          | role  |
| --- | ----- | ------------------------ | ----- |
| -1  | admin | gwBdINtuCcv9k+V3I2z1vQ== | Admin |  

2. The login info for this user:
    * Email: admin
    * Password: admin