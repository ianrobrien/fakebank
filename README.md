# fakebank

[![Continuous Integration](https://github.com/ianrobrien/fakebank/actions/workflows/ci.yaml/badge.svg?branch=main)](https://github.com/ianrobrien/fakebank/actions/workflows/ci.yaml)
[![CodeQL](https://github.com/ianrobrien/fakebank/actions/workflows/codeql.yml/badge.svg)](https://github.com/ianrobrien/fakebank/actions/workflows/codeql.yml)

[![Coverage](.github/badges/jacoco.svg)](https://www.ianrobrien.dev/fakebank/coverage)
[![Coverage](.github/badges/branches.svg)](https://www.ianrobrien.dev/fakebank/coverage)

[![fakebank-api](https://img.shields.io/badge/fakebank--api-4c8eca)](https://ianrobrien.dev/fakebank/api)

## Running the application

```shell
docker compose build
docker composer up fakebank
```

_Note: this step can sometimes hang. Stop and restart the build if it takes
more than 2 minutes_

## Postman collection

Import the [Postman collection](docs/postman/fakebank.postman_collection.json) to
make local API calls.

## API Calls

To see a complete specification, either check the [API summary](https://www.ianrobrien.dev/fakebank/api)
or the [yaml spec](fakebank-api/src/main/resources/fakebank.api.yaml).

These calls are included in the Postman collection for your convenience.

### /accounts/{accountId}/balance

Fetches an account balance for the given account id.

### /accounts/{accountId}/details

Fetches the account details for the given account id.

### /payments

Initiates a payment between two accounts.

## Things to do for production release

- Add business logic to the request interceptors
- Allow client to set trace-id or set it automatically and return it in the response
- Add create/delete account functions
- Replace mock repository with a database/api
