# TheMovieDB-Android

## Installation
Run git clone to download proyect

```ruby
git clone https://github.com/luisMan97/TheMovieDB-Android.git
```

#### Arquitectura
Se implemento CLEAN como arquitectura, con seis capas
1) UI: Contiene los Activity y sus adapters
2) Presentation: Contiene los ViewModels
3) UseCase: Contiene los casos de uso (acciones de la aplicación)
4) Domain: Contiene las entidades
5) Data: Contiene el patron repository para obtener los datos ya sea de una API o una base de datos local
6) Framework: Contiene la implementación a más detalle de la obtención de datos usando ya la respectiva librería o framework (Retrofit, Room y etc)

