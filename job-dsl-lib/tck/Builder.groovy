package tck

import util.JobSharedUtils

class Builder {

    String jobName
    String pipelineName
    Closure additionalParams

    def exclusiveJob(factory) {
        factory.with {
            pipelineJob(jobName) {
                disabled()

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/tck/' + pipelineName))
                        sandbox()
                    }
                }
                properties {
                    JobSharedUtils.doDisableConcurrentBuilds(delegate)
                }
                JobSharedUtils.defaultBuildDiscarder(delegate)
                parameters {
                    JobSharedUtils.mavenParameters(params: delegate)
                    additionalParams.call(delegate)
                }
            }
        }
    }

    def concurrentJob(factory) {
        factory.with {
            pipelineJob(jobName) {
                disabled()

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/tck/' + pipelineName))
                        sandbox()
                    }
                }
                JobSharedUtils.defaultBuildDiscarder(delegate)
                parameters {
                    JobSharedUtils.mavenParameters(params: delegate)
                    additionalParams.call(delegate)
                }
            }
        }
    }
}
