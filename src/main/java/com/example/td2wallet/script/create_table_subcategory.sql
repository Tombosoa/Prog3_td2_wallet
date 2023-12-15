CREATE TYPE subcategory_enum AS ENUM (
  'Food_and_Drinks',
    'Groceries',
    'Coffee_Shops',
    'Fast_Food',
    'Online_Shopping',
    'Pets',
    'Jewelry_Accessories',
    'Gifts',
    'Electronics_Accessories',
    'Children',
    'Home_Garden',
    'Stationery',
    'Pharmacy_Convenience',
    'Health_Beauty',
    'Leisure_Time',
    'Clothing_Shoes',
    'Housing',
    'Property_Insurance',
    'Utilities',
    'Maintenance_Repairs',
    'Rent',
    'Mortgage',
    'Services',
    'Transportation',
    'Long_Distance',
    'Taxi',
    'Public_Transportation',
    'Business_Travel',
    'Vehicle',
    'Vehicle_Insurance',
    'Fuel',
    'Vehicle_Maintenance',
    'Rental',
    'Rentals',
    'Parking',
    'Leisure',
    'Alcohol_Tobacco',
    'Wellness_Beauty',
    'Culture_Sports_Events',
    'Education_Development',
    'Life_Events',
    'Books_Audio_Subscriptions',
    'Hobbies',
    'Lottery_Gambling',
    'Charity_Organization',
    'Healthcare_Doctor',
    'Sports_Fitness',
    'TV_Streaming',
    'Holidays_Travel_Hotels',
    'Multimedia_Computers',
    'Internet',
    'Software_Applications_Games',
    'Postal_Services',
    'Phone_Mobile_Phone',
    'Financial_Expenses',
    'Family_Allowances',
    'Fines',
    'Insurances',
    'Warning',
    'Charges_Fees',
    'Loan_Interest',
    'Taxes',
    'Investments',
    'Real_Estate',
    'Collections',
    'Savings',
    'Financial_Investments',
    'Vehicles_Personal_Property',
    'Income',
    'Family_Allowances_Income',
    'Gifts_Income',
    'Checks_Discounts',
    'Subsidies_Donations',
    'Interests_Dividends',
    'Lottery_Gambling_Income',
    'Loan_Rental',
    'Refunds_Tax_Purchases',
    'Rental_Income',
    'Salaries_Invoices',
    'Sales',
    'Other',
    'Missing',
    'Unknown_Expense',
    'Unknown_Income'

);

CREATE TABLE  IF NOT EXISTS subcategory (id SERIAL primary key,subcategory_name subcategory_enum, category_id int  references category(id));
INSERT INTO subcategory ( subcategory_name, category_id)
SELECT 'Fast Food'
WHERE NOT EXISTS (
    SELECT 1 FROM subcategory
    WHERE subcategory_name='Fast Food' and category_id = 1
    );
INSERT INTO subcategory ( subcategory_name, category_id)
SELECT 'Salaries Invoices'
    WHERE NOT EXISTS (
    SELECT 1 FROM subcategory
    WHERE subcategory_name='Salaries Invoices' and category_id = 2
    );
INSERT INTO subcategory ( subcategory_name , category_id)
SELECT 'Lottery Gambling Income'
    WHERE NOT EXISTS(
    SELECT 1 FROM subcategory
    WHERE subcategory_name='Lottery Gambling Income' and category_id = 1
);