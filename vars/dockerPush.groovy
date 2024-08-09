#!/usr/bin/env groovy

import com.example.Docker

def call(String repository, String imageName) {
    return new Docker(this).dockerPush(repository, imageName)
}
