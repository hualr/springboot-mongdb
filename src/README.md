# docker命令

```bash
docker exec -it mongo bash
mongo

use admin
db.createUser({ user: 'admin',  pwd: '123456',  roles: [ { role: "root", db: "admin" } ] });
db.auth("admin","123456")

use test
db.createUser({ user: 'test', pwd:'test', roles: [ {role:"readWrite",db:"testdb"}]});
db.auth("test","test")
```

# 登录

选择leagacy方式登录


# 阿里云IP

101.201.67.114
