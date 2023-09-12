data "aws_iam_role" "ecs_ec2_rol" {
  name = var.ecs_ec2_rol
}
resource "aws_launch_template" "ecs_lt" {
    name_prefix = var.launch_template_prefix
    image_id = var.ecs_ami
    instance_type = var.ecs_instance_type
    key_name = var.ssh_key_name
    vpc_security_group_ids = [aws_security_group.ecs_ec2_sg.id]

    iam_instance_profile {
        name = data.aws_iam_instance_profile.ecs_ec2_ins_prof.name
    }
    block_device_mappings {
        device_name = "/dev/xvda"
        ebs {
            volume_size = 30
            volume_type = "gp2"
        }
     }

    tag_specifications {
        resource_type = "instance"
        tags = {
            Name = "ecs-instance"
        }
    }
    # user_data = "${file("../scripts/ecs_config.sh")}"
    user_data = filebase64("../scripts/ecs_config.sh")
}

resource "aws_autoscaling_group" "ecs_asg" {
    vpc_zone_identifier = [  aws_subnet.public_subnets[0].id,aws_subnet.public_subnets[1].id]
    desired_capacity    = 1
    max_size            = 3
    min_size            = 1

    launch_template {
        id = aws_launch_template.ecs_lt.id
        version = "$Latest"
    }

    tag {
        key                 = "AmazonECSMicroservices"
        value               = true
        propagate_at_launch = true
    }
}