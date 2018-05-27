insert  into `sysrole`(`id`,`name`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

insert  into `sysuser`(`id`,`password`,`username`) values (1,'root','$2a$10$wKN95dDXsNu0/wXbOBRmYOaZ4kTElN4/Jc8UB56O18vOBr9DAwx72'),(2,'cole','$2a$10$oChheFqpAuP.2djzhJErv.O2f3i6/WpJj4/kEJ2oHCYMpIOf8BTNy');

insert  into `sysuser_roles`(`SysUser_id`,`roles_id`) values (1,1),(2,2);