new vbe.Builder(branch: '7.4.x', javaHome: "/opt/oracle/openjdk-11.0.14.1_1").buildAndTest(this)

EapView.jobList(this, 'vbe', 'vbe-eap.*')
