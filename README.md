# fakebank

[![Continuous Integration](https://github.com/ianrobrien/fakebank/actions/workflows/ci.yaml/badge.svg?branch=main)](https://github.com/ianrobrien/fakebank/actions/workflows/ci.yaml)
[![Coverage](.github/badges/jacoco.svg)](https://www.obrien.no/fakebank/coverage)
[![Coverage](.github/badges/branches.svg)](https://www.obrien.no/fakebank/coverage)
[![CodeQL](https://github.com/ianrobrien/fakebank/actions/workflows/codeql.yml/badge.svg)](https://github.com/ianrobrien/fakebank/actions/workflows/codeql.yml)
[![API Summary](https://img.shields.io/badge/API_Summary-4c8eca)](https://obrien.no/fakebank/api)

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

To see a complete specification, either check the [API summary](https://www.obrien.no/fakebank/api)
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
