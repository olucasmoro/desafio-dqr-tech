# Desafio DQR Tech

## Features

### Obrigatórias:

-   [X] As taxas de câmbio disponíveis devem ser obtidas da chamada de [API Supported Currencies (/list)](https://currencylayer.com/documentation)
-   [X] A cotação atual deve ser obtida da chamada de [API Real-time Rates (/live)](https://currencylayer.com/documentation)
-   [ ] É necessário fazer tratamento de erros e dos fluxos de exceção, como busca vazia, carregamento e outros erros que possam ocorrer.
-   [X] Funcionalidade de busca na lista de moedas por nome ou sigla da moeda ("dólar" ou "USD").
-   [X] Ordenação da lista de moedas por nome ou código.
#### Estes dois últimos itens são importantíssimos. Precisam estar presentes.

### Opcionais (não necessário, porém contam pontos):

-   [X] Realizar a persistência local da lista de moedas e taxas para permitir o uso do app no caso de falta de internet.
-   [ ] Desenvolver testes unitários e/ou funcionais.
-   [X] Desenvolver o app seguindo a arquitetura MVVM.
-   [X] Pipeline automatizado.
