package ci_jobs

import util.Constants

class BashBuilder {

    String jobName
    String repoName
    String branch
    String schedule = Constants.DEFAULT_SCHEDULE

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
                        defaultValue('https://github.com/jboss-set/' + repoName + '.git')
                    }
                    stringParam {
                        name ("GIT_REPOSITORY_BRANCH")
                        defaultValue(branch)
                    }
                    stringParam {
                        name ("PATH_TO_SCRIPT")
                        defaultValue("./tests/tests-suite.sh")
                    }
                    stringParam {
                        name ("BUILD_PODMAN_IMAGE")
                        defaultValue("localhost/bashomatons")
                    }
                }
            }
        }
    }
}
