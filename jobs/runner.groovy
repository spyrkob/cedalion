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
            defaultValue('harmonia/cts/runner.sh')
        }
        stringParam {
            name('testFolder')
            defaultValue('unitializedTestFolder')
        }
        booleanParam {
            name('securityManager')
            defaultValue(false)
        }
        booleanParam {
            name('reverse')
            defaultValue(false)
        }
        stringParam {
            name ("build_selector")
            defaultValue('')
        }
    }
}
