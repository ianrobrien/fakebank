terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.40"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region = "eu-north-1"
}

resource "aws_instance" "app_server" {
  ami           = "ami-0d7a109bf30624c99"
  instance_type = "t2.micro"

  tags = {
    Name = "FakebankAppServerInstance"
  }
}
