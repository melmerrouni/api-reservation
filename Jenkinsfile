node {
    // reference to maven
    // ** NOTE: This 'maven-3.6.1' Maven tool must be configured in the Jenkins Global Configuration.   
    //def mvnHome = tool 'maven-3.6.1'

    // holds reference to docker image
    //def dockerImage
    // ip address of the docker private repository(nexus)
    
    def dockerRepoUrl = "192.168.1.168"
    def dockerImageName = "sinaf/reservation"
    def dockerImageTag = "${dockerRepoUrl}/${dockerImageName}:latest"
    environment {
      DOCKER_HOST = "tcp://192.168.1.168:2375"
    }

    stage('Clone Repo') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/melmerrouni/api-reservation.git'
      // Get the Maven tool.
      // ** NOTE: This 'maven-3.6.1' Maven tool must be configured
      // **       in the global configuration.           
     // mvnHome = tool 'maven-3.6.1'
    }    
  
    stage('Build Project') {
      // build project via maven
      sh "'./mvnw' -Dmaven.test.failure.ignore clean package"
    }
	
	//stage('Publish Tests Results'){
    //  parallel(
    //    publishJunitTestsResultsToJenkins: {
    //      echo "Publish junit Tests Results"
	//	  junit '**/target/surefire-reports/TEST-*.xml'
	//	  archive 'target/*.jar'
    //    },
    //    publishJunitTestsResultsToSonar: {
     //     echo "This is branch b"
    //  })
    //}
		
    stage('Build Docker Image') {
      // build docker image
      //sh "whoami"
      //sh "ls -all /var/run/docker.sock"
      //sh "mv ./target/hello*.jar ./data" 
      sh "./mvnw docker:build"
      
      //dockerImage = docker.build("hello-world-java")
    }
   
    stage('Push Docker Image'){
      
      // deploy docker image to nexus

      echo "Docker Image Tag Name: ${dockerImageTag}"

      sh "docker login -u admin -p Marouane1 ${dockerRepoUrl}"
    //       echo "login"

     // sh "docker tag ${dockerImageName} ${dockerImageTag}"
    //       echo "tag"
      sh "docker push ${dockerImageTag}"
    //       echo "push"
   }

   stage('Run Docker Image'){
      
       // deploy docker image to nexus

     echo "Running: ${dockerImageTag}"

     sh "chmod u+x deploy.sh"
     sh "./deploy api-reservation ${dockerImageTag}"
   //  sh "docker run -d ${dockerImageTag} --name api-reservation"
  }
}
