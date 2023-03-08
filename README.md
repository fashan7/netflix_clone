Movie Database Application (NETFLIX Clone)

This is a movie database application that allows users to search and rate movies. The application is built using a microservices architecture, with separate services for front-end development, back-end development, database modeling, API management, and application deployment.

Features

The main features of the application are:

User authentication
Welcome page with last seen films, new movies, and recommendations
Play film (mock)
Rate film
Compute stats (10 most popular movies, 10 most viewed movies)
Technologies Used

The application is built using the following technologies:

Front-end Development: React.js
Back-end Development: Node.js, Express.js
Database Modeling: PostgresQL, MongoDB, Neo4j
API Management: Kong API Gateway
Application Deployment: Docker, Kubernetes
Architecture Overview

The application architecture is based on microservices, with each service handling a specific set of functions:

Front-end Development: Handles the user interface and user authentication.
Back-end Development: Provides REST APIs for movie search, rating, and stats computation.
Database Modeling: Creates and manages the relational and document-based databases.
API Management: Handles the routing and authentication of requests to the appropriate services.
Application Deployment: Deploys the application on a Kubernetes cluster.
Installation Guide

To install and run the application locally, follow these steps:

Clone the repository: git clone https://github.com/fashan7/netflix_clone.git
Install dependencies: npm install
Start the application: npm start
Access the application at http://localhost:3000
API Documentation

The application has the following APIs:

GET /movies - Returns a list of movies.
GET /movies/:id - Returns details of a specific movie.
POST /movies/:id/rating - Adds a rating and comment for a specific movie.
GET /stats/popular - Returns the 10 most popular movies.
GET /stats/viewed - Returns the 10 most viewed movies.
Production Guide

For instructions on how to deploy the application on a production environment, see the production guide here.

Project Demo

You can access a demo of the project here.

Future Improvements

Some potential improvements for the application are:

Adding support for graph-based databases for viewing graphs and recommendations.
Implementing a recommendation engine to suggest movies based on user ratings.
Improving the user interface and adding more features such as movie trailers and search filters.
