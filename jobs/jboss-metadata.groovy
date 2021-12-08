new ci_jobs.Builder(
        jobName: 'jboss-metadata',
        repoUrl: 'git@github.com:jboss/metadata.git',
        mavenGoals: '-U package')
    .buildMvnJob(this)