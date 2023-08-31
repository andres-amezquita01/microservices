variable "region" {
    default = "us-east-1"
}
variable "microservices_bucket" {
  default = "10th-microservices-project-terraform-state"
}
variable "dynamodb_table" {
  default = "production_terraform_state_locking"
}