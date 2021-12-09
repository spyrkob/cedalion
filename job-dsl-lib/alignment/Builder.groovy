package alignment

import util.JobSharedUtils

class Builder {
    String branch
    String jobName
    String jobSuffix
    String repoUrl

    void build(factory) {
        if (jobName == null) {
            jobName = 'component-aligment-' + jobSuffix
        }
        factory.with {
            pipelineJob(jobName) {

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/component-alignment-pipeline'))
                        sandbox()
                    }
                }
                properties {
                    JobSharedUtils.doDisableConcurrentBuilds(delegate)
                }
                JobSharedUtils.defaultBuildDiscarder(delegate)
                triggers {
                    cron('@weekly')
                }
                parameters {
                    JobSharedUtils.mavenParameters(params: delegate)
                    JobSharedUtils.gitParameters(delegate, repoUrl, branch)
                    stringParam {
                        name("TO_ADDRESS")
                        defaultValue(COMP_ALIGMENT_TO_ADDRESS)
                    }
                    stringParam {
                        name("HARMONIA_SCRIPT")
                        defaultValue('upgrade-components-report.sh')
                    }
                    stringParam {
                        name("COMPONENT_UPGRADE_LOGGER")
                        defaultValue(COMP_ALIGMENT_UPGRADE_LOGGER)
                    }
                }
            }
        }
    }
}