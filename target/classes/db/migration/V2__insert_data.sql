-- Owners
INSERT INTO owner (id, name, phone_number) VALUES (1, 'Ravi Kumar', '+919802883865');
INSERT INTO owner (id, name, phone_number) VALUES (2, 'Anita Sharma', '+919416447117');

-- Pets
INSERT INTO pet (id, name, age, owner_id) VALUES (1, 'Tommy', 3, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (2, 'Bella', 5, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (3, 'Max', 2, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (4, 'Luna', 4, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (5, 'Charlie', 1, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (6, 'Lucy', 3, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (7, 'Cooper', 6, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (8, 'Daisy', 2, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (9, 'Rocky', 5, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (10, 'Molly', 4, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (11, 'Buddy', 7, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (12, 'Sadie', 3, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (13, 'Jack', 2, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (14, 'Chloe', 1, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (15, 'Zeus', 5, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (16, 'Lilly', 4, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (17, 'Toby', 3, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (18, 'Sophie', 6, 2);
INSERT INTO pet (id, name, age, owner_id) VALUES (19, 'Riley', 2, 1);
INSERT INTO pet (id, name, age, owner_id) VALUES (20, 'Zoe', 1, 2);

-- Vaccines
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (1, 'Rabies', '2024-07-01 10:00:00', 1);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (2, 'Distemper', '2024-06-15 14:30:00', 2);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (3, 'Parvovirus', '2024-07-10 09:15:00', 1);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (4, 'Hepatitis', '2024-05-20 11:45:00', 3);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (5, 'Leptospirosis', '2024-07-05 13:00:00', 5);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (6, 'Rabies', '2024-07-03 15:00:00', 7);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (7, 'Distemper', '2024-06-18 10:30:00', 9);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (8, 'Parvovirus', '2024-07-12 09:00:00', 11);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (9, 'Hepatitis', '2024-05-25 12:00:00', 13);
INSERT INTO vaccine (id, vaccine_name, vaccination_time, pet_id) VALUES (10, 'Leptospirosis', '2024-07-08 14:15:00', 15);
