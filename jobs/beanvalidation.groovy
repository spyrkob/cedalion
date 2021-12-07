pipelineJob(ITEM_NAME) {

    definition {
        cps {
            script(readFileFromWorkspace('pipelines/tck/tck-mvn-pipeline'))
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
            name ("HARMONIA_SCRIPT")
            defaultValue('cts/beanvalidation.sh')
        }
        stringParam {
            name ("JAVA_HOME")
            defaultValue('/opt/oracle/java')
        }
        stringParam {
            name ("MAVEN_HOME")
            defaultValue('/opt/apache/maven')
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
