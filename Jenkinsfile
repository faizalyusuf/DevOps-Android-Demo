pipeline {
  
  agent {label 'macagent'}
  
  stages {
        
    stage('Build'){
      steps{
        sh 'chmod +x gradlew && ./gradlew clean && ./gradlew assembleDebug'
      }
    }    
    /*stage('Code Scan') {
      steps {
        sh './gradlew sonarqube'
      }
    }*/
    stage('Install app to device'){
      steps{
        sh 'adb uninstall com.hitherejoe.animate'
        sh 'adb install app/build/outputs/apk/app-debug.apk'
      }
    }
    stage('Run appium test'){
      steps{
          dir('appium-test'){
            sh './gradlew clean && ./gradlew test'
          }
      }
    }
    stage('Archive artifacts'){
      steps{
        archiveArtifacts artifacts: '**///apk/app-debug.apk', onlyIfSuccessful: true
      }
    }
  }//end stages
  /*post {
    always {
      cleanWs()
    }
  }*/
}//end pipeline

