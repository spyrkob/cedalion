package ci_jobs

import util.JobSharedUtils

class BashBuilder {

    String jobName
    String repoName
    String branch

    def build(factory) {
        if (jobName == null) {
            jobName = 'ci-' + repoName
        }
        factory.with {
            pipelineJob(jobName) {

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/bash-pipeline'))
                        sandbox()
                    }
                }
                JobSharedUtils.defaultBuildDiscarder(delegate)
                triggers {
                    scm(JobSharedUtils.DEFAULT_SCHEDULE)
                    cron('@daily')
                }
                parameters {
                    JobSharedUtils.gitParameters(delegate, 'https://github.com/jboss-set/' + repoName, branch)
                    stringParam {
                        name("PATH_TO_SCRIPT")
                        defaultValue("./tests/tests-suite.sh")
                    }
                    stringParam {
                        name("BUILD_PODMAN_IMAGE")
                        defaultValue("localhost/bashomatons")
                    }
                }
            }
        }
    }
}
