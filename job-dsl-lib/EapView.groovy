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
        }
    }
}
