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
                        [url: 'https://github.com/vallegrande/AS201S3_T02_Educasi.git', credentialsId: 'vallegrande_github']
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
                    sh 'mvn clean verify sonar:sonar -Dsonar.host.url=http://35.222.30.201:9400 -Dsonar.login=62192f90e2d84e1ac2e3acdfd2a674e30cf68c61'
            }
        }

    }
}
