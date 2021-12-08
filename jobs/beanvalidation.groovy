import util.JobSharedUtils

pipelineJob(ITEM_NAME) {

    definition {
        cps {
            script(readFileFromWorkspace('pipelines/tck/tck-mvn-pipeline'))
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
            name ("HARMONIA_SCRIPT")
            defaultValue('cts/beanvalidation.sh')
        }
        stringParam {
            name ("version_org_hibernate_validator")
            defaultValue('')
        }
        stringParam {
            name ("build_selector")
            defaultValue('')
        }
    }
}
