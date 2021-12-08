import tck.Builder

new tck.Builder(
        jobName: 'atinject',
        pipelineName:'tck-atinject-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("weldVersion")
                    defaultValue('3.1.2.Final')
                }
                stringParam {
                    name("HARMONIA_SCRIPT")
                    defaultValue('cts/atinject.sh')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'appserver',
        pipelineName:'tck-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("PREBUILD_URL")
                    defaultValue('')
                }
                stringParam {
                    name("HARMONIA_SCRIPT")
                    defaultValue('cts/appserver.sh')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'beanvalidation',
        pipelineName:'tck-mvn-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("PREBUILD_URL")
                    defaultValue('')
                }
                stringParam {
                    name("HARMONIA_SCRIPT")
                    defaultValue('cts/beanvalidation.sh')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'buildcts',
        pipelineName:'tck-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("PATH_TO_SCRIPT")
                    defaultValue('harmonia/cts/buildcts.sh')
                }
                stringParam {
                    name("HARMONIA_SCRIPT")
                    defaultValue('cts/buildcts.sh')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'cdi',
        pipelineName:'tck-mvn-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("build_selector")
                    defaultValue('')
                }
                stringParam {
                    name("HARMONIA_SCRIPT")
                    defaultValue('cts/cdi.sh')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'glassfishpackage',
        pipelineName:'tck-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("PATH_TO_SCRIPT")
                    defaultValue('harmonia/cts/modspackage.sh')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'modspackage',
        pipelineName:'tck-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("PATH_TO_SCRIPT")
                    defaultValue('harmonia/cts/glassfishpackage.sh')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'jaxb23',
        pipelineName:'tck-runner-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("PATH_TO_SCRIPT")
                    defaultValue('harmonia/cts/jaxb23.sh')
                }
                stringParam {
                    name("build_selector")
                    defaultValue('')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'jaxws',
        pipelineName:'tck-runner-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name("PATH_TO_SCRIPT")
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
                    name("build_selector")
                    defaultValue('')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'runner',
        pipelineName:'tck-runner-pipeline',
        additionalParams: { delegate ->
            delegate.with {
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
        }}
    ).concurrentJob(this)

new tck.Builder(
        jobName: 'saaj',
        pipelineName:'tck-runner-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name ("PATH_TO_SCRIPT")
                    defaultValue('harmonia/cts/saaj.sh')
                }
                stringParam {
                    name('GIT_REPOSITORY_URL')
                    defaultValue(INTERNAL_GIT_REPOSITORY_URL + '/j2eects/tck-saaj-mods.git')
                }
                stringParam {
                    name('GIT_REPOSITORY_BRANCH')
                    defaultValue('jakarta_ee8')
                }
                stringParam {
                    name ("build_selector")
                    defaultValue('')
                }
                stringParam {
                    name ("runclientArgs")
                    defaultValue('')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'websocket',
        pipelineName:'tck-runner-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name ("PATH_TO_SCRIPT")
                    defaultValue('harmonia/cts/websocket.sh')
                }
                stringParam {
                    name ("build_selector")
                    defaultValue('')
                }
                stringParam {
                    name ("runclientArgs")
                    defaultValue('')
                }
            }}
    ).exclusiveJob(this)

new tck.Builder(
        jobName: 'toplevel',
        pipelineName:'tck-toplevel-pipeline',
        additionalParams: { delegate ->
            delegate.with {
                stringParam {
                    name ("build_number")
                    defaultValue('')
                }
                stringParam {
                    name ("preBuiltAppServerZip")
                    defaultValue('')
                }
            }}
    ).exclusiveJob(this)

listView('TCK') {
    recurse(true)
    jobs {
        names('atinject', 'beanvalidation', 'appserver', 'buildcts', 'cdi')
        names('glassfishpackage', 'jaxb23', 'jaxws', 'modspackage', 'runner')
        names('saaj', 'websocket', 'toplevel')
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}
