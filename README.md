# FakeBank

[![Continuous Integration](https://github.com/ianrobrien/fakebank/actions/workflows/ci.yaml/badge.svg?branch=main)](https://github.com/ianrobrien/fakebank/actions/workflows/ci.yaml)
[![CodeQL](https://github.com/ianrobrien/fakebank/actions/workflows/codeql.yml/badge.svg)](https://github.com/ianrobrien/fakebank/actions/workflows/codeql.yml)

[![Coverage](.github/badges/jacoco.svg)](https://www.ianrobrien.dev/fakebank/coverage)
[![Coverage](.github/badges/branches.svg)](https://www.ianrobrien.dev/fakebank/coverage)

[![FakeBank-api](https://img.shields.io/badge/fakebank--api-4c8eca)](https://ianrobrien.dev/fakebank/api)

## Overview

FakeBank is a sample banking application built with Spring Boot, Java, and
Maven. It provides a set of RESTful APIs for managing bank accounts and
processing payments.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [License](#license)

## Features

- Manage bank accounts
- Process payments
- RESTful APIs
- Docker support
- Continuous Integration with GitHub Actions
- Code quality checks with CodeQL

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Docker

### Installation

Clone the repository:

```shell
git clone https://github.com/ianrobrien/fakebank.git
cd fakebank
```

### Running the Application

Build and run the application using Docker Compose:

```shell
docker compose build
docker compose up fakebank
```

## Configuration

Configuration files are located in the `src/main/resources` directory:

- `application.properties`
- `application-local.properties`
- `application-dev.properties`

## API Documentation

To see a complete specification, check the [API summary](https://www.ianrobrien.dev/fakebank/api)
or the [YAML spec](docs/api/fakebank.api.yaml).

## Testing

Run the tests using Maven:

```shell
mvn test
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
