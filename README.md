# Mine Sweeper

# API

I choose to use spring-boot to focus more on the logic f the problems instead of the api structure and configuration itself.

Also used as return data Lists instead o Matrix, as lists has the advantages of streams  and consumers in java 8

For the dig and flag actions i decided to return only the affected cell to increase performance when playing the game

Used MongoDB as it would be faster en easier to retrive whole game structure (used mongo insted of Dynamo for local testing purposes)

Unit tests made with JUnit and Mockito

API is hosted on a EC2 instance on AWS

API Url: http://ec2-54-187-86-211.us-west-2.compute.amazonaws.com:8080

Endpoints:

POST game/start
PUT game/pause/{gameId}
PUT game/resume/{gameId}
GET game/{gameId}
GET game/user/{userId}
PUT game/dig/
PUT game/flag
PUT game/end


# Library
Node and Typescript

# UI

Built with Angular 10 

UI Url: http://d2e6mnnm8mwwvt.cloudfront.net/
