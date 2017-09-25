node {

    stage('Checkout'){
        checkout scm
    }

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