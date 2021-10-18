pipeline {
    agent any
    
    parameters { 
      input(name: 'URLGIT', input: [''], description: 'Seleccione el entorno a utilizar')
      input(name: 'BRANCHNAME', input: [''], description: 'Seleccione el entorno a utilizar')
      input(name: 'PROJECTKEY', input: [''], description: 'Seleccione el entorno a utilizar')
    }
    
    stages {

        stage("Git Clone"){
            steps {
                   sh 'echo "llego" + params.URLGIT'
                   SH 'git clone + params.URLGIT'
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
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=params.Projectkey -Dsonar.host.url=http://35.222.30.201:9400 -Dsonar.login=e49716356b537e63b2d17480fbf070f84dd623b9'
            }
        }
    }
}
