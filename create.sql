CREATE DATABASE farmer_global;

\c farmer_global

CREATE TABLE farmers (
id SERIAL PRIMARY KEY, name varchar, location varchar, number varchar, produce varchar, amountOfProduceInKg int, email varchar, priceOf1kgOfProduce int, wallet int);

CREATE TABLE reviews (
    id SERIAL PRIMARY KEY, farmername varchar, writtenby varchar, content varchar
);

CREATE DATABASE farmer_global_test WITH TEMPLATE farmer_global;

