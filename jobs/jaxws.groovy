pipelineJob(ITEM_NAME) {

    definition {
        cps {
            script(readFileFromWorkspace('pipelines/tck/tck-runner-pipeline'))
            sandbox()
        }
    }
    properties {
        disableConcurrentBuilds {
            abortPrevious(false)
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
            name ("PATH_TO_SCRIPT")
            defaultValue('harmonia/cts/jaxws.sh')
        }
        stringParam {
            name('GIT_REPOSITORY_BRANCH')
            defaultValue('jakarta_ee8')
        }
        stringParam {
            name('GIT_REPOSITORY_URL')
            defaultValue(INTERNAL_GIT_REPOSITORY_URL + '/j2eects/tck-jaxws-mods.git')
        }
        stringParam {
            name("runclientArgs")
            defaultValue('')
        }
        stringParam {
            name ("build_selector")
            defaultValue('')
        }
    }
}
