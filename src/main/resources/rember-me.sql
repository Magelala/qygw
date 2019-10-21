/*#注意：这个数据表必须要创建，不得做任何修改
#数据库应包含一个persistent_logins使用以下SQL（或等效SQL）创建的表：
*/

create table persistent_logins (username varchar(64) not null,
                                series varchar(64) primary key,
                                token varchar(64) not null,
                                last_used timestamp not null)