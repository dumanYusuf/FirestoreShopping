Bu uygulama, Clean Architecture prensiplerine uygun olarak tasarlanmış firestore authentication ve cloud firestore veritabanı kullanılarak yazılmış bir E ticaret uygulamasıdır. Hilt kullanılarak bağımlılık yönetimi gerçekleştirilmiş 
ve Use Case katmanı aracılığıyla Repository ve ViewModel arasındaki bağlantılar sağlanmıştır.

Uygulama,ilk açıldığında kullanıcıyı Register sayfası karşılıyor.Kullanıcı kayıt olduktan sonra AnaSayfaya yönlendiriliyor anasayfada firestordan  çektiğim kategoriler listelenmektedir.
hem anasayfa da hemde kategorilere göre ürünler listelenmiştir kullanıcı istediği ürünü favorilere ya da daha sepete  ekleyebilir ve silebilir. Sepette ürününün adetinde atrırrma ve azaltma işlemi yapabilir.Sonrasında ödeme adıma geçip 
varsa adresi yoksa da adres ekleyip ödeme sayfasına gidiyor orda da kart ekleyebilir ,kartı kaydedebilir,kartı silebilir eklediği kartı hesabım sayfasından ulasabilir ya da eklediği adrese yine hesabım sayfasında ulaşabilir
Hesabım sayfasında da kayıt olduktan sonra adını,soyadını ve girmiş oldugu mail
hesabı görülmektedir hesabım sayfasında da favorilere,anasayfaya ya da sepete  sayfasına gidebilir ve isterse çıkış yapıp login ekranından tekrar giriş yapabilir.

Uygulamanın tasarımı şu şekilde yapılandırılmıştır:

Hilt: Bağımlılıkları yönetmek amacıyla kullanılır. Hilt, DI (Dependency Injection) sağlayarak kodun test edilebilirliğini ve sürdürülebilirliğini artırır.

Clean Architecture: Uygulama, Clean Architecture prensiplerine uygun olarak katmanlara ayrılmıştır. Bu yaklaşım, veri, iş mantığı ve kullanıcı arayüzü bileşenlerinin bağımsız bir şekilde yönetilmesini sağlar.

Use Case: İş mantığını yöneten ve işlevlerin tek bir sorumluluk doğrultusunda düzenlendiği katmandır. Repository ve ViewModel arasındaki bağlantıyı sağlayarak kodun modülerliğini ve bakımını kolaylaştırır.

ViewModel: Kullanıcı arayüzü ile iş mantığı arasındaki köprüyü kurar ve kullanıcı etkileşimlerine yanıt verir.

view:sadece UI kodları bulunmata ve viewModele erişip UI güncelleme işlem yapmaktdır.

Repository: Veritabanı veya uzak veri kaynakları ile etkileşimde bulunur. Firebase Authentication işlemlerini yönetir ve Use Case katmanına veri sağlar. Bağımsız olarak test edilebilir ve iş mantığından ayrıdır.

Bu yapı, kodun okunabilirliğini, bakımını ve test edilebilirliğini artırır. Her bir fonksiyon ve sınıf, belirli bir işlevi yerine getirecek şekilde tasarlanmıştır, bu da uygulamanın daha düzenli ve yönetilebilir olmasını sağlar.

![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop1.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop2.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop3.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop4.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop5.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop6.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop7.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop8.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop9.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop10.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop11.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop12.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop13.png?raw=true)
![image alt](https://github.com/dumanYusuf/FirestoreShopping/blob/master/shop14.png?raw=true)


