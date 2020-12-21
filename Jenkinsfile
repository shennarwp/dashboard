pipeline {

    agent any

    stages {
        stage('Build') {
            steps {
                withCredentials([file(credentialsId: 'openweathermap_api', variable: 'openweathermap_api')]) {
                   sh "cp \$openweathermap_api /src/main/resources/META-INF/resources/secret/openweathermap_api"
                }
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
