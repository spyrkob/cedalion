package eap7

import util.JobSharedUtils

class Builder {

    String jobName
    String branch
    String schedule = JobSharedUtils.DEFAULT_SCHEDULE
    String parentJobname = ''
    String mavenSettingsXml
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
                JobSharedUtils.defaultBuildDiscarder(delegate)
                triggers {
                    scm(schedule)
                }
                parameters {
                    commonParameters(delegate)
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
                JobSharedUtils.defaultBuildDiscarder(delegate)
                parameters {
                    commonParameters(delegate)
                    stringParam {
                        name("PARENT_JOBNAME")
                        defaultValue(parentJobname)
                    }
                }
            }
        }
    }

    def commonParameters(params) {
        JobSharedUtils.gitParameters(params, gitRepositoryUrl, branch)
        JobSharedUtils.mavenParameters(params: params, mavenSettingsXml: mavenSettingsXml)
        params.with {
            stringParam {
                name ("HARMONIA_SCRIPT")
                defaultValue(harmoniaScript)
            }
        }
    }
}
