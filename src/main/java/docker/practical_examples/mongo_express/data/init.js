db = db.getSiblingDB('product-service');
db.createCollection('products');
db.products.insertMany(
    [
        {
        "name":"Ashok",
        "phone":"Redme",
        },
        {
        "name":"Mohit",
        "phone":"Iphone",
        }
    ]
)
