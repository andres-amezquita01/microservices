terraform {
  backend "s3" {
    bucket = "10th-microservices-project-terraform-state"
    key = "tf-infra/terraform.tfstate"
    region = "us-east-1"
    dynamodb_table = "production_terraform_state_locking"
    encrypt = true  
  }
    required_providers {
        aws = {
        source  = "hashicorp/aws"
        version = "~> 5.0"
        }
    }
}
provider "aws" {
  region = var.region
}
