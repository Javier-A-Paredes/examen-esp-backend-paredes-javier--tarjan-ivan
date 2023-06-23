db = db.getSiblingDB('api_serie_mongo');
db.createUser(
    {
        user: "usr-final-mongo",
        pwd: "pwd-final-mongo",
        roles: [
            {
                role: "readWrite",
                db: "api_serie_mongo"
            }
        ]
    }
);
db.createCollection("series");

db = db.getSiblingDB('api_catalog_mongo');
db.createUser(
    {
        user: "usr-final-mongo",
        pwd: "pwd-final-mongo",
        roles: [
            {
                role: "readWrite",
                db: "api_catalog_mongo"
            }
        ]
    }
);
db.createCollection("movies");
db.createCollection("series");
