import util.JobSharedUtils

pipelineJob(ITEM_NAME) {

    definition {
        cps {
            script(readFileFromWorkspace('pipelines/tck/tck-atinject-pipeline'))
            sandbox()
        }
    }
    properties {
        JobSharedUtils.doDisableConcurrentBuilds(delegate)
    }
    JobSharedUtils.defaultBuildDiscarder(delegate)
    parameters {
        JobSharedUtils.mavenParameters(params: delegate)
        stringParam {
            name("HARMONIA_SCRIPT")
            defaultValue('cts/atinject.sh')
        }
        stringParam {
            name("weldVersion")
            defaultValue('3.1.2.Final')
        }
    }
}
