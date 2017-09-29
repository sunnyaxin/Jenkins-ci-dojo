pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}

node('android-node') {
    stage('Checkout'){
        checkout scm
    }

    stage('Build') {
        sh './gradlew build'
    }

    stage('Test') {
        sh './gradlew check'
    }

    stage('Deploy') {
        retry(3) {
            sh './flakey-deploy.sh'
        }

        timeout(time: 3, unit: 'MINUTES') {
            sh 'echo "Yeah~~~"'
        }
    }
}

node('master') {
    stage('nodeTest') {
        sh 'echo "master~~~~~~~~"'
    }
}