new eap7.Builder(branch:'xp2',
                 jobName: 'xp-2.0.x',
                ).buildAndTest(this)
EapView.jobList(this, 'xp-2.0.x', 'xp-2.0.x.*')

new eap7.Builder(branch:'xp-3.0.x',
                 jobName: 'xp-3.0.x',
                ).buildAndTest(this)
new eap7.Builder(branch:'xp-3.0.x-proposed',
                 jobName: 'xp-3.0.x-proposed',
                ).buildAndTest(this)
EapView.jobList(this, 'xp-3.0.x', 'xp-3.0.x.*')

new eap7.Builder(branch:'xp-4.0.x',
                jobName: 'xp-4.0.x',
                customParams: {
                  stringParam {
                    name("BUILD_OPTS")
                    defaultValue("-Drelease -DnoManualmode")
                  },
                  stringParam {
                    name("TESTSUITE_OPTS")
                    defaultValue("-DnoManualmode")
                  }   
                }
                ).buildAndTest(this)
new eap7.Builder(branch:'xp-4.0.x-proposed',
                jobName: 'xp-4.0.x-proposed',
                customParams: {
                  stringParam {
                    name("BUILD_OPTS")
                    defaultValue("-Drelease -DnoManualmode")
                  },
                  stringParam {
                    name("TESTSUITE_OPTS")
                    defaultValue("-DnoManualmode")
                  }
                }
                ).buildAndTest(this)
EapView.jobList(this, 'xp-4.0.x', 'xp-4.0.x.*')
