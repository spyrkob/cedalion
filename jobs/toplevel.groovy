pipelineJob(ITEM_NAME) {

    definition {
        cps {
            script(readFileFromWorkspace('pipelines/tck/tck-toplevel-pipeline'))
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
            name ("build_number")
            defaultValue('')
        }
        stringParam {
            name ("preBuiltAppServerZip")
            defaultValue('')
        }
    }
}
