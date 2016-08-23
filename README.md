# "BiP Developer API" Örnek Kodları 

BiP’te Keşfet menüsü altındaki her bir avatar, bir Servisi temsil etmektedir. Servisler üzerinden;
* Metin mesajları
* Görsel
* Ses
* Video
* Zengin Medya Mesajı
* Anket

tipinde toplu gönderimler yapmak, BiP kullanıcılarından mesajlar almak ve BiP kullanıcılarıyla 1-1 iletişime geçmek mümkündür.

Burada BiP Developer API kullanarak hazırlanmış örnek uygulamaları bulabilirsiniz. Örneklerin amacı BiP Developer API'ye hızlı bir giriş yapılmasını sağlamaktır. Kodların ihtiyaca göre optimize edilmesi önerilmektedir. 

## Başlarken

Örnekler Java dilinde kodlanmış ve Maven ve Spring kütüphaneleri kullanılmıştır. 

Servislerin çağrılabilmesi için örnekler içerisinde REST servisler açılmıştır. 

REST servisleri kullanarak etkilerini kendi BiP uygulamanızda gözlemleyebilirsiniz. 

Webservice isteklerinin içeriği JSON formatında taşınmaktadır. İstekler HTTPS protokolü ile yapılır ve kullanıcı doğrulaması için HTTP Basic Authentication yöntemi kullanılır.

Örneklerin çalıştırabilmesi için BiP Developer API kullanıcı / şifre bilgilerinizi edinmeli ve örnekler içerisindeki "user" ve "password" bilgilerini kendi BiP Developer API kullanıcı ve şifre bilginiz ile değiştirmelisiniz. 

## Örnekler

Aşağıda burada yer alan örnekler verilmiştir. 

* helloworldtes - TES API'yi kullanarak bir kullanıcıya "Merhaba dünya!" metnini gönderen örnek 
* helloworldrex - REX API'yi kullanarak bir kullanıcıya "Merhaba dünya!" metnini gönderen örnek 
* sendmessagestes - TES API'yi kullanarak birden fazla kullanıcıya mesaj gönderen örnek
* imageuploadsendrex - FTS API'yi kullanarak bir url'deki resim dosyasını upload eden ve REX'i kullanarak kullanıcıya gönderen örnek
* imageuploadsendtes - FTS API'yi kullanarak bir url'deki resim dosyasını upload eden ve TES'i kullanarak kullanıcıya gönderen örnek 
* rmmtes - FTS API'yi kullanarak bir url'deki resim dosyasını upload eden ve TES'i kullanarak kullanıcıya rich media message olarak gönderen örnek 

### REX

BiP kullanıcısının(servisi takip eden kişi) gönderdiği mesajı Servis Sağlayıcıya ileten, dönen cevabı da BiP kullanıcısına ileten uygulamadır.
Servis Sağlayıcıya BiP kullanıcısına dönülecek medya (resim, video, ses) mesajlarına ilişkin içerik yüklemeleri FTS sunucusuna yapılır.

### TES

TIMS altyapısının mesajlaşma yeteneklerini dış dünyaya açan uygulamadır.
Servis sağlayıcı olarak konumlandırılan uygulamalar TES üzerinden BiP kullanıcısıyla mesajlaşabilir. Sisteme dâhil olan bu servisler BiP kullanıcısına servis kontağı olarak görünür.
Servis, BiP kullanıcısına herhangi bir anda mesaj gönderebileceği gibi, kullanıcıdan da herhangi bir anda mesaj alabilir. 
Servisten gönderilen medya (resim, video, ses) mesajlarına ilişkin içerik yüklemeleri FTS sunucusuna yapılır.

## Bundan sonra ...

BiP API'leriyle yolculuğunuzda başarılar diliyoruz.

Servisler ve Keşfet'le ilgili daha fazla bilgiye [http://www.bip.ai](http://www.bip.ai)'dan ve [developer wiki](http://www.bip.ai/documentations/baslarken)'den linkinden erişebilirsiniz. 

[![http://www.bip.ai](http://www.bip.ai/wp-content/themes/bip-developers/assets/images/logo.png)](http://www.bip.ai)
