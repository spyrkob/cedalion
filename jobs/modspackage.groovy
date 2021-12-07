pipelineJob(ITEM_NAME) {

    definition {
        cps {
            script(readFileFromWorkspace('pipelines/tck/tck-pipeline'))
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
            defaultValue('harmonia/cts/modspackage.sh')
        }
        stringParam {
            name('GIT_REPOSITORY_BRANCH')
            defaultValue('jakarta_ee8-eap_7.3.x')
        }
        stringParam {
            name('GIT_REPOSITORY_URL')
            defaultValue(INTERNAL_GIT_REPOSITORY_URL + '/j2eects/cts-8-mods.git')
        }
    }
}
