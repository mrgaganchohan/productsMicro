pipeline {
    agent any

    stages {

        stage ('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage ('Testing Stage') {
            steps {
                sh 'mvn test'
            }
        }

        stage ('Packaging Stage') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage ('Deployment Stage') {
            steps {
                 sh '/var/lib/jenkins/scripts/05_deploy_products_micro.sh'

            }
        }

    }
}