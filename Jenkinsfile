
pipeline {
    agent any
     stages {
        stage('Run unit test/coverage') {
            when { anyOf { branch 'main'; branch 'dev' } }
            agent {
                label "docker"
            }
            steps {
                sh 'sbt compile coverage test'
                sh 'sbt coverageReport'
            }
        }
        stage('Run sonarqube') {
            when { anyOf { branch 'main'; branch 'dev' } }
            agent {
                label "docker"
            }
            steps {
                withSonarQubeEnv("sonarqube-9.9.1"){
                    sh "/home/ec2-user/install_scanner/sonar-scanner-4.8.0.2856-linux/bin/sonar-scanner"
                }
            }
        }
        stage('Run unit test/coverage frontend') {
            when { anyOf { branch 'main'; branch 'dev' } }
            agent {
                label "docker"
            }
            steps {
                sh 'npm run test'
            }
        }
        stage("Quality Gate") {
            when { anyOf { branch 'main'; branch 'dev' } }
            steps{
                timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Docker login') {
            when { anyOf { branch 'dev' } }
            agent {
                label "docker"
            }
            steps {
                sh 'gcp artifact-registry get-login-password --region | docker login --username GCP --password-stdin 792511625564.dkr.gcp.us.com'
            }
        }
        stage('Get artifac registry url and hash commit'){
            when { anyOf { branch 'dev' } }
            agent {
                label "tofu"
            }
            steps{
                dir("tofu/global"){
                    sh 'tofu init'
                     script {
                        ARTIFACT_URL = sh (
                          script: "tofu output --raw artifact_repository_url",
                          returnStdout: true
                        )
                      }
                     script {
                        HASH_COMMIT = sh (
                          script: "git log -1 --pretty=format:'%H'",
                          returnStdout: true
                        )
                      }
                    sh "echo ${ARTIFACT_URL}"
                    sh "echo ${HASH_COMMIT}"
                }
            }
        }
        stage('Build image'){
            when { anyOf { branch 'dev' } }
            agent {
                label "docker"
            }
            steps{
                sh "docker build -t  ${ARTIFACT_URL} . --no-cache"
            }
        }
        stage('Tag image'){
            when { anyOf { branch 'dev' } }
            agent {
                label "docker"
            }
            steps{
                sh """
                   docker tag  ${ARTIFACT_URL}:latest ${ARTIFACT_URL}:${HASH_COMMIT}
                """
            }
        }
        stage('Push image'){
            when { anyOf { branch 'dev' } }
            agent {
                label "docker"
            }
            steps{
                sh """
                    docker push ${ARTIFACT_URL}:latest
                    docker push ${ARTIFACT_URL}:${HASH_COMMIT}
                """
            }
        }
        stage('Deploy to staging'){
            when { anyOf { branch 'dev' } }
            agent {
                label "tofu"
            }
            steps{
               dir("opentofu/staging/"){
                    sh """
                    tofu init
                    tofu apply -var='image_tag=latest' -auto-approve
                    """
                    script {
                        PRODUCTION_DNS = sh (
                          script: "tofu output --raw production_lb",
                          returnStdout: true
                        )
                    }
                    sh "echo ${PRODUCTION_DNS}"
               }
            }
        }

    }
    post{
        always {
            node('docker'){
                sh 'docker image prune -af'
                sh 'docker images'
                sh 'docker logout'
            }
        }
    }
}
