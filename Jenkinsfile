pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/wojtekcbr/2020-11-16-szkolenie.git'
            }
        }
        stage('run') {
            steps {
                bat "mvn clean test"
            }
        }
    }
}