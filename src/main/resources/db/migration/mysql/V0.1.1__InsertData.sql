-- department
INSERT INTO department(id, d_name, d_id, deleted)
VALUES (0, '青青股份有限公司', NULL, FALSE),
       (1, '研发部', 0, FALSE),
       (2, '宣发部', 0, FALSE),
       (3, '法务部', 0, FALSE),
       (4, '财务部', 0, FALSE),
       (5, '人事部', 0, FALSE);

-- schedule
INSERT INTO schedule(id, start_time, end_time, start_date, end_date, frequency, frequency_value)
VALUES (0, '09:00:00', '12:00:00', NULL, NULL, 2, 1),
       (1, '09:00:00', '12:00:00', NULL, NULL, 2, 2),
       (2, '09:00:00', '12:00:00', NULL, NULL, 2, 3),
       (3, '09:00:00', '12:00:00', NULL, NULL, 2, 4),
       (4, '09:00:00', '12:00:00', NULL, NULL, 2, 5),
       (5, '14:30:00', '17:00:00', NULL, NULL, 2, 1),
       (6, '14:30:00', '17:00:00', NULL, NULL, 2, 2),
       (7, '14:30:00', '17:00:00', NULL, NULL, 2, 3),
       (8, '14:30:00', '17:00:00', NULL, NULL, 2, 4),
       (9, '14:30:00', '17:00:00', NULL, NULL, 2, 5),
       (10, '09:00:00', '17:00:00', NULL, NULL, 0, 20211126);

-- user
INSERT INTO user(id, user_name, passwd, phone_number, resident_id, email_addr, address, position, deleted)
VALUES (0, '张三', 'zs', '12345678901', '234667199701010000', 'zs@gmail.com', '上海市市辖区浦东新区外高桥保税区005号606室', '研发部部长', FALSE),
       (1, '李四', 'ls', '12345678901', '234667199701010000', 'ls@gmail.com', '上海市市辖区浦东新区外高桥保税区005号607室', '宣发部部长', FALSE),
       (2, '王五', 'ww', '12345678901', '234667199701010000', 'ww@gmail.com', '上海市市辖区浦东新区外高桥保税区005号608室', '法务部部长', FALSE),
       (3, '刘二', 'le', '12345678901', '234667199701010000', 'le@gmail.com', '上海市市辖区浦东新区外高桥保税区005号609室', '财务部部长', FALSE),
       (4, '孙大', 'sd', '12345678901', '234667199701010000', 'sd@gmail.com', '上海市市辖区浦东新区外高桥保税区005号610室', '人事部部长', FALSE),
       (5, '刘泡泡', 'lpp', '12345678901', '234667199701010000', 'ls@gmail.com', '上海市市辖区浦东新区外高桥保税区005号607室', '宣发部部长',
        FALSE);

-- leave_request
INSERT INTO leave_request(id, user_id, start_time, end_time, submit_time, reason, status, checker_id)
VALUES (0, 5, '2021-11-22 09:00:00', '2021-11-22 17:00:00', '2021-11-20 22:00:00', '参加朋友婚宴', '待审核', NULL);

-- department_user
INSERT INTO department_user(user_id, d_id)
VALUES (0, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (4, 5),
       (5, 5);

-- department_manager
INSERT INTO department_manager(manager_id, d_id)
VALUES (0, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (4, 5);

-- department_leave_request
INSERT INTO department_leave_request(request_id, d_id)
VALUES (0, 5);

-- user_attendance_schedule
INSERT INTO user_attendance_schedule(user_id, schedule_id)
VALUES (0, 0),(0, 1),(0, 2),(0, 3),(0, 4),(0, 5),(0, 6),(0, 7),(0, 8),(0, 9),
       (1, 0),(1, 1),(1, 2),(1, 3),(1, 4),(1, 5),(1, 6),(1, 7),(1, 8),(1, 9),
       (2, 0),(2, 1),(2, 2),(2, 3),(2, 4),(2, 5),(2, 6),(2, 7),(2, 8),(2, 9),
       (3, 0),(3, 1),(3, 2),(3, 3),(3, 4),(3, 5),(3, 6),(3, 7),(3, 8),(3, 9),
       (4, 0),(4, 1),(4, 2),(4, 3),(4, 4),(4, 5),(4, 6),(4, 7),(4, 8),(4, 9),
       (5, 0),(5, 1),(5, 2),(5, 3),(5, 4),(5, 5),(5, 6),(5, 7),(5, 8),(5, 9);

-- user_attendance_time
INSERT INTO user_attendance_time(user_id, time)
VALUES (5, '2021-11-19 08:55:00');

-- user_attendance_leave
INSERT INTO user_attendance_leave(user_id, schedule_id)
VALUES (5, 10);

