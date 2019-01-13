# InstantStore
Project Mobile in Kotlin language

### Tecnologias Utilizadas: 
- Kotlin
- Firebase
- ~~Instant Apps~~

**Não se tornou possível continuar o projeto em implementação Instant App**

### Considerações sobre a tecnologia Instant App
**Para implementar Instant App, é necessário uma arquitetura especial com módulos distintos. Um módulo de Feature, onde é feita a codificação principal das telas, um modo base, que serve para compilação do código e intermediar a conversão das telas para o terceiro módulo, o módulo Instant App, que é reponsável de fato pelo porte da aplicação para a tecnologia, onde, não é necessário instalar o app no aparelho para rodar.**

**Tal arquitetura gera instabilidade na implementação de integrações que possam ser necessárias, como no caso, a integração com o Firebase. A comunicação entre os módulos faz com que seja necessária a implementação apenas de chamadas simples a Requisições REST externas, uma integração completa a terceiros, fazendo com que seja necessária a implementação de uma camada de negócio um pouco mais robusta, inviabiliza o uso da tecnologia no momento. Afinal, a tecnologia é para simplificar a comunicação, por isso uma extrema necessidade em que a aplicação seja puramente simples.**

### Conceito:
 ~~> O app Instant Store é aplicação mobile que visa fornecer a pequenos empreendedores uma alternativa, de interface amigável, para venda de produtos ou serviços associados ao conteúdo de contas comerciais do app Instagram.~~
 > O app Instant Store é aplicação mobile que visa fornecer a pequenos empreendedores uma alternativa, de interface amigável, para venda de produtos ou serviços associados ao conteúdos de mídia publicados na plataforma.
 
 **Houve auteração recente da API do Instagram, fazendo com que a [antiga API do Instagram](https://www.instagram.com/developer/) entrasse em DEPRECATED de forma imediata. A [nova API do Instagram](https://developers.facebook.com/docs/instagram-api/) necessita de implementação de backend para uso de Spring Social, o que não contempla a proposta deste projeto, então, decidiu-se o envio de imagens para o Firebase, via Firestore.**
  
### Principais Objetivos do App:
- ~~Prover uma abstração simples de e-commerce online para pequenos empreendedores com a utilização de fotos do Instagram;~~
- Fornecer uma interface amigável ao Cliente/Administrador;
- ~~Utilizar o conceito de Instants Apps para que a aplicação não precise ser instalada no smartphone;~~
- Oferecer um ambiente seguro aos usuários;

### Conceitos Técnicos:
- ~~A camada de negócio dessa aplicação foca no uso da API do Instagram;~~
- O desenvolvimento das telas utilizará um formato de design minimalista, ~~para facilitar o porte da aplicação para formato de Instant Apps.~~
- O App utilizará a autenticação de usuário via Firebase;
- A aplicação suportará as versões 8.0 - 9.0 do android ~~(sdk 28 se faz necessário para uso da tecnologia instant app);~~

### Protótipo base de telas:
- https://www.fluidui.com/editor/live/preview/cF9rUm1XbUUwbmVFTHRENzg0Z0YzQWJPb0hCU1FNems0Zw==

### Links úteis:
 - [Firebase Auth](http://www.appsdeveloperblog.com/firebase-authentication-example-kotlin/)
 - [Firebase Auth](https://medium.com/@paul.allies/kotlin-for-android-firebase-auth-275a262d825e)
 - [Toolbar Example](https://android--code.blogspot.com/2018/02/android-kotlin-toolbar-example.html)
 - [Firebase Auth Phone Kotlin](https://www.youtube.com/watch?v=4YM1n0zQ17I)
 - [Instagram API](https://www.instagram.com/developer/endpoints/users/)
 - [Sobre API Instagram](https://stackoverflow.com/questions/10881511/instagram-api-how-to-get-all-user-media)
 - [Fragment screen configuration Part1 kotlin](https://www.youtube.com/watch?v=mAFXFRiJbxw)
 - [Fragment screen configuration Part2 kotlin](https://www.youtube.com/watch?v=6Vpo9I3RcsI)
 - [List view with Images](https://www.youtube.com/watch?v=Ez5l8H-rkUQ)
 - [Grid view kotlin](https://grokonez.com/android/kotlin-gridview-example-show-list-of-items-on-grid-android)
 - [Circle image border](https://github.com/hdodenhof/CircleImageView)
 - [GridLayoutManager and StaggeredGridLayoutManager](https://www.android4dev.com/how-to-use-recyclerview-with-staggeredgridlayoutmanager-android-kotlin/)
 - [Android Kotlin Tutorial: Link RecyclerView](https://www.youtube.com/watch?v=HXz6618Zzn8&list=PLlxmoA0rQ-Lw5k_QCqVl3rsoJOnb_00UV&index=16)
 - [Tutorial Fragment](https://www.raywenderlich.com/361-android-fragments-tutorial-an-introduction-with-kotlin)
 - [Firebase 1](https://firebase.google.com/docs/auth/android/google-signin)
 - [Firebase 2](https://medium.com/@myric.september/authenticate-using-google-sign-in-kotlin-firebase-4490f71d9e44)
 - [Firebase 3](https://www.youtube.com/watch?v=i18IGN3MAbw)
 - [Firebase Firestore](https://grokonez.com/android/kotlin-firestore-example-crud-operations-with-recyclerview-android)
 - [Firebase Firestore videos](https://www.letsbuildthatapp.com/course_video?id=3512)
 - [Firebase Login](https://grokonez.com/android/kotlin-firebase-authentication-how-to-sign-up-sign-in-sign-out-verify-email-android)
 - [Kotlin RecyclerView Using Firebase](https://www.youtube.com/watch?v=5tgL0ujXeFk)
 - [Firebase UI](https://github.com/firebase/FirebaseUI-Android/tree/master/firestore)
 - [Display Firebase database items using Recyclerview with Cardview in android](https://www.mytrendin.com/display-firebase-database-items-recyclerview-cardview/)
  
 
 
