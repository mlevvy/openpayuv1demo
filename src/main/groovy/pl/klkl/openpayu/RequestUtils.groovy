package pl.klkl.openpayu

import java.security.MessageDigest

class RequestUtils {
    static def generateMD5(String requestNotUrlEncoded, String signatureKey) {
        String input = requestNotUrlEncoded+signatureKey
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(input.bytes);
        new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0').toString()
    }

    static def urlEncode(String request){
        "DOCUMENT=" + java.net.URLEncoder.encode(request,"UTF-8")
    }
}
