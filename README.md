[![Build Status](https://jenkins-apps-web-cicd.lab-originpaas.mecon.ar/buildStatus/icon?build=lastCompleted&config=prueba&style=plastic&job=apps-web-cicd%2Fapps-web-cicd-prueba-workflow-pipeline)](https://jenkins-apps-web-cicd.lab-originpaas.mecon.ar/job/apps-web-cicd/job/apps-web-cicd-prueba-workflow-pipeline/)

# Configuración a ser modificada cuando se use este starter para una aplicación

 - Revisar versiones de los frameworks (springboot, quarkus, angular, etc) y en tal caso actualizar a las versiones LTS
 - Revisar versión del wrapper de Gradle.
 - En caso de usar liquibase, borrar (si es necesario): **liquibaseRuntime 'com.h2database:h2'** en **liquibase.gradle**

# Trabajando en el Backend

**¿ Usas Eclipse ?**  
- Importá como proyecto Gradle la carpeta backend.

**¿ Usas VS Code ?**
- Si el SO es Windows 10, descargar WSL https://ubuntu.com/wsl. Abrir una terminal WSL. Ejecutar los siguientes comandos.
- Para inicializar la cache de packages (pide clave root): > sudo apt update
- Descargar el package zip (necesario para el siguiente paso): > sudo apt install zip
- Descargar el package unzip (necesario para el siguiente paso): > sudo apt install unzip
- Instalar scripts sdkman, una utilidad para instalar java y gradle: > curl -s "https://get.sdkman.io" | bash
- Instalar el package de Java: > sdk install java 11.0.12-open
- Instalar el package gradle: > sdk install gradle
- Descargar el proyecto de GitLab a una carpeta local: > git clone https://dgsiaf-gitlab.mecon.ar/presupuesto-consultas-code/presupuesto-consultas-code.git
- Comando por si al clonar falla la verificación de certificado del servidor: > git config --global http.sslverify "false"
- Ir a la carpeta descargada **presupuesto-consultas-code** y crear un nuevo branch: > git checkout -b <branch_name>
- Desde la carpeta **presupuesto-consultas-code** abrir el VS Code (el punto es intencional): > code .
- Ir a Extensions (Ctrl+Shift+X) e instalar: 
    - Remote - WSL
    - Extension Pack for Java
    - Gradle for java

## Codificacion y Formatter
### Eclipse IDE
- Enconding: UTF-8. Menú Project -> Properties -> Resource
- Line Delimiter: Unix (LF). Menú Project -> Properties -> Resoure
### VS Code IDE
- Encoding: UTF-8. Menú File -> Preferences -> Settings, User -> Text Editor -> Files, sección Encoding=UTF-8.
- Line Delimiter: Unix (LF). Menú File -> Preferences -> Settings, User -> Text Editor -> Files, sección Eol=\n  
Notar que en la barra inferior, a la derecha indica y se puede cambiar la configuración del editor actual, el Encoding y a la derecha el valor de Eol [LF | CRLF]