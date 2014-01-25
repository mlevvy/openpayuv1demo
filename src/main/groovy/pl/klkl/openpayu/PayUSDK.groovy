package pl.klkl.openpayu

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import pl.klkl.openpayu.config.PropertiesConfig

@Mixin(RequestUtils)
class PayUSDK {
    def openPayU = new RESTClient( 'https://sandbox.payu.pl/pl/standard/co/openpayu/OrderCreateRequest' )
    def authPayU = new RESTClient( 'https://sandbox.payu.pl/pl/standard/user/oauth/authorize' )
    def config = PropertiesConfig.prepareConfig()
    def signatureKey = config.signatureKey()


    def orderRegister(String request){
        def response = openPayU.post([
                body : urlEncode(request),
                requestContentType: ContentType.URLENC,
                headers : [
                        Accept : 'application/xml',
                        'openpayu-signature':"sender=${config.clientId()};signature=${generateMD5(request,signatureKey)};algorithm=MD5;content=DOCUMENT"
                ]])
        response
    }

    def authorizeByClientCredentials() {
        def response = authPayU.post([
                query : ["client_id":config.clientId(),"client_secret":config.clientSecret(),"grant_type":"client_credentials"]])
        println("Authorization access_token: "+response.responseData.access_token)
        println("Authorization refresh_token: "+response.responseData.refresh_token)
        println("Authorization expires_in: "+response.responseData.expires_in)
        response.responseData
    }
}
