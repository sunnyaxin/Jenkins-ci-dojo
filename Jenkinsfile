pipeline {
    agent any

    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'

        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
    }

    stages {
        stage('Build') {

            environment {
                DISABLE_BUILD = 'true_build'
                DB_BUILD    = 'sqlite_build'
            }

            steps {
                sh 'echo "Hello World"'
                sh '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
                sh 'printenv DISABLE_AUTH'
                sh 'printenv DB_ENGINE'
                sh 'printenv DISABLE_BUILD'
                sh 'printenv DB_BUILD'
            }
        }

        stage('Deploy') {
            steps {
                sh 'printenv DISABLE_AUTH'
                sh 'printenv DB_ENGINE'

                retry(3) {
                    sh './flakey-deploy.sh'
                }

                timeout(time: 3, unit: 'MINUTES') {
                    sh 'echo "Yeah~~~"'
                }
            }
        }
    }

    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}