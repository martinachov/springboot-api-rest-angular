# Openshift APP Starter - AXON

CQRS, Event Sourcing y DDD con [Axon framework](https://axoniq.io/product-overview/axon)

# Axon server local

Tenemos 2 formas de levantar el server de Axon localmente:

1.  Docker: ``docker run --name axon-server -p 8024:8024 -p 8124:8124 axoniq/axonserver``

2. Boot jar:

    - Descargar el zip de [Axon Server](https://download.axoniq.io/axonserver/AxonServer.zip)
    - Ejecutar el jar: java -jar axonserver.jar

Referencia: https://axoniq.io/download

# Aplicación 

- ``./gradlew bootRun --args='--spring.profiles.active=local'``

- Desde Swagger se pueden ver los endpoints disponible: ``http://localhost:8080/swagger-ui.html``

# Consola H2

``http://localhost:8080/h2-console``

# Construir en OKD

1. ./gradlew assemble
2. oc start-build \<build-name\> --from-dir=build/libs/ --follow -n \<namspace\>


#Lecturas recomendadas

- https://docs.axoniq.io/reference-guide/architecture-overview/ddd-cqrs-concepts
- https://docs.axoniq.io/reference-guide/axon-framework/messaging-concepts
- https://docs.axoniq.io/reference-guide/axon-framework/axon-framework-commands/command-handlers
- https://docs.axoniq.io/reference-guide/axon-framework/events
- https://docs.axoniq.io/reference-guide/axon-framework/sagas/implementation
- https://docs.axoniq.io/reference-guide/axon-framework/testing