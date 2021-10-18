pipeline {
    agent any

    parameters{
      text(name: 'URL', text: [''], description: 'Indique la URL del reporsitorio .git')
      text(name: 'RAMA', text: [''], description: 'Indique la rama')
    }
    stages {

        stage("Git Clone"){
            steps {
                cleanWs()
                    checkout([$class: 'GitSCM', 
                    branches: [[name: parameters.RAMA]], 
                    doGenerateSubmoduleConfigurations: false, 
                    extensions: [[$class: 'CleanCheckout']], 
                    submoduleCfg: [], 
                    userRemoteConfigs: [
                        [url: parameters.URL, credentialsId: 'jenkins_github']
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
