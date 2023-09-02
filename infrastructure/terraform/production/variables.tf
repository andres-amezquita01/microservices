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

#--------------------ASG-----------------
variable "launch_template_prefix" {
  default = "ecs-template"
}
variable "ecs_ami" {
  default = "ami-0f409bae3775dc8e5"
}
variable "ecs_instance_type" {
  default = "t2.micro"
}
variable "ssh_key_name" {
 default = "ecs_microservices_key" 
}
variable "ecs_ec2_rol" {
  default = "ecsInstanceRole"
}
# variable "ecs_ec2_arn_rol" {
#   default = "arn:aws:iam::792511625564:role/ecs_ec2_Role"
# }