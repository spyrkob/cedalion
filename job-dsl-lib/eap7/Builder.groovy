package eap7

import util.Constants

class Builder {

    String jobName
    String branch
    String schedule = Constants.DEFAULT_SCHEDULE
    String parentJobname = ''
    String mavenSettingsXml = '/opt/tools/settings.xml'
    String harmoniaScript = 'eap-job/olympus.sh'
    String gitRepositoryUrl = 'git@github.com:jbossas/jboss-eap7.git'

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
                        defaultValue(gitRepositoryUrl)
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
                    defaultValue(mavenSettingsXml)
                    }
                    stringParam {
                    name ("HARMONIA_SCRIPT")
                    defaultValue(harmoniaScript)
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
                        defaultValue(gitRepositoryUrl)
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
                        defaultValue(mavenSettingsXml)
                    }
                    stringParam {
                        name ("HARMONIA_SCRIPT")
                        defaultValue(harmoniaScript)
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
