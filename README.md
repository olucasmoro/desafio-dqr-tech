# Conversor
App de conversão de moedas que permiti que o usuário selecione a moeda de origem e a moeda a ser convertida, para então inserir o valor e visualizar o resultado da conversão.

## Features

### Obrigatórias:

-   [X] As taxas de câmbio disponíveis devem ser obtidas da chamada de [API Supported Currencies (/list)](https://currencylayer.com/documentation)
-   [X] A cotação atual deve ser obtida da chamada de [API Real-time Rates (/live)](https://currencylayer.com/documentation)
-   [X] É necessário fazer tratamento de erros e dos fluxos de exceção, como busca vazia, carregamento e outros erros que possam ocorrer.
-   [X] Funcionalidade de busca na lista de moedas por nome ou sigla da moeda ("dólar" ou "USD").
-   [X] Ordenação da lista de moedas por nome ou código.
-   [X] Realizar a persistência local da lista de moedas e taxas para permitir o uso do app no caso de falta de internet.
-   [X] Desenvolver o app seguindo a arquitetura MVVM.
-   [X] Pipeline automatizado.
-   [ ] Desenvolver testes unitários e/ou funcionais.