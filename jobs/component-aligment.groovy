import alignment.Builder

new alignment.Builder(jobSuffix: 'eap-73',          repoUrl: 'git@github.com:jbossas/jboss-eap7.git',               branch: '7.3.x',  toAddress: EAP_COMP_ALIGMENT_TO_ADDRESS).build(this)
new alignment.Builder(jobSuffix: 'eap-74',          repoUrl: 'git@github.com:jbossas/jboss-eap7.git',               branch: '7.4.x',  toAddress: EAP_COMP_ALIGMENT_TO_ADDRESS).build(this)

new alignment.Builder(jobSuffix: 'elytron-web',     repoUrl: 'git@github.com:wildfly-security/elytron-web.git',     branch: 'master', toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)
new alignment.Builder(jobSuffix: 'wildfly-elytron', repoUrl: 'git@github.com:wildfly-security/wildfly-elytron.git', branch: '1.x',    toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)

new alignment.Builder(jobSuffix: 'wildfly-core-74', repoUrl: 'git@github.com:jbossas/wildfly-core-eap.git',         branch: '15.0.x', toAddress: EAP_COMP_ALIGMENT_TO_ADDRESS).build(this)
new alignment.Builder(jobSuffix: 'wildfly-core-73', repoUrl: 'git@github.com:jbossas/wildfly-core-eap.git',         branch: '10.1.x', toAddress: EAP_COMP_ALIGMENT_TO_ADDRESS).build(this)

new alignment.Builder(jobSuffix: 'wildfly',         repoUrl: 'git@github.com:wildfly/wildfly.git',                  branch: 'main',   toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)
new alignment.Builder(jobSuffix: 'wildfly-core',    repoUrl: 'git@github.com:wildfly/wildfly-core.git',             branch: 'main',   toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)

EapView.jobList(this, 'Component Alignment', 'component-alignment.*')
