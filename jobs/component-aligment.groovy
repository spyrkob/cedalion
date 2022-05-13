import alignment.Builder

new alignment.Builder(id: 'component-alignment-eap-74', rule: 'eap-74', reportTitle: 'JBoss_EAP_7.4.x', logger_project_code: 'olympus-eap-7.4.x', jobSuffix: 'eap-74', repoUrl: 'git@github.com:jbossas/jboss-eap7.git', branch: '7.4.x', toAddress: EAP_COMP_ALIGMENT_TO_ADDRESS).build(this)

new alignment.Builder(id: 'component-alignment-elytron-web', rule: 'elytron-1x', reportTitle: 'Elytron_Web', logger_project_code: 'olympus-elytron-web-master', jobSuffix: 'elytron-web', repoUrl: 'git@github.com:wildfly-security/elytron-web.git', branch: 'master', toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)
new alignment.Builder(id: 'component-alignment-wildfly-elytron', rule: 'elytron-1x', reportTitle: 'Wildfly_Elytron', logger_project_code: 'olympus-elytron-1.x', jobSuffix: 'wildfly-elytron', repoUrl: 'git@github.com:wildfly-security/wildfly-elytron.git', branch: '1.x', toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)

new alignment.Builder(id: 'component-alignment-wildfly-core-74', rule: 'eap-74', reportTitle: 'Wildfly_Core_15.0.x_EAP_7.4', logger_project_code: 'olympus-wildfly-core-eap-7.4.x', jobSuffix: 'wildfly-core-74', repoUrl: 'git@github.com:jbossas/wildfly-core-eap.git', branch: '15.0.x', toAddress: EAP_COMP_ALIGMENT_TO_ADDRESS).build(this)

new alignment.Builder(id: 'component-alignment-Wildfly', rule: 'wildfly-master', reportTitle: 'Wildfly', logger_project_code: 'olympus-wildfly-master', jobSuffix: 'wildfly', repoUrl: 'git@github.com:wildfly/wildfly.git', branch: 'main', toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)
new alignment.Builder(id: 'component-alignment-wildfly-core', rule: 'wildfly-master', reportTitle: 'Wildfly_Core', logger_project_code: 'olympus-wildfly-core-master', jobSuffix: 'wildfly-core', repoUrl: 'git@github.com:wildfly/wildfly-core.git', branch: 'main', toAddress: WFLY_COMP_ALIGMENT_TO_ADDRESS).build(this)

EapView.jobList(this, 'Component Alignment', 'component-alignment.*')
