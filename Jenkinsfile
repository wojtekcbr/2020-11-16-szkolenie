pipeline {
    agent any

    triggers {
            cron('0 0 * * 0')
            githubPush()
        }

    parameters {
        choice(name: 'ENVIRONMENT', choices: ['dev', 'test', 'stage', 'sandbox'], description: 'Choose environment.')
        choice(name: 'TAG', choices: ['', 'junit', 'paramTest', 'noparamTest', 'sanity', 'second', 'string', 'wordpress', 'word', 'Frontend', 'login', 'ActionTest', 'Window'], description: 'Choose tag.')
        choice(name: 'EXTAG', choices: ['','junit', 'paramTest', 'noparamTest', 'sanity', 'second', 'string', 'wordpress', 'word', 'Frontend', 'login', 'ActionTest', 'Window'], description: 'Choose tag.')
        choice(name: 'CUCUMBER_TAG', choices: ['','@BDD', '@login', '@wordpress','@userpanel'], description: 'Choose cucumber tag.')
        choice(name: 'BROWSER', choices: ['chrome','firefox', 'edge'], description: 'Choose browser type.')
        choice(name: 'MACHINE', choices: ['remote','local'], description: 'Choose machine type.')
        string(name: 'REMOTE_URL', defaultValue: 'http://172.17.80.1:4444/wd/hub', description: 'Remote selenium grid url.')
    }

    stages {
        stage('checkout') {
            steps {
               git 'https://github.com/testautomation112020/qa.git'
            }
        }
        stage('build') {
                    step,
                    ,s

                     2{
                        sh "mvn clean install -DskipTests"
                    }
                }
        stage('run') {
            steps {
                sh "mvn clean test -Dgroups=${params.TAG} -DexcludedGroups=${params.EXTAG} -DENVIRONMENT=${params.ENVIRONMENT} -DBROWSER=${params.BROWSER} -DMACHINE=${params.MACHINE} -DREMOTE_URL=${params.REMOTE_URL} -Dcucumber.filter.tags=\"${params.CUCUMBER_TAG}\""
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
                cucumber buildStatus: 'UNSTABLE', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*cucumber.json', jsonReportDirectory: 'qagui/target/', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
            }
        }
}