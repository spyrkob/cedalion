package bugclerk

import util.Constants

class Builder {

    String jobName
    String branch
    String schedule = Constants.DEFAULT_SCHEDULE

    def build(factory) {
        factory.with {
            pipelineJob(jobName) {

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/bugclerk-pipeline'))
                        sandbox()
                    }
                }
                triggers {
                    scm (Constants.DEFAULT_SCHEDULE)
                    cron('@hourly')
                }
                properties {
                    disableConcurrentBuilds {
                        abortPrevious(false)
                    }
                }
                logRotator {
                    daysToKeep(30)
                    numToKeep(10)
                    artifactDaysToKeep(60)
                    artifactNumToKeep(5)
                }
                parameters {
                    stringParam {
                        name ("GIT_REPOSITORY_URL")
                        defaultValue('git@github.com:jboss-set/bug-clerk-report-job.git')
                    }
                    stringParam {
                        name ("GIT_REPOSITORY_BRANCH")
                        defaultValue(branch)
                    }
                    stringParam {
                        name ("JAVA_HOME")
                        defaultValue('/opt/oracle/java')
                    }
                    stringParam {
                        name ("MAVEN_HOME")
                        defaultValue('/opt/apache/maven')
                    }
                    stringParam {
                        name ("MAVEN_GOALS")
                        defaultValue('exec:java -U -Daphrodite.config=/opt/tools/aphrodite.json')
                    }
                    stringParam {
                        name ("MAVEN_SETTINGS_XML")
                        defaultValue('/opt/tools/settings.xml')
                    }
                    stringParam {
                        name ("MAVEN_OPTS")
                        defaultValue('-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2 -Dnorpm')
                    }
                }
            }
        }
    }
}
