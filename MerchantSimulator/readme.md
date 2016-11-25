Bip Payment Simülatör
===================
Bip TES API üzerinden metin mesajlarla haberleşen bir ödeme servisi simülatörüdür. Temel amaç kullanıcının abone olduğu servis üzerinden mesajlaşarak sipariş işleminin gerçekleşmesidir.

----------

Dokümantasyon
-------------

Geliştirme Java dilinde ve Spring Framework üzerine hazırlanmıştır.  

> **Başlamadan Önce:**

> - Spring Framework REST API örneğine [buradan][1] erişebilirsiniz.
> - Bip Tes API için [HelloWorld! Projesine][2] github üzerinden erişebilirsiniz.
> - Bip API Dökümantasyon sitesine [buradan][3] erişebilirsiniz.

#### Kurulum İçin Gerekenler:
- [JDK 1.8][4]
- [Maven 3.0+][5]
- [Apache Tomcat 9.0][6] *Opsiyonel
- [Eclipse-JEE(Spring Tool Suite)][7] *Opsiyonel
- [Chrome Postman Extension][8] *Opsiyonel

#### Projeyi Çalıştırma
Öncelikle projenin maven dependencylerini indirebilmek için projenin root dizininde (pom.xml'in bulunduğu dizin) maven installer komutunu çalıştırıyoruz.
```
mvn install
```

/target dizinin altında deployment dosyamız hazırlanmış olacak. Daha sonrasında deploy atmak için tomcat dizininde webapps\ROOT pathine kopyalayıp sunucuyu çalıştırmanız gerekmektedir.
> **Önemli Not:** VM argümanı olarak konfigürasyon klasörünün pathi "**CONF_PATH**" argümanı ile verilmelidir.Konfigürasyon >dosyaları kullanıcının bilgileri doğrultusunda güncellenmelidir.
> 
> Örnek: -DCONF_PATH=C:\Users\XXXXX\workspace\BIPSaac\MerchantSimulator\conf

> **Not:**
>Proje tomcat ve weblogic sunucu üzerinde çalışacak şekilde dizayn edilmiştir. İstenildiği takdirde Application.java dosyasında değişiklik yapılarak standalone jar dosyası halinde execute edilebilir. Detaylı bilgi için [tıklayınız][9].

#### Proje Testi 
Lokalde test yapabilmek için istekleri postman üzerinden simüle edebiliriz.

- Clientdan gelen mesajı sunucumuza göndermek için:

URL : localhost:8080/merchantsimulator/callback
METHOD:POST
```
 {
   "sender":"9053xxxxxxxx",
   "msgid":12341,
   "sendtime":"12.10.2015 08:00:00.123 +0300",
   "type":"M",
   "ctype":"T",
   "content":"gazoz"
}

```
- Ödeme servisinden gelen isteği test için:

URL : localhost:8080/merchantsimulator/paymentcallback
METHOD:POST
```
 {
	"transactionId":"64364590", // sipariş için oluşturulan unique transaction ID
	"resultCode":0,
	"resultDesc":"SUCCESS"
	
}

```

  [1]: https://spring.io/guides/gs/consuming-rest/
  [2]: https://github.com/Turkcell/BiP.AI
  [3]: http://www.bip.ai/tr/documentations/servisler_nedir/
  [4]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  [5]: https://maven.apache.org/download.cgi
  [6]: https://tomcat.apache.org/download-90.cgi
  [7]: https://spring.io/tools
  [8]: https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop
  [9]:http://docs.spring.io/spring-boot/docs/current/reference/html/howto-traditional-deployment.html
