# Insurance Domain

## 

# Insurance REST API


# General info

## Database

![database](https://i.ibb.co/r0LMQdT/image.png =800px)

## General endpoints

* /clients - insurance clients
* /objects - insurance objects for policy
* /types - types for objects
* /options - insurance options for object
* /policies - insurance policies for client

## Entities JSON fields for POST/PUT body

* client: "name", "birth".
* object: "name", "price".
* type: "name".
* option: "name".
* policy: "name".


## How to use

* create types to be used to create insurance objects (POST: /types) 
#### (ex: name = "car", name = "house")
* create insurance options to be used for insurance objects (POST: /options)
#### (ex: name = "collision coverage", name = "comprehensive coverage")
* create client (POST: /clients) 
#### (ex: name = "Antony Oner", birth = "1996")
* create policy for this client (POST: clients/1/policy)
#### (ex: name = "All Insurance")
* create insurance objects for this policy (POST: policies/1/objects)
#### (ex: name = "Red car", name = "Wooden house")
* for each object set its type from /types (POST: objects/1/types/1)
#### to add type 1 for object 1
* for each object set its options from /options 
(POST: objects/1/options/1), (POST: objects/1/options/2)
#### to add option 1 and 2 for object 1



# Simple requests


## GET
* /clients: retrieve all clients
* /objects: retrieve all insurance objects
* /types: retrie all object types
* /options: retrieve all insurance options
* /policies: retrieve all policies

## GET with ID
* /clients/{id}: retrieve client with id
* /objects/{id}: retrieve object with id
* /types/{id}: retrieve type with id
* /options/{id}: retrieve insurance option with id
* /policies/{id}: retrieve policy with id

## POST
* /clients: add new client 
* /objects: add new objects
* /types: add new object types
* /options: add new insurance options
* /policies: add new policies

## PUT
* /clients/{id}: update client with id
* /objects/{id}: update object with id
* /types/{id}: update type with id
* /options/{id}: update insurance option with id
* /policies/{id}: update policy with id

## DELETE
* /clients/{id}: delete client with id
* /objects/{id}: delete object with id
* /types/{id}: delete type with id
* /options/{id}: delete insurance option with id
* /policies/{id}: delete policy with id



# Relationships requests


## Client

### GET relations for Client
* /clients/{clientId}/policies: retrieve policy list for client with

### POST relations for Client
* /clients/{clientId}/policies: create new policy for the client
* /clients/{clientId}/policies/{policyId}: add an existing policy to the client 


## Object

### GET relations for Object
* /objects/{objectId}/options: get a list of insurance options for object
* /objects/{objectId}/type: get object type
* /objects/{objectId}/policy: get the policy in which the object is located

### POST relations for Object
* /objects/{objectId}/options: create a new insurance option for object
* /objects/{objectId}/type: create a new type for object
* /objects/{objectId}/policy: create a new policy for object
* /objects/{objectId}/options/{optionId}: add an existing insurance option to the object
* /objects/{objectId}/types/{typeId}: add an existing type to the object
* /objects/{objectId}/policies/{policyId}: add an existing policy to the object

### PUT relations for Object
* /objects/{objectId}/types/{typeId}: change object type

### DELETE relations for Object
* /objects/{objectId}/options/{optionId}


## Type

### GET relations for Type
* /types/{typeId}/objects: get a list of objects that have this type

### POST relations for Type
* /types/{typeId}/objects: create a new object with type
* /types/{typeId}/objects/{objectId}: add an existing object to the type group


## Option

### GET relations for Option
* /options/{optionId}/objects: get a list of objects that have this option

### POST relations for Option
* /options/{optionId}/objects: create a new object with option
* /options/{optionId}/objects/{objectId}: add an existing object to the option group


## Policy

### GET relations for Policy
* /policies/{policyId}/objects: get a list of objects that are protected by the policy
* /policies/{policyId}/client: get a client for whom a policy has been created

### POST relations for Policy
* /policies/{policyId}/objects: create a new object for policy
* /policies/{policyId}/client: create a new client for policy
* /policies/{policyId}/objects: add an existing object to the policy
* /policies/{policyId}/client: add an existing client to the policy

### PUT relations for Policy
* /policies/{policyId}/clients/{clientId}: change policy client




