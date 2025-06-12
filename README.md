# Arvore Pessoa AVL

## Integrantes: Thais Landfeldt, Victório Faraco, João Trajano

# Como funciona
#### O programa é executado a partir da classe `Menu.java` possuindo uma  simples interface no log para melhor visualização

## **Classes Usadas** 
#### Tivemos como classes importantes para esse projeto um pilar bem sólido destas 3 classes destacadas

```mermaid
classDiagram
    class Arvore {
        +raiz: No
        +notEmpty: boolean
        -Funções de balancear()
        -Funções de inserir()
        -Funções de Procurar()
    }

    class Pessoa {
        +rg: String
        +cpf: String
        +cidadeNascimento: String
        +nome: String
        +nascimento: LocalDate
        -toString()
    }
    class No {
        +altura: int
        +ponto: int
        +valor: int
        +esquerda: No
        +direita: No
        +pai: No
        +chave: T
        -toString()
    }
```
#### E também algumas funções de utilitários como as:

```mermaid
classDiagram
    class LeituraDeArquivos{
        +delimitador: char = ';'
        -Funções para ler o arquivo()
    }
    class OrdenaçãoUtil{
        -Funções de ordenação()
    }
```

## Execução
#### O programa executa através da Classe Menu que faz uma execução no Console java. Aqui uma pequena demonstração de um fluxo no programa:

```mermaid
sequenceDiagram
    participant Console
    participant Opções
    participant Execução
    Console->>Opções: Procura pessoa por CPF
    Opções->>Execução: Faz a busca nas árvore CPF
    Execução->>Console: Retorna o alvo da busca
```

## Conclusão
#### Os modelos de Estruturas conhecidos como árvores possuem uma grande vantagem organizativa para buscas e consultas de `N` tipos de dados, e alguns modelos de árvores possuem vantagens para diferentes tipos de dados e funções a serem exercidas
![Obrigado pela atenção](https://tenor.com/view/garfield-dance-jump-gif-11505863656034024388)