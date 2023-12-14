CREATE TYPE subcategory_enum AS ENUM (
  'Food and Drinks',
    'Groceries',
    'Coffee Shops',
    'Fast Food',
    'Online Shopping',
    'Pets',
    'Jewelry Accessories',
    'Gifts',
    'Electronics Accessories',
    'Children',
    'Home Garden',
    'Stationery',
    'Pharmacy Convenience',
    'Health Beauty',
    'Leisure Time',
    'Clothing Shoes',
    'Housing',
    'Property Insurance',
    'Utilities',
    'Maintenance Repairs',
    'Rent',
    'Mortgage',
    'Services',
    'Transportation',
    'Long Distance',
    'Taxi',
    'Public Transportation',
    'Business Travel',
    'Vehicle',
    'Vehicle Insurance',
    'Fuel',
    'Vehicle Maintenance',
    'Rental',
    'Rentals',
    'Parking',
    'Leisure',
    'Alcohol Tobacco',
    'Wellness Beauty',
    'Culture Sports Events',
    'Education Development',
    'Life Events',
    'Books Audio Subscriptions',
    'Hobbies',
    'Lottery Gambling',
    'Charity Organization',
    'Healthcare Doctor',
    'Sports Fitness',
    'TV Streaming',
    'Holidays Travel Hotels',
    'Multimedia Computers',
    'Internet',
    'Software Applications Games',
    'Postal Services',
    'Phone Mobile Phone',
    'Financial Expenses',
    'Family Allowances',
    'Fines',
    'Insurances',
    'Warning',
    'Charges Fees',
    'Loan Interest',
    'Taxes',
    'Investments',
    'Real Estate',
    'Collections',
    'Savings',
    'Financial Investments'
    'Vehicles Personal Property',
    'Income',
    'Family Allowances Income',
    'Gifts Income',
    'Checks Discounts',
    'Subsidies Donations',
    'Interests Dividends',
    'Lottery Gambling Income',
    'Loan Rental',
    'Refunds Tax Purchases',
    'Rental Income',
    'Salaries Invoices',
    'Sales',
    'Other',
    'Missing',
    'Unknown Expense',
    'Unknown Income'
);

CREATE TABLE  IF NOT EXISTS subcategory (id SERIAL primary key,subcategory_name subcategory_enum);
INSERT INTO subcategory ( subcategory_name)
SELECT 'Fast Food'
WHERE NOT EXISTS (
    SELECT 1 FROM subcategory
    WHERE subcategory_name='Fast Food'
    );
INSERT INTO subcategory ( subcategory_name)
SELECT 'Salaries Invoices'
    WHERE NOT EXISTS (
    SELECT 1 FROM subcategory
    WHERE subcategory_name='Salaries Invoices'
    );
INSERT INTO subcategory ( subcategory_name)
SELECT 'Lottery Gambling Income'
    WHERE NOT EXISTS (
    SELECT 1 FROM subcategory
    WHERE subcategory_name='Lottery Gambling Income'
);