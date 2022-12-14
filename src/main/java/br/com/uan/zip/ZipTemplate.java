package br.com.uan.zip;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

// Não deve ser um componente, deve ser instanciado manualmente como no ZipAutoConfiguration.java
// @Component
public class ZipTemplate
{
    protected Logger log = LoggerFactory.getLogger(ZipTemplate.class);

    /**
     * Verifica se o arquivo informado é um um zip, se não for, retorna null, se
     * sim, já retorna a referência para o arquivo Zip.
     */
    public ZipFile isZipFile(File file) {
        log.info("Verificando se o arquivo {} é um ZIP...", file.getPath());
        try {
            ZipFile zipFile = new ZipFile(file);

            if (zipFile.isValidZipFile()) {
                log.debug("Ele é um ZIP");
                return zipFile;
            }
        }
        catch (Exception e) {
            log.error("Ele não é um ZIP: {}", e.getMessage(), e);
        }

        return null;
    }

    public ZipFile isZipFile(String fileName) {
        return isZipFile(new File(fileName));
    }

    public void unzip(ZipFile zipFile, String password, String directoryDestination) throws ZipException, InvalidParameterException {
        unzip(zipFile, password, new File(directoryDestination));
    }

    public void unzip(File zipFile, File directoryDestination) throws ZipException, InvalidParameterException {
        unzip(zipFile, null, directoryDestination);
    }

    public void unzip(File zipFile, String password, File directoryDestination) throws ZipException, InvalidParameterException {
        unzip(isZipFile(zipFile), password, directoryDestination);
    }

    public void unzip(ZipFile zipFile, String password, File directoryDestination) throws ZipException, InvalidParameterException {

        /*
         * Verificando primeiramente se o diretório é válido.
         */
        if (!directoryDestination.exists() || !directoryDestination.isDirectory()) {
            String msg = "Diretório informado inválido.";
            log.error(msg);
            throw new InvalidParameterException(msg);
        }

        /*
         * Informando o password se necessário.
         */
        if (zipFile.isEncrypted() && password != null) {
            zipFile.setPassword(password.toCharArray());
        }

        /*
         * Extraindo os arquivos para o diretório informado.
         */
        log.info("Extraindo o conteúdo do ZIP {}", zipFile.getFile().getPath());
        zipFile.extractAll(directoryDestination.getAbsolutePath());
    }

    public void zip(File destination, List<File> filesToZip) throws ZipException, IOException {
        zip(destination, filesToZip, null);
    }

    /**
     * Cria um arquivo ZIP a partir dos arquivos informados.
     */
    public void zip(File destination, List<File> filesToZip, List<String> fileNames) throws ZipException, IOException {
        try (ZipFile result = new ZipFile(destination)) {
            for (int i = 0; i < filesToZip.size(); i++) {
                ZipParameters parameters = new ZipParameters();
                parameters.setCompressionMethod(CompressionMethod.DEFLATE);
                parameters.setCompressionLevel(CompressionLevel.ULTRA);
                parameters.setFileNameInZip(fileNames == null ? filesToZip.get(i).getName() : fileNames.get(i));

                result.addFile(filesToZip.get(i), parameters);
            }
        }
    }
}
