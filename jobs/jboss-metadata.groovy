new ci_jobs.MvnBuilder(
        jobName: 'jboss-metadata',
        repoUrl: 'git@github.com:jboss/metadata.git',
        mavenGoals: '-U package')
    .build(this)