package util

class JobSharedUtils {

    static defaultBuildDiscarder(def job) {
        job.with {
            buildDiscarder {
                strategy {
                    logRotator {
                        numToKeepStr('10')
                        artifactDaysToKeepStr('5')
                        artifactNumToKeepStr('')
                        daysToKeepStr('')
                    }
                }
            }
        }
    }

    static defaultPublishers(def job) {
        job.with {
            archiveJunit('**/target/surefire-reports/*.xml') {
                allowEmptyResults()
                retainLongStdout()
                healthScaleFactor(1.0)
            }
            extendedEmail()
            wsCleanup()
        }
    }

    static defaultSettings(def job) {
        job.with {
            preBuildCleanup()
            timeout {
                absolute(600)
            }
        }
    }

    static defaultMaven(def job) {
        job.with {
            env('MAVEN_HOME', '/opt/apache-maven-3.6.3')
            env('PATH', '$PATH:$MAVEN_HOME/bin')
        }
    }
}
