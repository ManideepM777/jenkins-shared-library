#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }
    
    def buildDockerImage(String repository, String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t ${repository}:$imageName ."
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'docker', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
        }
    }

    def dockerPush(String repository, String imageName) {
        script.sh "docker push ${repository}:$imageName"
    }
}
