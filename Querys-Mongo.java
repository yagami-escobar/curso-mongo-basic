-- DB_EJEMPLOS:
/*
Test1
Test2
Librería
Tienda


*/

-- CONSULTAS BASICAS:
'show dbs' -> Consulta las dbs del cluster.
'use platzi-mongo' -> Usar la db platzi-mongo.
'show collections' -> Consulta las colecciones de la db seleccionada a usar.
'db.myCol.find()' -> Buscar o Consultar los documentos de la Colección. 

/******* CRUD: *******/
-- CREATE
db.createCollection("myCol") -> Crea una colección.
db.myCol.insertOne({name: "Jhordan", age: 25, cel: "955056139"}) → Crea la colección insertando un documento.
db.myCol.insertMany({},{}, ...)


-- READ
db.myCol.find().pretty() -> Listar los documentos de forma pretty.
db.myCol.find().pretty().count() -> Cantidad de documentos.
db.myCol.findOne()


-- UPDATE
db.myCol.updateOne
db.myCol.updateMany()

-- DELETE
db.myCol.deleteOne()
db.myCol.deleteMany()
db.myCol.remove()


/******* FILTROS: *******/
db.inventory.findOne({status: "A"}, {item: 1, status: 1}) -> Me trae un documento con Status="A", pero solo los campos _id, item y status.
db.inventory.findOne({status: "A"}, {_id: 0, item: 1, status: 1}) -> Me trae un documento con Status="A" von Item, Status son el _ID.

/*************************** OPERADORES: ***************************/
-- OPERADORES DE COMPARACIÓN:
$eq -> =
$gt -> >
$gte -> >=
$lt -> <
$lte -> <=
$ne -> !=
$in -> Valores dentro de un arreglo.
$nin -> Valores que no estan dentro de un arreglo.

-- OPERADORES LOGICOS:
$and -> Une querys con un and lógico.
$or -> Une querys con un or lógico.
$not -> Invierte el efecto de una query.
$nor -> Invierte el efecto de un or lógico.

-- OPERADORES POR ELEMENTO:
$exist -> Documentos que cuentan con un campo especifico.
$type -> Para saber de que tipo es el campo de un documento.

-- OPERADORES PARA ARREGLOS:
$all -> Todos los elementos de un arreglo que coincida con una query.
$elemMatch -> Documentos que cumplen la condición del $elemMatch, en 1 de sus elementos.
$size -> Para saber el tamaño de un Arreglo.
$addToSet -> Agrega documentos de un arreglo dependiendo del filtro a usar. 
    Ejm -> db.inventory.updateOne({'_id': ObjectId(json['id_carrera'])}, {'$addToSet': {'cursos': curso}})
$pull → Retira documentos de un arreglo dependiendo del filtro a usar.
    Ejm -> db.inventory.updateOne({'_id': ObjectId(json['id_carrera'])}, {'$pull': {'cursos': {'_id': ObjectId(json['id_curso'])}}})

/* CASO: 1 -> Una query nos devuelve 100 documentos pero solo necesitamos 
             los documentos del número 20 al 30.
*/

db.carreras.find({}).skip(20).limit(10) -> Los primeros 20 y los primeros 10 de 20.



-- Arreglo Inventory
    use test
    db.inventory.insertMany(
    [
        { _id: 1, item: { name: "ab", code: "123" }, qty: 15, tags: [ "A", "B", "C" ] },
        { _id: 2, item: { name: "cd", code: "123" }, qty: 20, tags: [ "B" ] },
        { _id: 3, item: { name: "ij", code: "456" }, qty: 25, tags: [ "A", "B" ] },
        { _id: 4, item: { name: "xy", code: "456" }, qty: 30, tags: [ "B", "A" ] },
        { _id: 5, item: { name: "mn", code: "000" }, qty: 20, tags: [ [ "A", "B" ], "C" ] }
    ])

    // $or
    db.inventory.find({$or: [{qty: {$gt: 25}}, {qty: {$lte: 15}}]})

    // $gte
    db.inventory.find({qty: {$gte: 25}})

    // $size
    db.inventory.find({tags: {$size: 2}})

-- Arreglo Survay
db.survey.insertMany(
[
    { "_id": 1, "results": [ { "product": "abc", "score": 10 }, { "product": "xyz", "score": 5 } ] },
    { "_id": 2, "results": [ { "product": "abc", "score": 8 }, { "product": "xyz", "score": 7 } ] },
    { "_id": 3, "results": [ { "product": "abc", "score": 7 }, { "product": "xyz", "score": 8 } ] }
])

// $elemMatch
db.survey.find(
   { results: { $elemMatch: { product: "xyz", score: { $gte: 8 } } } }
)

db.survey.find(
   { results: { $elemMatch: { product: "xyz" } } }```



