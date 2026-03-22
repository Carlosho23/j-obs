# LinkedIn Posts — J-Obs v1.0.26

Textos prontos para divulgação do J-Obs no LinkedIn.

**Estratégia de publicação:**
1. Post 3 (Storytelling) primeiro — maior alcance
2. Post 1 (Lançamento) 3 dias depois — feature announcement
3. Post 2 (Técnico) na semana seguinte — público dev Java

**Dicas:**
- Link do GitHub nos **comentários**, não no corpo do post
- Poste entre 8h-9h (terça a quinta)
- Responda todo comentário nas primeiras 2h
- Adicione 1-2 screenshots do dashboard (pasta `assets/`)

---

## Post 1 — Lançamento (principal)

```
Acabei de lançar a v1.0.26 do J-Obs — agora production-ready.

J-Obs é uma dependência Java que adiciona observabilidade completa
em qualquer aplicação Spring Boot. Uma linha no pom.xml, zero
infraestrutura externa.

O que mudou nessa versão:

📦 Persistência JDBC
Traces, alertas e SLOs agora podem ser persistidos em PostgreSQL/H2.
Ativa com j-obs.persistence.enabled=true. Sem banco? Continua in-memory.

🔒 Security by default
Validação de config no startup (fail-fast se mal configurado).
WebSocket autenticado. Error boundaries nos aspects — J-Obs nunca
derruba sua aplicação.

📊 Trace Sampling
Taxa configurável de 0.0 a 1.0 com hash determinístico.
1000 req/s? Amostra 10% e seu dashboard continua útil.

👥 RBAC
3 roles: ADMIN, OPERATOR, VIEWER.
Configurável por usuário no application.yml.

⚡ Performance
Cache de metadata nos aspects AOP — reflection executada uma vez
por método, não a cada chamada.

296 testes automatizados. 82 E2E. Zero falhas.

Se você trabalha com Spring Boot, testa e me diz o que achou.

#Java #SpringBoot #Observability #OpenSource #OpenTelemetry
```

---

## Post 2 — Técnico (para devs Java)

```
Quanto overhead uma lib de observabilidade pode adicionar
à sua aplicação Spring Boot?

Quando criei o J-Obs, cada método instrumentado com @Observable
fazia:
→ method.getAnnotation() (reflection)
→ method.getParameters() (reflection)
→ String parsing dos atributos
→ Criação do span

Em CADA chamada. A 1000 req/s com 15 services instrumentados,
são 15.000 reflection calls por segundo.

Na v1.0.26, adicionei ConcurrentHashMap<Method, MethodMetadata>
nos aspects. Reflection executa UMA vez por método, o resultado
é cacheado.

Também adicionei:
→ TraceSampler com hash determinístico (mesmo traceId = mesma
  decisão em todos os serviços)
→ Error boundaries — se o span falha, o método original executa
  normalmente
→ MAX_METRICS = 10K com eviction (previne memory leak em apps
  de longa duração)

O resultado? Overhead de ~0.1ms por método ao invés de ~2-5ms.

Detalhes no release: github.com/JohnPitter/j-obs

#Java #Performance #SpringBoot #OpenTelemetry #AOP
```

---

## Post 3 — Storytelling (para alcance maior)

```
"Não precisa ser perfeito pra ser útil."

Há 2 meses lancei o J-Obs — uma lib Java que adiciona
observabilidade em Spring Boot com uma dependência.

O feedback: "Legal, mas não roda em produção."

Eles tinham razão.

Dados in-memory (restart = perda total).
Sem autenticação por default.
Sem sampling pra alto tráfego.
Sem controle de acesso.

Então nos últimos dias, implementei tudo:

✅ Persistência JDBC (PostgreSQL/H2)
✅ Validação de config no startup
✅ WebSocket autenticado
✅ Trace sampling configurável
✅ RBAC (Admin/Operator/Viewer)
✅ Data retention automático
✅ 296 testes (82 E2E)

33 arquivos alterados. +2.390 linhas.

A lib ainda é zero-config por default:
→ Sem banco? Roda in-memory.
→ Sem security? Warning no startup.
→ Sample rate 1.0? Captura tudo.

Mas agora, se você quiser usar em produção,
basta configurar.

Link nos comentários.

O que você acha que ainda falta?

#Java #SpringBoot #OpenSource #Observability #BuildInPublic
```
