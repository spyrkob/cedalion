package ci_jobs

class MvnBuilder {

    String jobName
    String repoName
    String repoUrl
    String schedule = 'H/10 * * * *'
    String mavenGoals = '' // defaults to clean install in hera

    def build(factory) {
        if (jobName == null) {
            jobName = 'ci-' + repoName
        }
        if (repoUrl == null) {
            repoUrl = 'https://github.com/jboss-set/' + repoName
        }
        factory.with {
            pipelineJob(jobName) {

                definition {
                    cps {
                    script(readFileFromWorkspace('pipelines/mvn-pipeline'))
                    sandbox()
                    }
                }
                logRotator {
                    daysToKeep(30)
                    numToKeep(10)
                    artifactDaysToKeep(60)
                    artifactNumToKeep(5)
                }
                triggers {
                    scm (schedule)
                    cron('@daily')
                }
                parameters {
                    stringParam {
                        name ("GIT_REPOSITORY_URL")
                        defaultValue(repoUrl)
                    }
                    stringParam {
                        name ("GIT_REPOSITORY_BRANCH")
                        defaultValue('master')
                    }
                    stringParam {
                        name ("MAVEN_HOME")
                        defaultValue("/opt/apache/maven")
                    }
                    stringParam {
                        name ("JAVA_HOME")
                        defaultValue("/opt/oracle/java")
                    }
                    stringParam {
                        name ("MAVEN_SETTINGS_XML")
                        defaultValue('/opt/tools/settings.xml')
                    }
                    stringParam {
                        name ("MAVEN_OPTS")
                        defaultValue("-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2  -Dnorpm")
                    }
                    stringParam {
                        name ("MAVEN_GOALS")
                        defaultValue(mavenGoals)
                    }
                }
            }
        }
    }
}
