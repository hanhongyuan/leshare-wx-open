# 微信公众号消息处理

### 针对weixin-java-tools的改动

**加密**

- 修改*WxMpConfigStorage*类， 增加`getAuthorizerAppid()`和`setAuthorizerAppid(String authorizerAppid)`方法
- 修改*WxMpInMemoryConfigStorage*类, 增加authorizerAppid字段
- 修改*WxMpCryptUtil*类，appId改为AuthorizerAppid

- 2017-07-18 莆田高速闽通卡公众号交互消息处理