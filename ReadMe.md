PROJEME HOŞGELDİNİZ.

Öncelikle belirtmeliyim ki üç gün içerisinde yetiştirebildiğim kadarıyla yaptım.
Arayüzleri yetiştiremedim ama söylemeliyim ki genel amaç aynı CRUD işlemleri hepsi.

Core modülü içerisinde .sql dosyasını paket içerisinde 'resource' klasöründe bulacaksınız.
Esnek ürün alanları için kendi veri tabanı mantığımı kurdum :)

Örnek teşkil etmesi amacıyla her tabloya birer satır ekleme yaptım.

Kullanıcı girişi yaparken aşağıdaki bilgileri kullanarak giriş yapınız.

Email: user@gmail.com
Password: admin1234

ÖZET:
- Proje modüler yapıldı.
   - Core: Tüm dao ve entitiy sınıflarının bulunduğu modül
   - Middleware: Restful servisler ve schedule sınıfarının bulunduğu modül
   - Web: web.xml, faces-config.xml, .xhtml arayüz dosyaları, listener classlarının bulunduğu modül.
- Schedule işlemleri yapıldı.
- Ürün yapısı esnek tutuldu.
- Gerekli javadoclar yazıldı.
- UnitTestler yetiştirilemedi.
- Tüm arayüzler yetiştirilemedi fakat en önemli iki arayüz yazıldı.
- Örnek olarak restful servis açıldı.
- Katmanlı yapının tam anlamıyla çalışması için gerek orta yazılım katmanı rest servisleri yetiştirilemedi.
- Aynı anda iki kullanıcı girişi yapılamadı.

NOT: Manuel olarak denemelerim sonucu tüm dao sınıflarını doğru çalıştığını test ettim.
NOT2: Bazı yerlerde(özellikle dao classlarındaki ManagedBean name değişkeninde) fazla kodlar bulunmakta mazur görün :)

KURULUM:
1. Github dosyasını .zip olarak indiriniz.
2. Apache Tomcat 9 server indirip kullandığınız IDE ortamında kurulumunu gerçekleştiriniz(Ben IntelliJ IDEA 2016.1 kullandım).
3. Proje klasörüne girerek 'mvn compile','mvn package' komutlarını sırasıyla çalıştırınız.
4. Maven çıktısı olan 'Web:war exploded' artifact'ını servera deploy ediniz.
5. Projeyi koşturabilirsiniz.
6. Schedule işlemi için, middleware modülündeki schedule.trigger paketindeki trigger sınıfını koşturunuz.
