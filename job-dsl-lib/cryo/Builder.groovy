package eap7

import util.JobSharedUtils

class Builder {

    String jobName
    String branch
    String mavenSettingsXml = '/opt/tools/settings.xml'
    String harmoniaScript = 'eap-job/olympus.sh'

    def buildAndTest(factory) {
        if (jobName == null) {
            jobName = 'cryo-' + branch
        }

        repoBuild(factory)
        buildChain(factory, jobName + '-build')
        buildChain(factory, jobName + '-testsuite')
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
                JobSharedUtils.defaultBuildDiscarder(delegate)
                properties {
                    JobSharedUtils.doDisableConcurrentBuilds(delegate)
                }
                triggers {
                    scm(JobSharedUtils.DEFAULT_SCHEDULE)
                }
                parameters {
                    JobSharedUtils.gitParameters(delegate, 'git@github.com:jbossas/jboss-eap7.git', branch)
                    JobSharedUtils.mavenParameters(params: params, mavenSettingsXml: mavenSettingsXml)
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

    def buildChain(factory, _jobName) {
        factory.with {
            pipelineJob(_jobName) {

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/cryo-pipeline'))
                        sandbox()
                    }
                }
                JobSharedUtils.defaultBuildDiscarder(delegate)
                properties {
                    JobSharedUtils.doDisableConcurrentBuilds(delegate)
                }
                parameters {
                    JobSharedUtils.mavenParameters(params: params, mavenSettingsXml: mavenSettingsXml)
                }
            }
        }
    }
}
