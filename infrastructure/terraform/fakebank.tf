resource "digitalocean_droplet" "fakebank_droplet" {
  name   = "fakebank-server"
  size   = "s-1vcpu-512mb-10gb"
  image  = "ubuntu-22-04-x64"
  region = "ams3"

  provisioner "remote-exec" {
    inline = [
      "export PATH=$PATH:/usr/bin",
      "sudo apt update",
      "sudo apt install -y docker",
      "docker pull ghcr.io/ianrobrien/fakebank:latest",
      "docker run ghcr.io/ianrobrien/fakebank:latest"
    ]
  }
}
