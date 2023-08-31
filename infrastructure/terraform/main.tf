resource "digitalocean_droplet" "fakebank_droplet" {
  name      = "fakebank-droplet"
  size      = "s-1vcpu-512mb-10gb"
  image     = "ubuntu-22-04-x64"
  region    = "ams3"
}
