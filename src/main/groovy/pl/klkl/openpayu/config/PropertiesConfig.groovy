package pl.klkl.openpayu.config

class PropertiesConfig {

    static Config prepareConfig() {
        Properties props = new Properties()
        def conf = System.properties["config"]
        def confInput = conf ? new File(conf).newInputStream() : getClass().getResourceAsStream("/checkoutExpress.properties")
        props.load(confInput)
        def config = [:]
        Config.methods.collect { it.name}.each {
            config[it] = { a -> props.get(it) }
        }
        config as Config
    }
}
