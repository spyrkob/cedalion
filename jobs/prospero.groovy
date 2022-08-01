import util.JobSharedUtils

pipelineJob('prospero') {
    // for some reason readFileFromWorkspace doesn't work from within baseJob call
    definition {
        cps {
            script(readFileFromWorkspace('pipelines/mvn-pipeline'))
            sandbox()
        }
    }
    parameters {
        JobSharedUtils.gitParameters(delegate, 'https://github.com/wildfly-extras/prospero.git', 'master')
        JobSharedUtils.mavenParameters(params: delegate, javaHome: javaHome)
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

EapView.jobList(this, 'prospero')