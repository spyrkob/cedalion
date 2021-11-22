pipelineJob('ansible-ci') {
  parameters {
    stringParam {
      name ("PATH_TO_SCRIPT")
      defaultValue("build-collection.sh")
    }
    stringParam {
      name ("GIT_REPOSITORY_URL")
      defaultValue("https://github.com/rpelisse/redhat-csp-download.git")
    }
    stringParam {
      name ("GIT_REPOSITORY_BRANCH")
      defaultValue("main")
    }
    stringParam {
      name ("BUILD_PODMAN_IMAGE")
      defaultValue("localhost/ansible")
    }
  }
  definition {
    cps {
        script(readFileFromWorkspace('pipelines/bash-pipeline'))
        sandbox(true)
    }
  }
}
