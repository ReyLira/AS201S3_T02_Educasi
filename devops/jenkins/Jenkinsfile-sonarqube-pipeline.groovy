pipeline {
    agent any

    parameters{
      string(name: 'URL', defaultValue:' ' , description: 'Indique la URL del reporsitorio .git')
      string(name: 'RAMA', defaultValue:' ' , description: 'Indique la rama del reporsitorio .git')
    }
    stages {

        stage("Git Clone"){
            steps {
                script{
                  sh git clone %URL%
                  sh git checkout %RAMA%
                }
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
