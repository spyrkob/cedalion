package ci_jobs

import util.JobSharedUtils

class MvnBuilder {

    String jobName
    String repoName
    String repoUrl
    String mavenGoals = '' // defaults to clean install in hera

    def build(factory) {
        if (jobName == null) {
            jobName = 'ci-' + repoName
        }
        if (repoUrl == null) {
            repoUrl = 'https://github.com/jboss-set/' + repoName
        }
        factory.with {
            pipelineJob(jobName) {

                definition {
                    cps {
                        script(readFileFromWorkspace('pipelines/mvn-pipeline'))
                        sandbox()
                    }
                }
                JobSharedUtils.defaultBuildDiscarder(delegate)
                triggers {
                    scm(JobSharedUtils.DEFAULT_SCHEDULE)
                    cron('@daily')
                }
                parameters {
                    JobSharedUtils.gitParameters(delegate, repoUrl, 'master')
                    JobSharedUtils.mavenParameters(params: delegate)
                    // override MAVEN_OPTS to add -Dnorpm
                    stringParam {
                        name ("MAVEN_OPTS")
                        defaultValue("-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2  -Dnorpm")
                    }
                    stringParam {
                        name ("MAVEN_GOALS")
                        defaultValue(mavenGoals)
                    }
                }
            }
        }
    }
}
