new ci_jobs.MvnBuilder(repoName: 'aphrodite').build(this)

new ci_jobs.MvnBuilder(repoName: 'assistant').build(this)

new ci_jobs.MvnBuilder(repoName: 'bug-clerk').build(this)

new ci_jobs.MvnBuilder(repoName: 'cryo').build(this)

new ci_jobs.MvnBuilder(repoName: 'mjolnir').build(this)

new ci_jobs.MvnBuilder(repoName: 'prbz-overview').build(this)

new ci_jobs.BashBuilder(repoName: 'harmonia', branch: 'olympus').build(this)

new ci_jobs.BashBuilder(jobName: 'ci-harmonia-cci', repoName: 'harmonia', branch: 'olympus-cci').build(this)

EapView.jobList(this, 'SET CI', 'ci.*')