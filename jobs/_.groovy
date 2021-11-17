new eap7.Builder(branch:'7.4.x').build(this)

new eap7.Builder(branch:'7.4.x-proposed').build(this)

EAPView.jobList(this, 'eap-7.4.x', /eap-7\.4\/.*/)