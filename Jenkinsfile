pipeline {
    agent any

    parameters {
        choice(name: 'ENVIRONMENT', choices: ['dev', 'test', 'stage', 'sandbox'], description: 'Choose environment.')
        choice(name: 'TAG', choices: ['junit', 'paramTest', 'noparamTest', 'sanity', 'second', 'string', 'wordpress', 'word', "Frontend", "login". "ActionTest", "Window"], description: 'Choose tag.')
        choice(name: 'EXTAG', choices: ['','junit', 'paramTest', 'noparamTest', 'sanity', 'second', 'string', 'wordpress', 'word'], description: 'Choose tag.')
    }

    stages {
        stage('checkout') {
            steps {
               git 'https://github.com/testautomation112020/qa.git'
            }
        }
        stage('run') {
            steps {
                sh "mvn clean test -Dgroups=${params.TAG} -DexcludedGroups=${params.EXTAG} -DENVIRONMENT=${params.ENVIRONMENT}"
            }
        }

    }
        post {
            always {
                allure([
                         includeProperties: false,
                         jdk: '',
                         properties: [[key: 'allure.issues.tracker.pattern', value: 'http://tracker.company.com/%s'],
                         [key: 'allure.tests.management.pattern', value: 'http://tms.company.com/%s'],
                         ],
                         reportBuildPolicy: 'ALWAYS',
                         results: [[path: 'qajunit/target/allure-results'], [path: 'qagui/target/allure-results']]
                         ])
            }
        }
}