# "BiP Developer API" Örnek Kodları 

BiP’te Keşfet menüsü altındaki her bir avatar, bir Servis'i temsil etmektedir. Servisler üzerinden;
* Metin mesajları
* Görsel
* Ses
* Video
* Konum
* Zengin Medya Mesajı
   * Tekil
   * Çoğul
   * Anket

tipinde toplu gönderimler yapmak, BiP kullanıcılarından mesajlar almak ve BiP kullanıcılarıyla 1-1 iletişime geçmek mümkündür.

Burada BiP Developer API kullanarak hazırlanmış örnek uygulamaları bulabilirsiniz. Örneklerin amacı BiP Developer API'ye hızlı bir giriş yapılmasını sağlamaktır. Kodların ihtiyaca göre optimize edilmesi önerilmektedir. 

### REX

REX yapısı, servise mesaj atan her kullanıcıya önceden tanımlanmış komutlara uygun statik verilerin dönmesini sağlar. REX üzerinden Dinamik veri dönülmek istenirse bipussu'nden tanımlanan komut ile gelen mesaj Dısbaglantı URL'ine iletilir ve dönen cevap mesaj sahibine iletilir. Servis sağlayıcı mesajı analiz ederek istediği mesajı dinamik olarak dönebilir. (Örneğin; kullanıcı "dolar" mesajı gönderdiğinde, bipussu'nde tanımlı statik bir cevap vermek yerine mesaj servis sağlayıcının web servisine yönlendirilir, servis sağlayıcı kelimeyi analiz edip güncel dolar kurunu dönüş yapar.)

Servis Sağlayıcıya BiP kullanıcısına dönülecek medya (resim, video, ses) mesajlarına ilişkin içerik yüklemeleri FTS sunucusuna yapılır.

### TES

TIMS altyapısının mesajlaşma yeteneklerini dış dünyaya açan uygulamadır.
Servis sağlayıcı olarak konumlandırılan uygulamalar TES üzerinden BiP kullanıcısıyla mesajlaşabilir. Sisteme dâhil olan bu servisler BiP kullanıcısına servis kontağı olarak görünür.
Servis, BiP kullanıcısına herhangi bir anda mesaj gönderebileceği gibi, kullanıcıdan da herhangi bir anda mesaj alabilir. 
Servisten gönderilen medya (resim, video, ses) mesajlarına ilişkin içerik yüklemeleri FTS sunucusuna yapılır.


## Gereksinimler

Örneklerin çalışabilmesi için;

* BiP Developer API kullanıcı adı/şifre bilgilerinizi edinmeli ve /util/AppConstant sınıfı içerisindeki "USER" ve "PASS" bilgilerini kendi BiP Developer API kullanıcı adı/şifre bilgileriniz ile değiştirmelisiniz. 

* TES'in takipçiden BiP servisinize gelen mesajları web servisinize iletebilmesi için opersayon ekibine web servisinizin URL'ini vermiş olmanız gerekir.

## Başlarken

Örnekler Java dilinde kodlanmış ve Maven ve Spring kütüphaneleri kullanılmıştır. 

Servislerin çağrılabilmesi için örnekler içerisinde REST servisler açılmıştır. 

REST servisleri kullanarak etkilerini kendi BiP uygulamanızda gözlemleyebilirsiniz. 

Webservice isteklerinin içeriği JSON formatında taşınmaktadır. İstekler HTTPS protokolü ile yapılır ve kullanıcı doğrulaması için HTTP Basic Authentication yöntemi kullanılır.


* Proje localinize indirilir veya klonlanır.
* Kullandığınız IDE'den import maven project yapılıp proje seçilir. (Bu projede Eclipse kullanılmıştır. Intellij, Netbeans vb IDE'ler kullanılarak geliştirme yapılmak istenirse Eclipse projesi olarak import edilebilir.)
* Projenin dependencyleri otamatik yüklenir. Otomatik yüklenmezse pom.xml'e sağ tıklanıp Run as -> Maven install seçilerek yüklenir.
* AppConstant içerisindeki USER ve PASS değerleri kendi Bip Servisinizin bilgileriyle değiştrilir.
* Proje bir server üzerinde çalıştırılır. Örneğin; tomcat, jetty vb.
* Bu adımdan sonra 2 farklı şekilde test yapabilirsiniz: 1) Takipçi servisinize mesaj atar ve TES mesajı web servisinize yönlendirir, web servisiniz mesaja uygun cevabı döner. 2) Takipçiniz web servisinize mesaj atmış ve TES mesajı web servisinize yönlendirmiş gibi simule etmek için REST Client kullanılır.

**1)** Kullanıcı servisinize takip eder ve mesaj atar:

* TES gelen mesajı web servisinize yönlendirir.
* Web servisiniz mesaja cevap verir.
* Mesaj Bip uygulamanız aracılığıyla takipçiye iletilir. 
<p align="center">
<img src="http://www.bip.ai/wp-content/uploads/2016/09/Merhaba_dunya_canli.png" alt="Merhaba Dünya" height="400" width="200"/>
</p>

**2)** Takipçiniz Bip servisinize mesaj atmış gibi TES'in web servisinize yönlendireceği mesajı simule ederek deneme yapmak için şu adımları takip edebilirsiniz;
* Denemelerin yapılması için Rest Client kullanmanız gerekmektedir. Örneğin bir Chrome App'i olan Postman kullanılarak kullanıcıdan mesaj almış gibi tek kullanıcıya mesaj göndermek için:
![](http://www.bip.ai/wp-content/uploads/2016/09/Postman_Istegi.png)
   * Postman ekranından POST metodu seçilir.
   * URL kısmına çağrılacak servisin Path'i girilir. Örneğin; TES'ten gelen mesajı alıp TES üzerinden mesajı göndermek üzere hazırlanmış örnek API'nin URL'i http://localhost:8080/helloworld/helloTES girilir. (Projeyi localde çalıştırıyor, serverınız 8080'da etkinse http://localhost:8080/... farklı server ve port için http://server:port/...)
   * Body kısmının bilgileri row ve JSON(application/json) olarak seçilir. İçeriğe ise; takipçi mesaj gönderdiğinde TES API'nin Web servisinize göndereceği formatta JSON girilir. Örneğin;
     ```
     {
     "sender":"05a6d402f40383e4c016302e2dca75a2",
     "msgid":1342,
     "sendtime":"12.10.2015 08:00:00.123 +0300",
     "type":"M",
     "ctype":"T",
     "content":"selam"
     }
     ```

     (sender'daki Opak numara, test etmek istediğiniz numarayla değiştirilmeli. Mesajın iletilebilmesi için bu numara sahibinin, servisinizi takip ediyor olması lazım.)

     * Send'e basılarak istek gönderilir.

* Web servisiniz mesaja cevap verir.
* Mesaj Bip uygulamanız aracılığıyla takipçiye iletilir. (Kullanıcının gönderdiği "selam" mesajı Postman ile TES üzerinden web servisinize yönlendirilmiş gibi simule edildi ve web servisiniz cevap olarak "Merhaba Dünya" mesajını kullanıcıya dönmüş oldu)
<p align="center">
<img src="http://www.bip.ai/wp-content/uploads/2016/09/Merhaba_dunyaPNG.png" alt="Merhaba Dünya" height="400" width="200"/>
</p>


## Hiyerarşi

com.turkcell.bipai.helloworld

├── **api**

│&nbsp;&nbsp;&nbsp;&nbsp;├── **fts**

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── **request**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FTS API ile dosya yükleme isteği yapılırken kullanılan model sınıfıdır.

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── **response**&nbsp;&nbsp;&nbsp;Dosya yükleme işlemine FTS API'nin verdiği cevabı tutan sınıftır.

│&nbsp;&nbsp;&nbsp;&nbsp;├── **rex**

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **model**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REX  API üzerinden mesaj alma/gönderme yapılırken kullanılan mesaj modelini tutan sınıftır.

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **request**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REX API kullanılarak takipçiye gönderilen mesajın modelini tutan sınıftır.

│&nbsp;&nbsp;&nbsp;&nbsp;├── **tes**

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **model**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TES API üzerinden mesaj alma/gönderme yapılırken kullanılan mesaj modelini tutan sınıftır.

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **request**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TES API kullanılarak takipçiye gönderilen mesajların modelini tutan sınıftır.

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **response**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mesaj gönderme işlemine TES API'nin verdiği cevabı tutan sınıftır.

├── **command**

│&nbsp;&nbsp;&nbsp;&nbsp;├── **Command.java**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/" ile başlayan komutları modellemek için kullanılacak interface

│&nbsp;&nbsp;&nbsp;&nbsp;├── **HelpCommand.java**&nbsp;&nbsp;&nbsp;&nbsp;Command interface'ini kullanarak oluşturulmuş "/yardım" komutunu modelleyen örnek

│&nbsp;&nbsp;&nbsp;&nbsp;├── **config**&nbsp;&nbsp;&nbsp;&nbsp;Web Servis oluşturulmak için gereken Spring ayarları.

│&nbsp;&nbsp;&nbsp;&nbsp;├── **service**&nbsp;&nbsp;&nbsp;&nbsp;Mesaj alım/gönderim örnekleri

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **HelloWorldMultiUsers.java**&nbsp;&nbsp;&nbsp;&nbsp;Birden fazla takipçiye mesaj gönderen örneklerin bulunduğu web servis

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **HelloWorldREX.java**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REX API kullanarak mesaj alma/gönderme örnekleri

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **HelloWorldTES.java**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TES API kullanarak mesaj alma/gönderme örnekleri

│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├── **Service.java**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TES API'ye mesaj gönderme isteğinde bulunan sınıf

│&nbsp;&nbsp;&nbsp;&nbsp;├── **util**&nbsp;&nbsp;&nbsp;&nbsp;USER/PASS gibi değişkenlerin, FTS'e dosya yükleme işlemlerinin yapıldığı linkler gibi sabit bilgiler
 


## Örnekler

Örnekler service klasörü altında toplanmıştır. 3 temel örnek çeşidi vardır.

### 1) HelloWorldTES

Mesaj/Bildirim Alma ve 1-1 Mesaj Gönderme örneklerinin bulunduğu web servistir.  TES'in ilettiği mesajı alır ve takipçinin attığı mesaj tipine göre takipçiye mesaj gönderir.  5 örnek fonksiyon bulunmaktadır.  Bunlar; 

- **respondWithText**
- **respondWithSingleRMM**
- **respondWithMultiRMM**
- **respondWithPollRMM**
- **respondWithImage**

Örnek fonksiyonların çalışma şekli şu şekildedir.

Takipçiden gelen mesaj analiz edilir:

   - Gelen mesaj Text tipinde ise takipçiye Text tipinde cevap verilir: 
      - Text içerisinde "/yardım" gibi "/" ile başlayan ifadeler komut olarak algılanır ve bu komutlara karşılık tanımlanan açıklama takipçiye **respondWithText** fonksiyonu yardımıyla gönderilir. 
      - Text içerisinde  "rmm tekil" ifadesi varsa gelen mesaja karşılık takipçiye tekil RMM (Zengin MultiMedia) mesajı **respondWithSingleRMM** fonksiyonu yardımıyla gönderilir. 
      - Text içerisinde  "rmm çoğul" ifadesi varsa gelen mesaja karşılık takipçiye çoğul RMM mesajı **respondWithMultiRMM** fonksiyonu yardımıyla gönderilir. 
      - Text içerisinde "rmm anket" ifadesi varsa gelen mesaja karşılık takipçiye anket RMM mesajı **respondWithPollRMM** fonksiyonu yardımıyla gönderilir. 
   -  Gelen mesaj Image tipinde ise takipçiye Image tipinde cevap verilir:
      - Takipçiye gönderilecek image  **respondWithImage** fonksiyonu yardımıyla gönderilir.


Takipçiye medya gönderimi varsa, fonksiyonlar içerisinde öncelikle takipçiye gönderilecek fotoğraf FTS API yardımı ile Turkcell serverlarına yüklenir. Gönderilecek mesaj isteği; TES'in tek takipçiye mesaj göndermede kabul ettiği JSON formatı olan **TesSingleUserRequest** sınıfında oluşturulur.  Bu modelin alanları gönderilecek mesaj tipine göre (Text, Image, RMM vb.) doldurulur. Bu istek, Service sınıfının **send** fonksiyonuna parametre olarak verilip cağrılır. Send fonksiyonu **AppConstant** sınıfı içerisende tanımladığınız USER ve PASS kullanarak authenticationı sağlar. Oluşturulan mesaj isteği, TES API'nın tek kullanıcıya mesaj gönderen URL'ine POST edilerek mesaj takipçiye gönderilir.


### 2) HelloWorldREX

REX, takipçiden aldığı Text veya Location tipindeki mesajları Web Servisinize yönlendirir. Takipçi REX API ile gönderebileceğiniz mesaj tipleri Metin,  Resim, Ses, Video veya Konum tipinde olabilir. 

Bu örnekte takipçi keşfet servisine "dlr", "dolar", veya "dolar ne kadar" gibi mesajlar atmaktadır. REX bu mesajları Bip Üssü'nde tanımladığınız komutlarla benzerlik olup olmadığını tespit eder. Bip Üssü'nde tanımladığınız "dolar" keyword'ü ile benzerlik bulunur. Ayrıca bu keyworde karşılık verilecek cevap Bip Üssü'nde Dış Bağlantı olarak tanımlandığından, gelen mesaj ve eşleşme bulunan "dolar" keyword'ü web servisinize iletilir. Web servis içerisinde güncel dolar kuru hesaplanır (bu örneklerde gerçek zamanlı hesaplama yapılmamış sabit bir mesaj dönülmüştür, web servisinizin çalışma prensibine göre gelen mesaja uygun bir çok gerçek zamanlı veriyi hesaplayıp takipçiye dönebilirsiniz.). TES API'de olduğu gibi, takipçiye medya dönülmek isteniyorsa öncelikle FTS API yardımı ile medyalar Turkcell serverlarına yüklenir. Gönderilecek istek REX'inkabul ettiği JSON formatı olan **RexRequest** sınıfında oluşturulur ve dönüş yapılır. 

### 3) HelloWorldMultiUsers

TES API'nin çok kullanıcıya aynı ve çok kullanıcıya farklı mesaj gönderme örnekleri bu web servis içerisinde gösterilmiştir. 

Çok kullanıcıya aynı mesaj göndermek için mesaj gönderilecek kullanıcılar bir listeye eklenir ve gönderilecek mesaj ile birlikte **respondWithTextMultiUserDifferentMessage** fonksiyonu çağırılır. Bu fonksiyon gönderilecek measjı TES'in kabal ettiği JSON formatı olan **TesMultiUserSameMessageRequest** sınıfında oluşturur ve mesajı kullanıcılara iletir.

Çok kullanıcıya farklı mesaj göndermek için mesaj gönderilecek kullanıcılar ve her bir kullanıcıya gönderilecek mesaj bir Map içerisine eklenerek **respondWithTextMultiUserDifferentMessage** fonksiyonu çağırılır. Bu fonksiyon gönderilecek measjı TES'in kabal ettiği JSON formatı olan **TesMultiUserDifferentMessageRequest** sınıfında oluşturur ve mesajı kullanıcılara iletir.

### MerchantSimulator 
Payment Servis üzerinden ödeme işlemlerini gerçekleştiren simülatördür. Bu örnek için detaylı bilgiye [MerchantSimulator klasöründen](https://github.com/Turkcell/BiP.AI/tree/master/MerchantSimulator) ulaşabilirsiniz.

## Bundan sonra ...

BiP API'leriyle yolculuğunuzda başarılar diliyoruz.

Servisler ve Keşfet'le ilgili daha fazla bilgiye [http://www.bip.ai](http://www.bip.ai)'dan ve [developer wiki](http://www.bip.ai/documentations/baslarken)'den linkinden erişebilirsiniz. 

[![http://www.bip.ai](http://www.bip.ai/wp-content/themes/bip-developers/assets/images/logo.png)](http://www.bip.ai)
