package bugclerk

import util.JobSharedUtils

class Builder {

    String jobName
    String branch

    def build(factory) {
        factory.with {
            pipelineJob(jobName) {

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/bugclerk-pipeline'))
                        sandbox()
                    }
                }
                triggers {
                    scm (JobSharedUtils.DEFAULT_SCHEDULE)
                    cron('@hourly')
                }
                properties {
                    JobSharedUtils.doDisableConcurrentBuilds(delegate)
                }
                JobSharedUtils.defaultBuildDiscarder(delegate)
                parameters {
                    JobSharedUtils.gitParameters(delegate, 'git@github.com:jboss-set/bug-clerk-report-job.git', branch)
                    JobSharedUtils.mavenParameters(params: delegate)
                    stringParam {
                        name ("MAVEN_OPTS")
                        defaultValue('-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2 -Dnorpm')
                    }
                    stringParam {
                        name ("MAVEN_GOALS")
                        defaultValue('exec:java -U -Daphrodite.config=/opt/tools/aphrodite.json')
                    }
                }
            }
        }
    }
}
