// Declarative Jenkins pipeline.
pipeline {
    // Lets Jenkins allocate an available agent.
    agent any
    // Defines the build stages.
    stages {
        // Runs the automated test suite.
        stage('Test') {
            // Executes Maven in the agent workspace.
            steps { sh 'mvn clean test' }
        }
    }
    // Runs after the pipeline, whether successful or not.
    post {
        // Always attempts to publish test results.
        always {
            // Collects JUnit XML files from Surefire.
            junit 'target/surefire-reports/*.xml'
        }
    }
}
