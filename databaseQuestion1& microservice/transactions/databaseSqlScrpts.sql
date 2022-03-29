SELECT * FROM transactions.customers;

SELECT * FROM transactions.customer_transaction;

truncate table customers;
insert into customers (id, name)
values(1, 'jone wick'),
(2, 'sam smith');


truncate table customer_transaction;
insert into customer_transaction (id, customer_id, amount, date_created)
values(1 , 1, 12, '2022-01-19'),
(2, 1, 10, '2022-01-20'),
(3, 1, 23, '2022-02-22'),
(4, 2, 13, '2022-02-23'),
(5, 1, 10, '2022-01-21'),
(6, 2, 11, '2022-02-24'),
(7, 1, 16, '2022-02-27');



select ROW_NUMBER() OVER(ORDER BY (select null)) as id,
name as `Customer name`,
sum(amount) as `Total amount`,
concat(YEAR(date_created), '-',
lpad(MONTH(date_created), 2, '0')) as `Year month`
from customer_transaction
join customers on customers.id=customer_transaction.customer_id
group by `customer_id`, `Year month`
order by `customer_id`, `Year month`

