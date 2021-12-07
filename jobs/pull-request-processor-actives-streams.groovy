import util.Constants

pipelineJob(ITEM_NAME) {

    definition {
        cps {
        script(readFileFromWorkspace('pipelines/pr-processor-pipeline'))
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
    triggers {
        scm (Constants.DEFAULT_SCHEDULE)
        cron('@daily')
    }
    parameters {
        stringParam {
            name ("PULL_REQUEST_PROCESSOR_HOME")
            defaultValue('/opt/tools/pr-processor/')
        }
        stringParam {
            name ("HARMONIA_SCRIPT")
            defaultValue('pr-processor.sh')
        }
        stringParam {
            name ("JAVA_HOME")
            defaultValue("/opt/oracle/java")
        }
        stringParam {
            name ("MAVEN_HOME")
            defaultValue("/opt/oracle/maven")
        }
        stringParam {
            name ("MAVEN_SETTINGS_XML")
            defaultValue('/opt/tools/settings.xml')
        }
        stringParam {
            name ("VERSION")
            defaultValue("0.8.10.Final")
        }
    }
}
