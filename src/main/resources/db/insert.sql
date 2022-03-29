
truncate table staff;

truncate table visitor;

insert into staff(staff_id, staff_name, phone_number, email_address, address)
values (1, 'merlin isle' , '0999999999', 'merlin@gmail.com', '45 adebowale street yaba'),
 (2, 'micheal brown' , '888888999', 'micheal@gmail.com', '5 wale street island'),
 (3, 'mike smith' , '09989797999', 'mike@gmail.com', '4 adebom street palmgroove'),
 (4, 'bisi praise' , '08167546383', 'bisi@gmail.com', '18 olayemi street ladi-lak');


insert into visitor(visitor_id, visitor_name, phone_number, email_address, address)
values (26, 'merlin isle' , '08187463562', 'merlin@gmail.com', '45 adebowale street yaba'),
       (27, 'micheal brown' , '08184763738', 'micheal@gmail.com', '5 wale street island'),
       (28, 'sam smith' , '09178363989', 'sam@gmail.com', '4 adebom street palmgroove'),
       (29, 'bassi joel' , '08167546383', 'bassi@gmail.com', '18 olayemi street ladi-lak');

