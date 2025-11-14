pipeline {
	agent any
	tools {
		maven 'Maven_3.9.11'
		jdk 'Java21'
	}

	stages {
		stage ('Compile Stage') {
			steps {
				withMaven(maven: 'Maven_3.9.11') {
					bat 'mvn clean compile -Dcheckstyle.skip=true'
				}
			}
		}

		stage ('Testing Stage') {
			steps {
				withMaven(maven : 'MAVEN_3_6_3') {
					bat 'mvn test -Dcheckstyle.skip=true'
				}
			}
		}

		stage ('package Stage') {
			steps {
				withMaven(maven : 'MAVEN_3_6_3') {
					bat 'mvn package -Dcheckstyle.skip=true'
				}
			}
		}
	}
}