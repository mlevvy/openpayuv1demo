package pl.klkl.openpayu.requestBuilder

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import pl.klkl.openpayu.config.Config
import pl.klkl.openpayu.config.PropertiesConfig

class PayUMessages {
    static Config config = PropertiesConfig.prepareConfig()

    static def orderCreateRequest(Map props = [:]) {
        println("Order Create Request parameters: $props")
        """<?xml version="1.0" encoding="UTF-8"?>
        <OpenPayU xmlns="http://www.openpayu.com/openpayu.xsd">
           <HeaderRequest>
              <Algorithm>MD5</Algorithm>
              <SenderName>exampleSenderName</SenderName>
              <Version>1.0</Version>
           </HeaderRequest>
           <OrderDomainRequest>
              <OrderCreateRequest>
                 <ReqId>f0a728e0f9dce5172671b2a6fa82799f</ReqId>
                 <CustomerIp>127.0.0.1</CustomerIp>
                 <NotifyUrl>http://localhost:80/OrderNotifyRequest.php</NotifyUrl>
                 <OrderCancelUrl>http://localhost:80/layout/page_cancel.php</OrderCancelUrl>
                 <OrderCompleteUrl>http://localhost:80/layout/page_success.php</OrderCompleteUrl>
                 <Order>
                    <MerchantPosId>${config.merchantPosId()}</MerchantPosId>
                    <SessionId>${props.sessionid ?: 'b9ca570f4b82a5f83440c6727f949448'}</SessionId>
                    <OrderUrl>http://localhost:80/layout/page_cancel.php?order=1192958629</OrderUrl>
                    <OrderCreateDate>${ISODateTimeFormat.dateTime().print(new DateTime());}</OrderCreateDate>
                    <OrderDescription>random description (f03ca454e515a01eaa3c3e0c609f6a9c)</OrderDescription>
                    <MerchantAuthorizationKey>${config.posAuthKey()}</MerchantAuthorizationKey>
                    <OrderType>MATERIAL</OrderType>
                    <ShoppingCart>
                       <GrandTotal>24600</GrandTotal>
                       <CurrencyCode>PLN</CurrencyCode>
                       <ShoppingCartItems>
                          <ShoppingCartItem>
                             <Quantity>1</Quantity>
                             <Product>
                                <Name>random test product</Name>
                                <UnitPrice>
                                   <Gross>12300</Gross>
                                   <Net>0</Net>
                                   <Tax>0</Tax>
                                   <TaxRate>0</TaxRate>
                                   <CurrencyCode>PLN</CurrencyCode>
                                </UnitPrice>
                             </Product>
                          </ShoppingCartItem>
                          <ShoppingCartItem>
                             <Quantity>1</Quantity>
                             <Product>
                                <Name>random test product</Name>
                                <UnitPrice>
                                   <Gross>12300</Gross>
                                   <Net>0</Net>
                                   <Tax>0</Tax>
                                   <TaxRate>0</TaxRate>
                                   <CurrencyCode>PLN</CurrencyCode>
                                </UnitPrice>
                             </Product>
                          </ShoppingCartItem>
                       </ShoppingCartItems>
                    </ShoppingCart>
                 </Order>
                 <ShippingCost>
                    <AvailableShippingCost>
                       <CountryCode>PL</CountryCode>
                       <ShipToOtherCountry>true</ShipToOtherCountry>
                       <ShippingCostList>
                          <ShippingCost>
                             <Type>courier_0</Type>
                             <CountryCode>PL</CountryCode>
                             <Price>
                                <Gross>1220</Gross>
                                <Net>0</Net>
                                <Tax>0</Tax>
                                <TaxRate>0</TaxRate>
                                <CurrencyCode>PLN</CurrencyCode>
                             </Price>
                          </ShippingCost>
                          <ShippingCost>
                             <Type>courier_1</Type>
                             <CountryCode>PL</CountryCode>
                             <Price>
                                <Gross>861</Gross>
                                <Net>700</Net>
                                <Tax>161</Tax>
                                <TaxRate>23</TaxRate>
                                <CurrencyCode>PLN</CurrencyCode>
                             </Price>
                          </ShippingCost>
                       </ShippingCostList>
                    </AvailableShippingCost>
                    <ShippingCostsUpdateUrl>http://localhost:80/ShippingCostRetrieveRequest.php</ShippingCostsUpdateUrl>
                 </ShippingCost>
                 <Customer>
                    <Email>example@mail.address.com</Email>
                    <FirstName>Jan</FirstName>
                    <LastName>Kowalski</LastName>
                    <Phone>01234567</Phone>
                    <Language>pl_PL</Language>
                    <Shipping>
                       <Street>Marcelinska</Street>
                       <HouseNumber>90</HouseNumber>
                       <ApartmentNumber />
                       <PostalCode>69-456</PostalCode>
                       <City>Poznan</City>
                       <CountryCode>PL</CountryCode>
                       <AddressType>SHIPPING</AddressType>
                       <RecipientName>Jan Kowalski</RecipientName>
                    </Shipping>
                    <Invoice>
                       <Street>Marcelinska</Street>
                       <HouseNumber>90</HouseNumber>
                       <ApartmentNumber />
                       <PostalCode>60-324</PostalCode>
                       <City>Poznan</City>
                       <CountryCode>PL</CountryCode>
                       <AddressType>BILLING</AddressType>
                       <RecipientName>PayU SA</RecipientName>
                       <TIN>779-23-08-495</TIN>
                    </Invoice>
                 </Customer>
              </OrderCreateRequest>
           </OrderDomainRequest>
        </OpenPayU>
        """
    }
}
