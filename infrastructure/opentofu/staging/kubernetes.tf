resource "google_container_cluster" "primary" {
  name = "primary"
  location = "us-east1-b"
  remove_default_node_pool = true
  initial_node_count = 1
  network = google_compute_network.main.self_link
  subnetwork = google_compute_subnetwork.private.self_link
  networking_mode = "VPC_NATIVE"

  addons_config {
    http_load_balancing {
      disabled = true
    }  
    horizontal_pod_autoscaling {
      disabled = false
    }
  }

  release_channel {
    channel = "REGULAR"
  }
  # workload_identity_config { // I don't understand this shit exactly :D
  #   workload_pool = ""
  # }
  ip_allocation_policy {
    cluster_secondary_range_name = "k8s-pod-range"
    services_secondary_range_name = "k8s-service-range"
  }
  
  private_cluster_config {
    enable_private_nodes = true
    enable_private_endpoint = false
    master_ipv4_cidr_block = "172.16.0.0/28"
  }
  deletion_protection=false
}
