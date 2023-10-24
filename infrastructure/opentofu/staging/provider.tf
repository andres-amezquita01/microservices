provider "google" {
  project = "nojipiz-corp-infrastructure"
  region = "us-east1"
}

terraform {
  backend "gcs" {
    bucket = "microservices-tf-state-staging" 
    prefix = "terraform/state"
  }
}
