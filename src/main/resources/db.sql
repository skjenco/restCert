DROP TABLE IF EXISTS employee_address;
DROP TABLE IF EXISTS employee_phone;
DROP TABLE IF EXISTS employee_salary;
DROP TABLE IF EXISTS org;
DROP TABLE IF EXISTS employees;

CREATE TABLE employees(
   id INT GENERATED ALWAYS AS IDENTITY,
   name VARCHAR(255) NOT NULL,
   UNIQUE(id)
);

CREATE TABLE employee_address(
   id INT GENERATED ALWAYS AS IDENTITY,
   employee_id INT,
   street_address VARCHAR(255) NOT NULL,
   city VARCHAR(255) NOT NULL,
   state VARCHAR(255) NOT NULL,
   country VARCHAR(255) NOT NULL,
   postal_code VARCHAR(255) NOT NULL,
   PRIMARY KEY(id),
   CONSTRAINT fk_employee_address
     FOREIGN KEY(employee_id)
     REFERENCES employees(id)
);

CREATE TABLE employee_phone(
   id INT GENERATED ALWAYS AS IDENTITY,
   employee_id INT,
   type VARCHAR(1) DEFAULT('H'),
   number VARCHAR(15) NOT NULL,
   PRIMARY KEY(id),
   CONSTRAINT fk_employee_phone
     FOREIGN KEY(employee_id)
     REFERENCES employees(id)
);

CREATE TABLE employee_salary(
   id INT GENERATED ALWAYS AS IDENTITY,
   employee_id INT,
   salary INT NOT NULL,
   PRIMARY KEY(id),
   CONSTRAINT fk_employee_salary
     FOREIGN KEY(employee_id)
     REFERENCES employees(id)
);

CREATE TABLE org(
   id INT GENERATED ALWAYS AS IDENTITY,
   employee_id INT,
   supervisor_id INT,
   PRIMARY KEY(id),
   CONSTRAINT fk_org
     FOREIGN KEY(employee_id)
     REFERENCES employees(id),
   CONSTRAINT fk_supervisor
     FOREIGN KEY(supervisor_id)
     REFERENCES employees(id),
   UNIQUE(employee_id)
);

-- Insert Employee Data
insert into employees (name)
values('CEO');

insert into employees (name)
values('Leida Lentz');

insert into employees (name)
values('Angla Alter');

insert into employees (name)
values('Reda Rayes');

insert into employees (name)
values('Jazmine Joubert');

insert into employees (name)
values('Golden Givens');

insert into employees (name)
values('Annabelle Arjona');

insert into employees (name)
values('Emerald Egan');

insert into employees (name)
values('Len Labadie');

insert into employees (name)
values('Georgeanna Gering');

insert into employees (name)
values('Ester Evatt');

insert into employees (name)
values('Magdalena Mikus');

insert into employees (name)
values('Andre Avina');

insert into employees (name)
values('Otha Odegaard');

insert into employees (name)
values('Clark Cotner');

insert into employees (name)
values('Allegra Avila');

insert into employees (name)
values('Keri Knighten');

insert into employees (name)
values('Hilton Hudec');

insert into employees (name)
values('Anastacia Auxier');

insert into employees (name)
values('Carita Coaxum');

insert into employees (name)
values('Lois Looby');

insert into employees (name)
values('Heidy Herlihy');

insert into employees (name)
values('Joan Jakubowski');

insert into employees (name)
values('Izola Inglis');

insert into employees (name)
values('Petrina Pilot');

insert into employees (name)
values('Pam Pendergraft');

insert into employees (name)
values('Tennie Tarpey');

insert into employees (name)
values('Mirtha Mujica');

insert into employees (name)
values('Loyd Lueders');

insert into employees (name)
values('Gerry Glines');

insert into employees (name)
values('Annamae Applegate');

insert into employees (name)
values('David Dey');

insert into employees (name)
values('Ocie Overfelt');

insert into employees (name)
values('Lila Lisle');

insert into employees (name)
values('Marshall Meisel');

insert into employees (name)
values('Adriene Antle');

insert into employees (name)
values('Julianna Jung');

insert into employees (name)
values('Melodi Merced');

insert into employees (name)
values('Nathanael Nunnally');

insert into employees (name)
values('Cherryl Cothern');

insert into employees (name)
values('Joycelyn Joerling');

insert into employees (name)
values('Irish Ickes');

insert into employees (name)
values('Song Seelye');

insert into employees (name)
values('Isabell Ice');

insert into employees (name)
values('Tamekia Theobald');

insert into employees (name)
values('Bo Beliveau');

insert into employees (name)
values('Adeline Althoff');

insert into employees (name)
values('Lavonda Lemke');

insert into employees (name)
values('Anh Amidon');

insert into employees (name)
values('Lakeshia Ladner');

insert into employees (name)
values('Diego Dowless');

--Insert Org Data
insert into org(employee_id, supervisor_id)
values(1, null);

insert into org(employee_id, supervisor_id)
values(2, 1);

insert into org(employee_id, supervisor_id)
values(3, 1);

insert into org(employee_id, supervisor_id)
values(4, 1);

insert into org(employee_id, supervisor_id)
values(5, 2);

insert into org(employee_id, supervisor_id)
values(6, 2);

insert into org(employee_id, supervisor_id)
values(7, 3);

insert into org(employee_id, supervisor_id)
values(8, 3);

insert into org(employee_id, supervisor_id)
values(9, 3);

insert into org(employee_id, supervisor_id)
values(10, 4);

insert into org(employee_id, supervisor_id)
values(11, 4);

insert into org(employee_id, supervisor_id)
values(12, 5);

insert into org(employee_id, supervisor_id)
values(13, 5);

insert into org(employee_id, supervisor_id)
values(14, 5);

insert into org(employee_id, supervisor_id)
values(15, 5);

insert into org(employee_id, supervisor_id)
values(16, 6);

insert into org(employee_id, supervisor_id)
values(17, 6);

insert into org(employee_id, supervisor_id)
values(18, 6);

insert into org(employee_id, supervisor_id)
values(19, 16);

insert into org(employee_id, supervisor_id)
values(20, 17);

insert into org(employee_id, supervisor_id)
values(21, 6);

insert into org(employee_id, supervisor_id)
values(22, 21);

insert into org(employee_id, supervisor_id)
values(23, 7);

insert into org(employee_id, supervisor_id)
values(24, 7);

insert into org(employee_id, supervisor_id)
values(25, 7);

insert into org(employee_id, supervisor_id)
values(26, 23);

insert into org(employee_id, supervisor_id)
values(27, 24);

insert into org(employee_id, supervisor_id)
values(28, 25);

insert into org(employee_id, supervisor_id)
values(29, 28);

insert into org(employee_id, supervisor_id)
values(30, 8);

insert into org(employee_id, supervisor_id)
values(31, 8);

insert into org(employee_id, supervisor_id)
values(32, 8);

insert into org(employee_id, supervisor_id)
values(33, 8);

insert into org(employee_id, supervisor_id)
values(34, 9);

insert into org(employee_id, supervisor_id)
values(35, 9);

insert into org(employee_id, supervisor_id)
values(36, 9);

insert into org(employee_id, supervisor_id)
values(37, 9);

insert into org(employee_id, supervisor_id)
values(38, 10);

insert into org(employee_id, supervisor_id)
values(39, 38);

insert into org(employee_id, supervisor_id)
values(40, 38);

insert into org(employee_id, supervisor_id)
values(41, 39);

insert into org(employee_id, supervisor_id)
values(42, 41);

insert into org(employee_id, supervisor_id)
values(43, 11);

insert into org(employee_id, supervisor_id)
values(44, 43);

insert into org(employee_id, supervisor_id)
values(45, 44);

insert into org(employee_id, supervisor_id)
values(46, 45);

insert into org(employee_id, supervisor_id)
values(47, 12);

insert into org(employee_id, supervisor_id)
values(48, 47);

insert into org(employee_id, supervisor_id)
values(49, 13);

insert into org(employee_id, supervisor_id)
values(50, 49);

insert into org(employee_id, supervisor_id)
values(51, 50);

--Insert Address Data
insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(1, '1 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(2, '2 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(3, '3 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(4, '4 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(5, '5 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(6, '6 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(7, '7 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(8, '8 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(9, '9 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(10, '10 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(11, '11 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(12, '12 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(13, '13 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(14, '14 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(15, '15 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(16, '16 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(17, '17 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(18, '18 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(19, '19 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(20, '20 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(21, '21 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(22, '22 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(23, '23 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(24, '24 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(25, '25 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(26, '26 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(27, '27 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(28, '28 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(29, '29 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(30, '30 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(31, '31 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(32, '32 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(33, '33 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(34, '34 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(35, '35 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(36, '36 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(37, '37 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(38, '38 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(39, '39 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(40, '40 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(41, '41 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(42, '42 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(43, '43 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(44, '44 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(45, '45 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(46, '46 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(47, '47 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(48, '48 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(49, '49 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(50, '50 Fake Street', 'City', 'State', 'Country', '1');

insert into employee_address(employee_id, street_address, city, state, country, postal_code)
values(51, '51 Fake Street', 'City', 'State', 'Country', '1');

--Insert Employee Phone Data
insert into employee_phone(employee_id, number, type)
values(1, '1-111-1111', 'W');

insert into employee_phone(employee_id, number, type)
values(2, '2-111-1111', 'W');

insert into employee_phone(employee_id, number, type)
values(3, '3-111-1111', 'W');

insert into employee_phone(employee_id, number, type)
values(4, '4-111-1111', 'W');

insert into employee_phone(employee_id, number, type)
values(5, '5-111-1111', 'W');

insert into employee_phone(employee_id, number)
values(6, '6-111-1111');

insert into employee_phone(employee_id, number)
values(7, '7-111-1111');

insert into employee_phone(employee_id, number)
values(8, '8-111-1111');

insert into employee_phone(employee_id, number)
values(9, '9-111-1111');

insert into employee_phone(employee_id, number)
values(10, '10-111-1111');

insert into employee_phone(employee_id, number)
values(11, '11-111-1111');

insert into employee_phone(employee_id, number)
values(12, '12-111-1111');

insert into employee_phone(employee_id, number)
values(13, '13-111-1111');

insert into employee_phone(employee_id, number)
values(14, '14-111-1111');

insert into employee_phone(employee_id, number)
values(15, '15-111-1111');

insert into employee_phone(employee_id, number)
values(16, '16-111-1111');

insert into employee_phone(employee_id, number)
values(17, '17-111-1111');

insert into employee_phone(employee_id, number)
values(18, '18-111-1111');

insert into employee_phone(employee_id, number)
values(19, '19-111-1111');

insert into employee_phone(employee_id, number)
values(20, '20-111-1111');

insert into employee_phone(employee_id, number)
values(21, '21-111-1111');

insert into employee_phone(employee_id, number)
values(22, '22-111-1111');

insert into employee_phone(employee_id, number)
values(23, '23-111-1111');

insert into employee_phone(employee_id, number)
values(24, '24-111-1111');

insert into employee_phone(employee_id, number)
values(25, '25-111-1111');

insert into employee_phone(employee_id, number)
values(26, '26-111-1111');

insert into employee_phone(employee_id, number)
values(27, '27-111-1111');

insert into employee_phone(employee_id, number)
values(28, '28-111-1111');

insert into employee_phone(employee_id, number)
values(29, '29-111-1111');

insert into employee_phone(employee_id, number)
values(30, '30-111-1111');

insert into employee_phone(employee_id, number)
values(31, '31-111-1111');

insert into employee_phone(employee_id, number)
values(32, '32-111-1111');

insert into employee_phone(employee_id, number)
values(33, '33-111-1111');

insert into employee_phone(employee_id, number)
values(34, '34-111-1111');

insert into employee_phone(employee_id, number)
values(35, '35-111-1111');

insert into employee_phone(employee_id, number)
values(36, '36-111-1111');

insert into employee_phone(employee_id, number)
values(37, '37-111-1111');

insert into employee_phone(employee_id, number)
values(38, '38-111-1111');

insert into employee_phone(employee_id, number)
values(39, '39-111-1111');

insert into employee_phone(employee_id, number)
values(40, '40-111-1111');

insert into employee_phone(employee_id, number)
values(41, '41-111-1111');

insert into employee_phone(employee_id, number)
values(42, '42-111-1111');

insert into employee_phone(employee_id, number)
values(43, '43-111-1111');

insert into employee_phone(employee_id, number)
values(44, '44-111-1111');

insert into employee_phone(employee_id, number)
values(45, '45-111-1111');

insert into employee_phone(employee_id, number)
values(46, '46-111-1111');

insert into employee_phone(employee_id, number, type)
values(47, '47-111-1111', 'C');

insert into employee_phone(employee_id, number, type)
values(48, '48-111-1111', 'C');

insert into employee_phone(employee_id, number, type)
values(49, '49-111-1111', 'C');

insert into employee_phone(employee_id, number, type)
values(50, '50-111-1111', 'C');

insert into employee_phone(employee_id, number, type)
values(51, '51-111-1111', 'C');

--Insert Employee Salary Data
insert into employee_salary(employee_id, salary)
values(1, 1000000);

insert into employee_salary(employee_id, salary)
values(2, 96400);

insert into employee_salary(employee_id, salary)
values(3, 94600);

insert into employee_salary(employee_id, salary)
values(4, 92800);

insert into employee_salary(employee_id, salary)
values(5, 91000);

insert into employee_salary(employee_id, salary)
values(6, 89200);

insert into employee_salary(employee_id, salary)
values(7, 87400);

insert into employee_salary(employee_id, salary)
values(8, 85600);

insert into employee_salary(employee_id, salary)
values(9, 83800);

insert into employee_salary(employee_id, salary)
values(10, 82000);

insert into employee_salary(employee_id, salary)
values(11, 80200);

insert into employee_salary(employee_id, salary)
values(12, 78400);

insert into employee_salary(employee_id, salary)
values(13, 76600);

insert into employee_salary(employee_id, salary)
values(14, 74800);

insert into employee_salary(employee_id, salary)
values(15, 73000);

insert into employee_salary(employee_id, salary)
values(16, 71200);

insert into employee_salary(employee_id, salary)
values(17, 69400);

insert into employee_salary(employee_id, salary)
values(18, 67600);

insert into employee_salary(employee_id, salary)
values(19, 65800);

insert into employee_salary(employee_id, salary)
values(20, 64000);

insert into employee_salary(employee_id, salary)
values(21, 62200);

insert into employee_salary(employee_id, salary)
values(22, 60400);

insert into employee_salary(employee_id, salary)
values(23, 58600);

insert into employee_salary(employee_id, salary)
values(24, 56800);

insert into employee_salary(employee_id, salary)
values(25, 55000);

insert into employee_salary(employee_id, salary)
values(26, 53200);

insert into employee_salary(employee_id, salary)
values(27, 51400);

insert into employee_salary(employee_id, salary)
values(28, 49600);

insert into employee_salary(employee_id, salary)
values(29, 47800);

insert into employee_salary(employee_id, salary)
values(30, 46000);

insert into employee_salary(employee_id, salary)
values(31, 44200);

insert into employee_salary(employee_id, salary)
values(32, 42400);

insert into employee_salary(employee_id, salary)
values(33, 40600);

insert into employee_salary(employee_id, salary)
values(34, 38800);

insert into employee_salary(employee_id, salary)
values(35, 37000);

insert into employee_salary(employee_id, salary)
values(36, 35200);

insert into employee_salary(employee_id, salary)
values(37, 33400);

insert into employee_salary(employee_id, salary)
values(38, 31600);

insert into employee_salary(employee_id, salary)
values(39, 29800);

insert into employee_salary(employee_id, salary)
values(40, 28000);

insert into employee_salary(employee_id, salary)
values(41, 26200);

insert into employee_salary(employee_id, salary)
values(42, 24400);

insert into employee_salary(employee_id, salary)
values(43, 22600);

insert into employee_salary(employee_id, salary)
values(44, 20800);

insert into employee_salary(employee_id, salary)
values(45, 19000);

insert into employee_salary(employee_id, salary)
values(46, 17200);

insert into employee_salary(employee_id, salary)
values(47, 15400);

insert into employee_salary(employee_id, salary)
values(48, 13600);

insert into employee_salary(employee_id, salary)
values(49, 11800);

insert into employee_salary(employee_id, salary)
values(50, 10000);

insert into employee_salary(employee_id, salary)
values(51, 8200);