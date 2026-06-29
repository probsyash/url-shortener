# URL Shortener

## About The Project

A RESTful URL shortener that converts long URLs into short codes using Base62 encoding on auto-incremented database IDs. Submit a long URL and get back a short code — visiting the short link redirects you to the original URL with an HTTP 302 response.

## Tech Stack (Built With)

* [Java 21](https://www.oracle.com/java/)
* [Spring Boot 3](https://spring.bootproject.io/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [PostgreSQL](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)
* [Maven](https://maven.apache.org/)

## Getting Started

To get a local copy up and running, follow these steps.

### Prerequisites

* Java 21+
* Docker (for PostgreSQL)

### Installation

1. Clone the repo

```
git clone https://github.com/probsyash/url-shortener.git
```

2. Navigate into the project directory

```
cd url-shortener
```

3. Run the app (Docker Compose spins up PostgreSQL automatically)

```
./mvnw spring-boot:run
```

The server starts at `http://localhost:8080`.

## Usage

### Shorten a URL

Send a POST request with the long URL as plain text in the request body:

```
curl -X POST http://localhost:8080/api/shorten \
  -H "Content-Type: text/plain" \
  -d "https://www.google.com"
```

Response:

```
1
```

### Redirect to Original URL

Send a GET request with the short code:

```
curl -v http://localhost:8080/1
```

Response:

```
HTTP/1.1 302
Location: https://www.google.com
```

Returns `404 Not Found` if the code does not exist.

## How It Works

1. A long URL is submitted via `POST /api/shorten`
2. It is persisted to PostgreSQL and assigned an auto-incremented ID
3. The ID is encoded into a Base62 short code (digits + lowercase + uppercase)
4. The short code is stored alongside the original URL
5. A `GET /{code}` request looks up the code and returns a 302 redirect

## Project Structure

```
src/main/java/com/yash/urlshortener/
├── Url.java                          # JPA entity
├── UrlshortenerApplication.java      # Entry point
├── controller/
│   └── UrlController.java            # REST endpoints
├── repository/
│   └── UrlRepository.java            # Spring Data JPA repository
└── service/
    └── UrlService.java               # Business logic + Base62 encoding
```

## Roadmap

* [x] Base62 URL shortening
* [x] PostgreSQL persistence
* [x] Docker Compose setup
* [ ] Custom short codes
* [ ] Click analytics / visit tracking
* [ ] Expiry / TTL for short links
* [ ] REST API documentation with Swagger

## Contributing

Contributions are welcome!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License.

## Contact

Yash — [github.com/probsyash](https://github.com/probsyash)

Project Link: [https://github.com/probsyash/url-shortener](https://github.com/probsyash/url-shortener)

## Acknowledgments

* [Spring Initializr](https://start.spring.io/)
* [Choose an Open Source License](https://choosealicense.com/)
* [Docker Documentation](https://docs.docker.com/)
* [PostgreSQL Documentation](https://www.postgresql.org/docs/)