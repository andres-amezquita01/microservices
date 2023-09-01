variable "region" {
    default = "us-east-1"
}
variable "microservices_bucket" {
  default = "10th-microservices-project-terraform-state"
}
variable "dynamodb_table" {
  default = "production_terraform_state_locking"
}
variable "environment" {
  default = "microservices-production"
}

# ----------- VPC ----------
variable "vpc_block" {
  default = "10.0.0.0/16"
}
variable "internet_destination_block" {
  default = "0.0.0.0/0"
}
variable "public_subnet_cidrs" {
 type        = list(string)
 description = "Public Subnet CIDR values"
 default     = ["10.0.1.0/24", "10.0.2.0/24"]
}
variable "availability_zones" {
 type        = list(string)
 description = "Availability Zones list"
 default     = ["us-east-1a", "us-east-1b"]
}