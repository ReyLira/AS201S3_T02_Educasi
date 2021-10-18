pipeline {
    agent any
    stages {

        stage("Git Clone"){
            steps {
                cleanWs()
                    checkout([$class: 'GitSCM', 
                    branches: [[name: '*/feature/sonarqube']], 
                    doGenerateSubmoduleConfigurations: false, 
                    extensions: [[$class: 'CleanCheckout']], 
                    submoduleCfg: [], 
                    userRemoteConfigs: [
                        [url: 'https://github.com/vallegrande/AS201S3_T02_Educasi.git', credentialsId: 'jenkins_github']
                        ]])
                sh 'pwd' 
                sh 'ls -l'
            } //steps
        }  //stage

        stage('Build Project') {
            agent any
            steps {
                    sh 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            agent any
            steps {
                    sh 'mvn clean verify sonar:sonar -Dsonar.host.url=http://35.222.30.201:9400 -Dsonar.login=e49716356b537e63b2d17480fbf070f84dd623b9'
            }
        }

    }
}