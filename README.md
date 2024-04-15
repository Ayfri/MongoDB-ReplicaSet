# MongoDB-ReplicaSet

A TP I have to do, must use MongoDB ReplicaSet on docker, can use any language to add CRUD operations.

## How to install

1. Clone the repository
2. Run `docker-compose up`
3. Install Java 17 or higher

## Scripts

- Run `gradle :run --args=create` for creating the `users` collection and adding fake data
- Run `gradle :run --args=delete` for deleting the first user with more than 70 years old
- Run `gradle :run --args=search` for searching all users with more than 30 years old
- Run `gradle :run --args=update` for adding all users 5 years to their age

## Crud operations with Mongosh

### Connecting to the database

First, here's how to connect to the database :

```bash
docker exec -it mongo1 bash
```

Replace `mongo1` with `mongo2` or `mongo3` if you want to connect to the other nodes.

Then, you can connect to the database with the following command :
``bash
mongosh --host localhost --port 27017

```

Then, here's how to do CRUD operations :

### Creating
```javascript
db.users.insertOne({name: "John", age: 25})
```

### Reading

```javascript
db.users.find({age: {$gt: 30}})
```

### Updating

```javascript
db.users.updateOne({name: "John"}, {$set: {age: 30}})
```

Deleting :

```javascript
db.users.deleteOne({name: "John"})
```

## Differences between script and CLI CRUD operations

The main difference between the script and the CLI CRUD operations is that the script is more automated and can be run in one command, while
the CLI operations require you to type each command manually.
You can also define dynamic parameters in the script, while you can't do that in the CLI.
The code also let us see the performance of the operations, while the CLI doesn't.

## Difficulties encountered

The main difficulty I encountered was to set up the Replica Set with Docker Compose. I had to search for examples in GitHub, the main issue
was to initialize the replica set with the right configuration.
Using environment variables as my first approach was not working, I ended up removing them and use a bash script to initialize the
Replica Set in an aside container.

Another issue I encountered was to connect to the database with code. I first created the entire code in JavaScript, but never succeeded to
connect to the database. I had to rewrite everything from scratch in another language, I chose Kotlin.
