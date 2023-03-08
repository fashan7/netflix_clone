
# Movie Database Application

This is a movie database application that allows users to search and rate movies. The application is built using a microservices architecture, with separate services for front-end development, back-end development, database modeling, API management, and application deployment.

## Features
The main features of the application are:
- User authentication
- Welcome page with last seen films, new movies, and recommendations
- Play film (mock)
- Rate film
- Compute stats (10 most popular movies, 10 most viewed movies)


## Technologies Used
The application is built using the following technologies:
- Front-end Development: React.js
- Back-end Development: Node.js, Java
- Database Modeling: PostgresQL, MongoDB
- Cloak
- Application Deployment: Docker


## Architecture Overview
The application architecture is based on microservices, with each service handling a specific set of functions:
- Front-end Development: Handles the user interface and user authentication.
- Back-end Development: Provides REST APIs for movie search, rating, and stats computation.
- Database Modeling: Creates and manages the relational databases.
- API Management: Handles the routing and authentication of requests to the appropriate services.






## Installation Guide

To install and run the application locally, follow these steps:

Clone the repository
```bash
  git clone https://github.com/fashan7/netflix_clone.git
```

Install dependencies
```bash
  npm install
```

Start the application
```bash
  npm start
```

Access the application at 
```bash
  http://localhost:3000
```




## API Documentation

#### Returns a list of movies

```http
  GET /movies 
```

#### Returns details of a specific movie.

```http
  GET /movies/:id
```

#### Adds a rating and comment for a specific movie.

```http
  POST /movies/:id/rating 
```

#### Returns the 10 most popular movies.

```http
  GET /stats/popular
```

#### Returns the 10 most viewed movies.

```http
  GET /stats/viewed 
```


## Future Improvements

Some potential improvements for the application are:
- Adding support for graph-based databases for viewing graphs and recommendations.
- Implementing a recommendation engine to suggest movies based on user ratings.
- Improving the user interface and adding more features such as movie trailers and search filters.
