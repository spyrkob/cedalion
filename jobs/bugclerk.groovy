new bugclerk.Builder(
            jobName: 'bugclerk-reports-jira-eap720-unresolved',
            branch: 'EAP_720'
        ).build(this)

new bugclerk.Builder(
            jobName: 'bugclerk-reports-jira-eap730-unresolved',
            branch: 'EAP_730'
        ).build(this)

EapView.jobList(this, 'bugclerk', 'bugclerk.*')