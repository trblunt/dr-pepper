
INSERT INTO SUser VALUES
-- patients
(1, 'Henry OM', 'henry@email.com', 'Tempe, AZ', '10/26/2000'),
(2, 'Winston Smith', 'wsmith@mot.o', 'Oceania, UK', '1/1/1984'),
-- nurses
(3, 'Nurse Ratched', 'cuckoo@flewover.com', 'Oregon', '2/1/1962'),
-- doctors
(4, 'Dr. Pepper', 'pepper@health.com', 'Tempe, AZ', '7/3/1990'),
(5, 'Dr. Coke', 'coke@health.com', 'Tempe, AZ', '1/1/1984');
SELECT setval('user_id_seq', 5, true);

INSERT INTO Patient VALUES
(1, 'Blue Cross', 4932, 'Pharm Addr'),
(2, 'ministry of health', 2139, 'Another Pharm Addr');


INSERT INTO Staff VALUES
-- nurses
(3, 'nratched', 'evil', FALSE),
-- drs
(4, 'pepper', 'pass123', TRUE),
(5, 'coke', 'secret_recipe', TRUE);


INSERT INTO Pharmacy VALUES
('Dr. Peppers Clinic', 'Tempe, AZ');


INSERT INTO Visit VALUES  
(1, 1, '5/23/2016', 68, 155, 98.3, 100, 80, 'is cool', 'yeah'),
(2, 1, '9/3/2018', 69, 170, 97.9, 132, 90, 'is still cool', 'yep'),
(3, 2, '1/1/1984', 52, 120, 100.3, 140, 120, 'is loyal', 'no');
SELECT setval('visit_id_seq', 3, true);


INSERT INTO Perscription VALUES
(1, 2, 'red pill', 1);
SELECT setval('perscription_id_seq', 1, true);


INSERT INTO Allergy VALUES
(1, 2, 'Gluten');
SELECT setval('allergy_id_seq', 1, true);
