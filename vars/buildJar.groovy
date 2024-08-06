#!/usr/bin/env groovy

def call() {
    echo "building the application..."
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t manideepm777/demo-app:jma-2.1 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push manideepm777/demo-app:jma-2.1'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
