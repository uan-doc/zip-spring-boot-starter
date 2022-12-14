# zip-spring-boot-starter
Starter do Spring Boot para Zip e Unzip de arquivos.

Basta adicionar no pom.xml e começar a usar o serviço ZipTemplate.

Obs.: Para o spring-boot 3.0.0 foi necessário adicionar o ZipAutoConfiguration.class no @Import da classe principal do serviço, ex.: 

<code>
@Import({ ZipAutoConfiguration.class })
public class DocumentExportServerApplication ...
</code>
