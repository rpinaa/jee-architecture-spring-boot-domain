DROP TABLE IF EXISTS ISSUE;

CREATE TABLE ISSUE (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL,
  description varchar(100) NOT NULL,
  type varchar(10) DEFAULT NULL,
  priority varchar(10) DEFAULT NULL,
  status varchar(15) DEFAULT NULL,
  register_date datetime DEFAULT NULL,
  change_date datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

insert into ISSUE(title, description, type, priority, status, register_date, change_date) values('Service Error', 'Runtime error on service layer', 'BUG', 'MEDIUM', 'IN_PROGRESS', '2016-11-28 15:16:14', '2016-11-28 15:16:14');
insert into ISSUE(title, description, type, priority, status, register_date, change_date) values('Logger Error', 'Runtime error on logger layer', 'BUG', 'HIGH', 'IN_PROGRESS', '2016-11-28 15:16:14', '2016-11-28 15:16:14');
insert into ISSUE(title, description, type, priority, status, register_date, change_date) values('Core Error', 'Runtime error on core layer', 'BUG', 'HIGH', 'OPEN', '2016-11-28 15:16:14', '2016-11-28 15:16:14');