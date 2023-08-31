terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "~> 2.0"
    }
  }
}

provider "digitalocean" {
  token = var.digitalocean_api_token
}

data "digitalocean_ssh_key" "id_ed25519_ianrobrien" {
  name = "id_ed25519_ianrobrien"
}