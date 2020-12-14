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
                sh 'docker-compose down'
                sh 'docker container rm dashboard'
                sh 'docker-compose up'
            }
        }
    }
}
