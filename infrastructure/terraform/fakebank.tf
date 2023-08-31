resource "digitalocean_droplet" "fakebank_droplet" {
  name   = "fakebank-server"
  size   = "s-1vcpu-512mb-10gb"
  image  = "ubuntu-22-04-x64"
  region = "ams3"
  ssh_keys = [
    data.digitalocean_ssh_key.id_ed25519_ianrobrien.id
  ]

  connection {
    host        = self.ipv4_address
    user        = "root"
    type        = "ssh"
    private_key = var.ssh_private_key
    timeout     = "2m"
  }

  provisioner "remote-exec" {
    inline = [
      <<-EOT
  export PATH=$PATH:/usr/bin
  sudo apt update
  sudo apt -y install ca-certificates curl gnupg
  sudo install -m 0755 -d /etc/apt/keyrings
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
  sudo chmod a+r /etc/apt/keyrings/docker.gpg
  echo "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
  rm -rf /var/lib/dpkg/lock-frontend
  sudo apt update
  sudo apt -y install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
  docker run -d -p 8080:8080 -p 443:443 --name fakebank-server ghcr.io/ianrobrien/fakebank:latest
EOT
    ]
  }
}
