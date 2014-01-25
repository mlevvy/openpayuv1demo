import pl.klkl.openpayu.PayUSDK
import pl.klkl.openpayu.requestBuilder.PayUMessages
import spock.lang.Specification

class SimpleBehaviour extends Specification{
    PayUSDK payu = new PayUSDK();
    XmlSlurper xml = new XmlSlurper();

    def "Giving a request with random session ID, status should be OPENPAYU_SUCCESS"() {
        given:
        def request = PayUMessages.orderCreateRequest([sessionid:generate(9)])

        when:
        def response = payu.orderRegister(request)

        then:
        response.status == 200
        xml.parseText(response.data.text).depthFirst().find { it.name() == 'StatusCode' }?.text() == 'OPENPAYU_SUCCESS'
    }

    def "When session is created authentication should be possible"() {
        given:
        def sessionid = generate(9)
        def request = PayUMessages.orderCreateRequest([sessionid:sessionid])

        when:
        def response = payu.orderRegister(request)
        def authResponse = payu.authorizeByClientCredentials()


        then:
        response.status == 200
        xml.parseText(response.data.text).depthFirst().find { it.name() == 'StatusCode' }?.text() == 'OPENPAYU_SUCCESS'
        authResponse.access_token != null
        println("You can access payU by URL: https://sandbox.payu.pl/pl/standard/co/summary?sessionId=$sessionid&oauth_token=$authResponse.access_token&showLoginDialog=true")
    }

    def generate(int size){
        generator( (('A'..'Z')+('0'..'9')).join(), size )
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
        }
    }


}
