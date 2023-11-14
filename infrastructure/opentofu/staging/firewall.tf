resource "google_compute_firewall" "allow-ssh" {
  name = "allow-ssh"
  network = google_compute_network.main.name
  allow {
    protocol = "tcp"
    ports = ["8090", "3042", "5432"]
  }

  source_ranges = ["0.0.0.0/0"]
}
