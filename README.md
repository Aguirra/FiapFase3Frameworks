# FiapFase3Frameworks

## Arquivo de COnfiguracao uso Banco ORACLE 

spring.application.name=calorias

# Conexão com o Oracle
spring.datasource.url=jdbc:oracle:thin:@ohostnameOUip:1521:ORCL
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# HikariCP - Configurações de conexão
spring.datasource.hikari.maximum-pool-size=5   # Máximo de 5 conexões simultâneas
spring.datasource.hikari.minimum-idle=2         # Pelo menos 2 conexões ociosas disponíveis
spring.datasource.hikari.idle-timeout=300000    # 5 minutos (300 milissegundos) para fechar conexão ociosa
spring.datasource.hikari.max-lifetime=1800000   # 30 minutos de vida máxima para uma conexão
spring.datasource.hikari.connection-timeout=10000  # 10 segundos para tentar abrir uma nova conexão
spring.datasource.hikari.validation-timeout=3000   # 3 segundos no teste de validação da conexão
spring.datasource.hikari.leak-detection-threshold=15000  # 15 segundos para detectar conexões "vazando"
spring.datasource.hikari.connection-test-query=SELECT 1 FROM DUAL

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=none  # Em produção, não deixar o Hibernate criar/alterar as tabelas
spring.jpa.show-sql=false           # Não mostrar SQL no console em produção
spring.jpa.properties.hibernate.format_sql=false

# Configuração de erro
server.error.include-stacktrace=never
