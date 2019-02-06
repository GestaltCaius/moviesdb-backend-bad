# Getting started on Ubuntu

Install postgresql
```sh
sudo apt-get install postgresql # on Ubuntu
sudo pacman -S postgresql # on archlinux
```

check if postgresql has been installed
```
/etc/init.d/postgresql status # on Ubuntu

# On ArchLinux
sudo -u postgres -i
initdb -D '/var/lib/postgres/data'
systemctl start postgresql            
systemctl enable postgresql
```

create *technocrats* user
```
sudo -u postgres createuser --interactive --password technocrats

Shall the new role be a superuser? (y/n) n
Shall the new role be allowed to create databases? (y/n) y
Shall the new role be allowed to create more new roles? (y/n) n
Password: C8BxprLB
```

Create moviesdb database owned by technocrats user.

```
sudo -u postgres createdb moviesdb -O technocrats
```


Edit those two lines, so Spring Boot can access our database (md5 and peer to trust):
```
sudo vim /etc/postgresql/10/main/pg_hba.conf
```
> ```
> # "local" is for Unix domain socket connections only
> local   all             all                                     trust
> # IPv4 local connections:
> host    all             all             127.0.0.1/32            trust
> ```

Restart database.
```
sudo service postgresql restart
psql -U technocrats -d moviesdb -W
```

You're done.

# References

[ZetCode Get Started with PostgreSQL and Spring Boot](http://zetcode.com/springboot/postgresql/)