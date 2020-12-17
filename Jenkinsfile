pipeline {

    agent any

    stages {
        stage('Build') {
            steps {
                sh 'docker-compose build'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker container stop dashboard && docker container rm dashboard'
                sh 'docker-compose up -d'
            }
        }
        stage('Cleanup') {
            steps {
                sh 'docker image prune --force'
            }
        }
    }
}
