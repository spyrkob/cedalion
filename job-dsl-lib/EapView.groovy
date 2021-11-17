static jobList(factory, String viewName, String jobRegex) {
    factory.with {
        listView(viewName) {
            recurse(true)
            jobs {
                name(viewName)
                regex(jobRegex)
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
                lastDuration()
                buildButton()
            }
            leftPortlets {
                testStatisticsChart {
                    displayName ("Test Statistics Chart")
                }
            }
            rightPortlets {
                latestBuilds {
                    name ("Latest builds")
                    numBuilds(10)
                }
            }
        }
    }
}
