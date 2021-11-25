pipelineJob('ansible-ci') {
  parameters {
    stringParam {
      name ("PATH_TO_SCRIPT")
      defaultValue("build-collection.sh")
    }
    stringParam {
      name ("GIT_REPOSITORY_URL")
      defaultValue("")
    }
    stringParam {
      name ("GIT_REPOSITORY_BRANCH")
      defaultValue("main")
    }
    stringParam {
      name ("BUILD_PODMAN_IMAGE")
      defaultValue("localhost/ansible")
    }
    stringParam {
      name ("VERSION")
      defaultValue("")
    }
  }
  definition {
    cps {
        script(readFileFromWorkspace('pipelines/ansible-pipeline'))
        sandbox(true)
    }
  }
}
