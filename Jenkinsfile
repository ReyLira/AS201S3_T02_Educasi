pipeline {
    agent any
    stages {
        stage('SonarQube Analysis') {
            agent any
            steps {
                echo 'Analysing'
                    sh '''mvn clean verify sonar:sonar 
                    -Dsonar.host.url=http://localhost:9000 
                    -Dsonar.login=62192f90e2d84e1ac2e3acdfd2a674e30cf68c61
                    -Dsonar.projectKey=AS201S3_T02_Educasi'''
            }
        }

    }
}
