resource "digitalocean_droplet" "fakebank_droplet" {
  name      = "fakebank-server"
  size      = "s-1vcpu-512mb-10gb"
  image     = "ubuntu-22-04-x64"
  region    = "ams3"
}

resource "digitalocean_droplet" "fakebank_database" {
  name      = "fakebank-database"
  size      = "s-1vcpu-512mb-10gb"
  image     = "ubuntu-22-04-x64"
  region    = "ams3"
}
