import util.JobSharedUtils

pipelineJob('prospero-build') {
    // for some reason readFileFromWorkspace doesn't work from within baseJob call
    definition {
        cps {
            script(readFileFromWorkspace('pipelines/mvn-pipeline'))
            sandbox()
        }
    }
    parameters {
        JobSharedUtils.gitParameters(delegate, 'https://github.com/wildfly-extras/prospero.git', 'main')
        JobSharedUtils.mavenParameters(params: delegate, javaHome: '/opt/oracle/openjdk-11.0.14.1_1')
        // override MAVEN_OPTS to add -Dnorpm
        stringParam {
            name ("MAVEN_OPTS")
            defaultValue("-Dmaven.wagon.http.ssl.insecure=true -Dhttps.protocols=TLSv1.2  -Dnorpm")
        }
        stringParam {
            name ("MAVEN_GOALS")
            defaultValue("clean install -Pdist")
        }
    }
}

pipelineJob('prospero-eap') {
    // for some reason readFileFromWorkspace doesn't work from within baseJob call
    definition {
        cps {
            script(readFileFromWorkspace('pipelines/bash-pipeline-test'))
            sandbox()
        }
    }
    parameters {
        JobSharedUtils.gitParameters(delegate, 'https://github.com/spyrkob/harmonia.git', 'prospero')
        JobSharedUtils.mavenParameters(params: delegate, javaHome: '/opt/oracle/openjdk-11.0.14.1_1')
        // override MAVEN_OPTS to add -Dnorpm
        stringParam {
            name("PATH_TO_SCRIPT")
            defaultValue("./prospero/build-eap.sh")
        }
    }
}

EapView.jobList(this, 'Prospero', 'prospero.*')