# Cidade em Dia de Chuva

Global Solution 2026.2 - Programação Orientada a Objetos com Java e Web

Integrantes:
- Gabrielly Barbosa Camilo - RM 563305
- Victor Hugo Santore - RM 562075
- Luccas Rafael Pazzanese - RM 563794

RM da equipe: 563305 + 562075 + 563794 = 1689174

Versão escolhida: web (Thymeleaf)

Parâmetros:
- P1 = 1689174 % 1000 = 174, prefixo das tabelas GS174
- P2 = 10 + 2 * (1689174 % 4) = 14, limiar L = 14 cm
- P3 = (1689174 / 10) % 3 = 2, atributo possuiPiscinao
- P4 = (1689174 / 100) % 3 = 1, trechos ordenados por altitude e depois logradouro
- P5 = (1689174 / 1000) % 2 = 1, exclusão de trecho em cascata

DTOs: um DTO por entidade (BairroDTO, TrechoDTO e LeituraDTO), feitos como classes com Lombok.
