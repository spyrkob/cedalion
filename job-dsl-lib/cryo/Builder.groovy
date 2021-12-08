package eap7

import util.Constants

class Builder {

    String jobName
    String branch
    String schedule = Constants.DEFAULT_SCHEDULE
    String mavenSettingsXml = '/opt/tools/settings.xml'
    String harmoniaScript = 'eap-job/olympus.sh'

    def buildAndTest(factory) {
        if (jobName == null) {
            jobName = 'cryo-' + branch
        }

        repoBuild(factory)
        build(factory)
        test(factory)
    }

    def repoBuild(factory) {
        factory.with {
            pipelineJob(jobName + '-repository-build') {

                definition {
                    cps {
                    script(readFileFromWorkspace('pipelines/cryo-pipeline'))
                    sandbox()
                    }
                }
                logRotator {
                    daysToKeep(30)
                    numToKeep(10)
                    artifactDaysToKeep(60)
                    artifactNumToKeep(5)
                }
                properties {
                    disableConcurrentBuilds {
                        abortPrevious(false)
                    }
                }
                triggers {
                    scm (schedule)
                }
                parameters {
                    stringParam {
                        name ("GIT_REPOSITORY_URL")
                        defaultValue('git@github.com:jbossas/jboss-eap7.git')
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
                        name ("MAVEN_OPTS")
                        defaultValue("-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2")
                    }
                    stringParam {
                        name ("INCLUDE_LIST")
                        defaultValue('')
                        description('Comma separated list of PRs IDs( int) to include. If present this will be initial set to vet against rules.')
                    }
                    stringParam {
                        name ("EXCLUDE_LIST")
                        defaultValue('')
                        description('Comma separated list of PRs IDs( int) to exclude')
                    }
                    stringParam {
                        name ("SUFFIX")
                        defaultValue('.future')
                        description('Suffix to use with branch name.')
                    }
                    booleanParam {
                        name ("DRY_RUN")
                        defaultValue(true)
                        description('Determine if run is a dry run. If not, CRYO will attempt to push new branch. Defaults to TRUE, resulting archive can be pulled and fiddled locally! WARNING: You most likely should not change this!')
                    }
                    booleanParam {
                        name ("FLIP")
                        defaultValue(true)
                        defaultValue('Invert PRs. By default GitHub/Aphrodite return newest as first - bigger ID is first.')
                    }
                    booleanParam {
                        name ("CHECK_STATE")
                        defaultValue(false)
                        defaultValue('Switch on testing state of PR. By default CRYO will ignore PR metadata. If set to true, it will only use PRs that have all ACKs set(Has all acks label, usually set by PR processor).')
                    }
                    booleanParam {
                        name ("FAST_LOGGING")
                        defaultValue(false)
                        defaultValue('If turned on, CRYO wont control logging and push timestamp into long running commands.')
                    }
                }
            }
        }
    }

    def build(factory) {
        factory.with {
            pipelineJob(jobName + '-build') {

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/cryo-pipeline'))
                        sandbox()
                    }
                }
                logRotator {
                    daysToKeep(30)
                    numToKeep(10)
                    artifactDaysToKeep(60)
                    artifactNumToKeep(5)
                }
                properties {
                    disableConcurrentBuilds {
                        abortPrevious(false)
                    }
                }
                parameters {
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
                        script(readFileFromWorkspace('pipelines/cryo-pipeline'))
                        sandbox()
                    }
                }
                logRotator {
                    daysToKeep(30)
                    numToKeep(10)
                    artifactDaysToKeep(60)
                    artifactNumToKeep(5)
                }
                properties {
                    disableConcurrentBuilds {
                        abortPrevious(false)
                    }
                }
                parameters {
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
                        name ("MAVEN_OPTS")
                        defaultValue("-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2")
                    }
                }
            }
        }
    }
}
