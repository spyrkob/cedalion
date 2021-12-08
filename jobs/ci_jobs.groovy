new ci_jobs.Builder(repoName: 'aphrodite').buildMvnJob(this)

new ci_jobs.Builder(repoName: 'assistant').buildMvnJob(this)

new ci_jobs.Builder(repoName: 'bug-clerk').buildMvnJob(this)

new ci_jobs.Builder(repoName: 'cryo').buildMvnJob(this)

new ci_jobs.Builder(repoName: 'mjolnir').buildMvnJob(this)

new ci_jobs.Builder(repoName: 'prbz-overview').buildMvnJob(this)

new ci_jobs.Builder(repoName: 'harmonia', branch: 'olympus').buildBashJob(this)

new ci_jobs.Builder(jobName: 'ci-harmonia-cci', repoName: 'harmonia', branch: 'olympus-cci').buildBashJob(this)

EapView.jobList(this, 'SET CI', 'ci.*')