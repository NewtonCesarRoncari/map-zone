# map-zone [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21) [![codebeat badge](https://codebeat.co/badges/bedd8e90-a65b-4fb4-a36a-d5f6843e1b19)](https://codebeat.co/projects/github-com-newtoncesarroncari-map-zone-develop)

Este projeto consiste na criação de um app para gerenciar potenciais clientes de vendedores que fazem serviço de campo (se locomovem pela cidade), cadastrando potenciais clientes, enviando propostas e agendando visitas

## Funcionalidades

<p>Ao localizar um potencial cliente em qualquer estabelecimento que seja, com os dados do cliente em mãos é possível
- Cadastrar informando os dados
- Localizar a partir do mapa 
- Agendar visita
- Enviar propostas
- Realizar os diversos filtros que desejar</p>

### Layouts
Home             |  Tela Negocios    | Tela de Visitas 
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://github.com/NewtonCesarRoncari/map-zone/blob/develop/map_imgs/home_screen.PNG" width="250" height="560" title="Home"/> | <img src="https://github.com/NewtonCesarRoncari/map-zone/blob/develop/map_imgs/list_business_screen.PNG" width="250" height="560" title="Tela Negocios"/> | <img src="https://github.com/NewtonCesarRoncari/map-zone/blob/develop/map_imgs/list_visit_screen.PNG" width="250" height="560" title="Tela de Visitas"/>

- Além das telas principais também é demonstrado as telas para o cadastro que popula o banco de dados e adiciona os marcadores no mapa, e também a tela de filtros, no qual é possível pesquisar por endereços, nomes, valores entre outros

Cadastro de Lead            |  Filtros   
:-------------------------:|:-------------------------:
<img src="https://github.com/NewtonCesarRoncari/map-zone/blob/develop/map_imgs/register_lead_screen.PNG" width="250" height="560" title="Cadastro de Lead"/> | <img src="https://github.com/NewtonCesarRoncari/map-zone/blob/develop/map_imgs/filter_business_screen.PNG" width="250" height="560" title="Filtros"/>

- Caso queira, é possível realizar o <a href="https://github.com/NewtonCesarRoncari/map-zone/raw/develop/apk/map-zone.rar">download da apk<a/> 

### Bibliotecas

- Material Design
Inicialmente para fins de design foram utilizados as métricas e indicações do Material Design Component para uma melhor visualização e disposição dos componentes utilizados
- Navigation
Como a arquitetura do App foi desenvolvida em cima do MVVM (Model, View, View-Model) e em conjunto com o desenvolvimento single-activity foi escolhido a biblioteca Navigation
do Android Jetpack para flexibilizar a codificação da navegação do aplicativo
- LiveData
Como já explicado em relação a arquitetura MVVM, uma das soluções abordadas foram as LiveDatas, assim otimizando as respostas e o tráfego de informação no aplicativo desenvolvido
- Koin
Atrelado a todos os componentes antes escolhidos para o desenvolvimento da aplicação, biblioteca Koin que é uma biblioteca já desenvolvida em Kotlin para injeção de dependência
foi a escolhida por ter uma configuração otimizada e de manuseio mais performático no momendo da codificação

## Rodando a aplicação

Clone ou realize o download do projeto em formato Zip

### Pré requisitos

Para garantir o bom funcionamento da aplicação rode com: 
- Target JVM 1.8 
- Android Gradle Plugin Version 4.1.1 
- Gradle Version 6.5.1

### Instalando 

Após clonar o projeto, importe no seu Android Studio, aceitando as susjestões da Ide, os pré requisitos serão importados automaticamente,

- Rode a aplicação normalmente

### Informações para apresentação

- Para incluir informações no banco de dados para apresentações (mocks), basta baixar o arquivo de <a href="https://github.com/NewtonCesarRoncari/map-zone/blob/develop/script/mock_zone_map_script.txt">script<a/> e rodar no banco de dados da aplicação 

## Tecnologias utilizadas

- <a href="https://developer.android.com/guide/topics/ui/look-and-feel?hl=pt-br">Material Design<a/> 
- <a href="https://developer.android.com/guide/navigation?gclid=Cj0KCQiAvJXxBRCeARIsAMSkAppbYUXuaVm-tnHPOV9rH5RlVVScLrsUnhHxK-tbmHkYdTBeCDqU6aoaAphrEALw_wcB">Android Navigation</a>
- <a href="https://github.com/airbnb/lottie-android">Lotties</a>
- <a href="https://developer.android.com/topic/libraries/architecture/livedata">Live Data with ViewModel<a/>
- <a href="https://insert-koin.io/">Koin dependency injection<a/>
- <a href="https://site.mockito.org/">Mockito<a/>
