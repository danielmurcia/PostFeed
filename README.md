# PostFeed
Android project assessment submission for benshi.ai by Daniel Murcia

## WORK IN PROGRESS
Still pending:
- Load more posts on scroll (pagination)
- Email action logs
- Load images
- Make UI better looking

## Design pattern and project structure
Implemented a Domain-Driven Design (DDD) where the project is separated in three main architectural layers: Presentation, Domain and Data. With these we can make our code more testable, scalable and maintainable by building the app around the domain layer.

The Presentation layer holds all of the screens and views, that will call their respective viewmodels for their state management.

The Domain layer contains the models used around the app, the usecases that the presentation layer calls to get the needed data from the remote data client.

In the Data layer is represented by an external package (posts_client)that uses Retrofit to make API calls to get all remote data and to send usage logs as emails.

## Folder structure
...
|-- app/src/main/java/com.dnlm.postfeed
|   |-- domain  
|   |   |-- entities
|   |   |-- usecases  
|   |-- presentation  
|   |   |-- <feature>
|   |   |   |-- viewmodels
|   |   |   |-- views   
|   |   |   |-- <feature>Fragment.kt   
|   |-- utils  
|   |-- MainActivity.kt 
|-- posts_client/src/main/java/com.dnlm.posts_client
|   |-- api  
|   |-- models