pipeline {
  agent {
    label "large-cloud"
  }
  triggers {
    pollSCM('* */15 * * *')
  }
  stages {
    stage('Prep') {
      steps {
        echo "Hello"
        sh "/opt/apache-maven-3.6.3/bin/mvn clean install"
      }
    }
  }
  post {
    always {
      junit "**/target/surefire-reports/*.xml"
    }
  }
}
