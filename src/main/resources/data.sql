
INSERT INTO user_group(id,name) VALUES (1,'STYLIST');
INSERT INTO user_group(id,name) VALUES (2,'ADMIN');
INSERT INTO user_group(id,name) VALUES (3,'CUSTOMER');
INSERT INTO user_group(id,name) VALUES (4,'DEVELOPER');

INSERT INTO role(id,name) VALUES (1,'ADD_APPOINTMENT');
INSERT INTO role(id,name) VALUES (2,'UPDATE_APPOINTMENT');
INSERT INTO role(id,name) VALUES (3,'DELETE_APPOINTMENT');
INSERT INTO role(id,name) VALUES (4,'SELECT_USER');

INSERT INTO group_role(id, role_id, group_id) VALUES (1,1,1);
INSERT INTO group_role(id, role_id, group_id) VALUES (2,2,1);
INSERT INTO group_role(id, role_id, group_id) VALUES (3,3,1);
INSERT INTO group_role(id, role_id, group_id) VALUES (4,1,2);
INSERT INTO group_role(id, role_id, group_id) VALUES (5,2,2);
INSERT INTO group_role(id, role_id, group_id) VALUES (6,3,2);
INSERT INTO group_role(id, role_id, group_id) VALUES (7,1,3);
INSERT INTO group_role(id, role_id, group_id) VALUES (8,2,3);
INSERT INTO group_role(id, role_id, group_id) VALUES (9,3,3);

INSERT INTO user_status(id, name) VALUES (3,'AVAILABLE');
INSERT INTO user_status(id, name) VALUES (4,'NOT_AVAILABLE');
INSERT INTO user_status(id, name) VALUES (5,'BLOCKED');

insert into settings(name,appointment_slot_length,appointment_slot_count,appointment_slot_start_time) values('appointment','00:30:00',16,'09:00:00');

INSERT INTO user(name,surname,username, password,status_id,group_id,register_dt) VALUES ('Sarkhan','Rasullu','sarkhan','$2a$10$8wSTmoHJysCh88RSvjAn9efu8gVsBs5Ag4S4GooN9nLxquwTF6jDS',3,1,CURRENT_DATE());
