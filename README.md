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

## Bruno collection

Import the [Bruno collection](docs/bruno/fakebank/bruno.json) to
make local API calls.

## API Calls

To see a complete specification, either check the [API summary](https://www.ianrobrien.dev/fakebank/api)
or the [yaml spec](fakebank-api/src/main/resources/fakebank.api.yaml).

These calls are included in the Bruno collection for your convenience.
