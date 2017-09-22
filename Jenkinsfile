pipeline {
    agent any

    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'

        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
        SECRET_TEXT_ID = credentials('SECRET_TEXT_ID');
    }

    stages {
        stage('Build') {

            environment {
                DISABLE_BUILD = 'true_build'
                DB_BUILD    = 'sqlite_build'
            }

            steps {
                sh './gradlew build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew check'
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

                sh 'printenv AWS_ACCESS_KEY_ID'
                sh 'printenv SECRET_TEXT_ID'
            }
        }
    }

    post {
        always {
            echo 'This will always run'
            archive 'build/libs/**/*.jar'
            deleteDir()
        }
        success {
            echo 'This will run only if successful'
            mail to: 'yxwang@thoughtworks.com',
                                     subject: "Success Pipeline: ${currentBuild.fullDisplayName}",
                                     body: "Everything is right with ${env.DB_ENGINE}"
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