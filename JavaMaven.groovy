def call(body) {
def config = [:]
 body.resolveStrategy = Closure.DELEGATE_FIRST
 body.delegate = config
 body()
def buildNode = config.buildNode ?:'master'
def mavenGoal = config.mavenGoal ?:'test'
//properties([pipelineTriggers([pollSCM('* * * * *')])])
pipeline{
  label "${buildNode}"
    tools{
        jdk 'JDK_8'
        maven 'MAVEN'
    }
    stages{
	    /*
        stage('SCM Checkout'){
            steps{
               git credentialsId: 'for_github', url: 'https://github.com/himanshuchandan/demoproj.git'
            }
        }
        */
	 /*  
        stage('sonar analysis') {
                steps {
			withSonarQubeEnv('sonar_server'){
                bat 'mvn clean package sonar:sonar -Dsonar.host.url=http://35.223.120.92:9000 -Dsonar.login=0fea14dbd7e239e4adff7794217851b3ac286ea2'
		//sh 'mvn clean package sonar:sonar -Dsonar.host.url=http://35.223.120.92:9000 -Dsonar.login=0fea14dbd7e239e4adff7794217851b3ac286ea2' 
			}
            }
        }   
	*/
	    /*
	     stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
   	*/
	    
	      stage('Package'){
            steps{
                bat 'mvn clean package'
              //sh 'mvn package'
            }
        }    
        
	/*    
        stage('Test') {
            steps {
                 bat 'mvn  ${mavenGoal}'
            }
        }
	*/
      	//stage('nexus IQ'){
	//    steps{
	//    	nexusPolicyEvaluation advancedProperties: '', enableDebugLogging: false, failBuildOnNetworkError: false, iqApplication: selectedApplication(''), iqStage: '', jobCredentialsId: ''
	//    }
	//}
      /*
        stage('Store jar in nexus repo'){
            steps{
               nexusArtifactUploader artifacts: [
                   [
                       artifactId: 'calculator', 
                       classifier: '',
                       file: 'C:/windows/system32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/JavaPipeline/target/calculator-0.0.1-SNAPSHOT.jar', 
                       type: 'jar'
                    ]
                ],
                credentialsId: 'nexus', 
                groupId: 'org.springframework.boot', 
                nexusUrl: '35.223.120.92:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: 'demoapp/', 
                version: '0.0.1-SNAPSHOT'
            }
        }
        */
   }
	    
	//post {
    	//  success {
	//	bat 'curl -D- -u himanshu.kool.hk@gmail.com:oLVSlrDZ0Auf1k15odof6508 -X POST --data {"fields":{"project":{"key":"FIT"},"summary":"created for j","description":"Created for j","issuetype":{"name":"Task"}}} -H "Content-Type: application/json" https://himanshu98.atlassian.net/rest/api/2/issue/'
  	//     }
        //}
}
}
