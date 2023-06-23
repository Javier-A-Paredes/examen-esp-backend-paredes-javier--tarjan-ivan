db = db.getSiblingDB('api-serie-mongo');
db.createUser(
    {
        user: "usr-final-mongo",
        pwd: "pwd-final-mongo",
        roles: [
            {
                role: "readWrite",
                db: "api-serie-mongo"
            }
        ]
    }
);
db.createCollection("series");

db = db.getSiblingDB('api-catalog-mongo');
db.createUser(
    {
        user: "usr-final-mongo",
        pwd: "pwd-final-mongo",
        roles: [
            {
                role: "readWrite",
                db: "api-catalog-mongo"
            }
        ]
    }
);
db.createCollection("movies");
db.createCollection("series");
