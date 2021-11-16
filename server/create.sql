DROP SCHEMA Public CASCADE;
CREATE SCHEMA Public;

CREATE SEQUENCE user_id_seq;

CREATE TABLE SUser (
    user_id BIGINT NOT NULL DEFAULT NEXTVAL('user_id_seq'),
    name VARCHAR(100),
    email VARCHAR(50),
    address VARCHAR(1000),
    dob DATE,
    -- key
    PRIMARY KEY (user_id)
);


CREATE TABLE Patient (
    user_id BIGINT,
    insuranceProvider VARCHAR(50),
    insuranceID INT,
    pharmacyAddress VARCHAR(50),
    -- key
    FOREIGN KEY (user_id) REFERENCES SUser(user_id),
    PRIMARY KEY (user_id)
);


CREATE TABLE Staff (
    user_id BIGINT,
    username VARCHAR(50),
    password VARCHAR(50),
    isDoctor BOOLEAN, -- this is weird, but the only difference is what
    -- they can do, docotrs and nurses share all other attributes
    -- key
    FOREIGN KEY (user_id) REFERENCES SUser(user_id),
    PRIMARY KEY (user_id)
);

CREATE TABLE Pharmacy (
    name VARCHAR(100) NOT NULL,
    address VARCHAR(1000),
    -- key
    PRIMARY KEY (name)
);

-- do we need a clinic??

CREATE SEQUENCE visit_id_seq;

CREATE TABLE Visit (
    visit_id BIGINT NOT NULL DEFAULT NEXTVAL('visit_id_seq'),
    patient_id SERIAL NOT NULL,
    date DATE,
    -- vitals
    height INT,
    weight INT,
    temp FLOAT,
    bpSystolic INT,
    bpDiastolic INT,
    testName VARCHAR(50),
    testResult VARCHAR(50),
    -- key
    FOREIGN KEY (patient_id) REFERENCES Patient(user_id),
    PRIMARY KEY (visit_id)
);

CREATE SEQUENCE perscription_id_seq;

CREATE TABLE Perscription (
    perscription_id BIGINT NOT NULL DEFAULT NEXTVAL('perscription_id_seq'),
    user_id SERIAL NOT NULL,
    medicationName VARCHAR(50),
    quantityPerDay INT,
    -- key
    FOREIGN KEY (user_id) REFERENCES Patient(user_id),
    PRIMARY KEY (perscription_id)
);

CREATE SEQUENCE allergy_id_seq;

CREATE TABLE Allergy (
    allergy_id BIGINT NOT NULL DEFAULT NEXTVAL('allergy_id_seq'),
    user_id SERIAL NOT NULL,
    name VARCHAR(50),
    -- key
    FOREIGN KEY (user_id) REFERENCES Patient(user_id),
    PRIMARY KEY (allergy_id)
);