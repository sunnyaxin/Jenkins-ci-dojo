node {

    stage('Checkout'){
        checkout scm
    }

    stage('Build') {
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

            retry(3) {
                sh './flakey-deploy.sh'
            }

            timeout(time: 3, unit: 'MINUTES') {
                sh 'echo "Yeah~~~"'
            }
        }
    }
}