package eap7

class Builder {

    String jobName
    String branch
    String schedule = 'H/10 * * * *'
    String parentJobname = ''

    def buildAndTest(factory) {
        build(factory)
        test(factory)
    }

    def build(factory) {

        if (jobName == null) {
            jobName = 'eap-' + branch
        }

        factory.with {
            pipelineJob(jobName + '-build') {

                definition {
                    cps {
                    script(readFileFromWorkspace('pipelines/wildfly-pipeline'))
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
                }
                parameters {
                    stringParam {
                    name ("GIT_REPOSITORY_URL")
                    defaultValue("git@github.com:jbossas/jboss-eap7.git")
                    }
                    stringParam {
                    name ("GIT_REPOSITORY_BRANCH")
                    defaultValue(branch)
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
                    defaultValue("/opt/tools/settings.xml")
                    }
                    stringParam {
                    name ("HARMONIA_SCRIPT")
                    defaultValue("eap-job/olympus.sh")
                    }
                    stringParam {
                      name ("MAVEN_OPTS")
                      defaultValue("-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2")
                    }
                }
            }
        }
    }

    def test(factory) {
        factory.with {
            pipelineJob(jobName + '-testsuite') {

                definition {
                    cps {
                    script(readFileFromWorkspace('pipelines/wildfly-pipeline'))
                    sandbox()
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
                    defaultValue("git@github.com:jbossas/jboss-eap7.git")
                    }
                    stringParam {
                    name ("GIT_REPOSITORY_BRANCH")
                    defaultValue(branch)
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
                    defaultValue("/opt/tools/settings.xml")
                    }
                    stringParam {
                    name ("HARMONIA_SCRIPT")
                    defaultValue("eap-job/olympus.sh")
                    }
                    stringParam {
                      name ("MAVEN_OPTS")
                      defaultValue("-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2")
                    }
                    stringParam {
                        name("PARENT_JOBNAME")
                        defaultValue(parentJobname)
                    }
                }
            }
        }
    }
}
