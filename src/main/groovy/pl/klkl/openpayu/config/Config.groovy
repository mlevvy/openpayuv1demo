package pl.klkl.openpayu.config

public interface Config {
    String env()
    String merchantPosId()
    String posAuthKey()
    String clientId()
    String clientSecret()
    String signatureKey()
}