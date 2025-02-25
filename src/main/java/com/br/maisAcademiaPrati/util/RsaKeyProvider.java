package com.br.maisAcademiaPrati.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Getter
@Component
public class RsaKeyProvider {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    // Construtor que inicializa as chaves RSA.
    public RsaKeyProvider() throws Exception {
        this.privateKey = loadPrivateKey();
        this.publicKey = loadPublicKey();

//        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
//        this.privateKey = keyPair.getPrivate();
//        this.publicKey = keyPair.getPublic();
//
//        try {
//            savePrivateKey(privateKey, "src/main/resources/keys/private.pem");
//            savePublicKey(publicKey, "src/main/resources/keys/public.pem");
//        } catch (IOException e) {
//            // Lide com a exceção de acordo com a sua aplicação
//            System.err.println("Erro ao salvar chaves para arquivos: " + e.getMessage());
//            // Pode ser necessário lançar a exceção novamente ou tomar outra medida.
//            throw new RuntimeException("Falha ao salvar chaves", e);
//        }
    }

    // Método para carregar a chave privada do arquivo "private.pem".
    private PrivateKey loadPrivateKey() throws Exception {
        InputStream is = new ClassPathResource("keys/private.pem").getInputStream();
        byte[] keyBytes = is.readAllBytes();

        // Converte para string e remove cabeçalhos e rodapés desnecessários.
        String keyString = new String(keyBytes, StandardCharsets.UTF_8)
                .trim()
                .replaceAll("-----BEGIN (?:RSA )?PRIVATE KEY-----", "")
                .replaceAll("-----END (?:RSA )?PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        // Decodifica a chave Base64.
        byte[] decoded = Base64.getDecoder().decode(keyString);

        // Cria uma especificação de chave privada no formato PKCS#8.
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);

        // Obtém uma instância de KeyFactory para RSA e gera a chave privada.
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // Método para carregar a chave pública do arquivo "public.pem".
    private PublicKey loadPublicKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(new ClassPathResource("keys/public.pem").getURI()));

        // Converte para string e remove cabeçalhos e rodapés desnecessários.
        String keyString = new String(keyBytes)
                .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", ""); // Remove espaços e quebras de linha.

        // Decodifica a chave Base64.
        byte[] decoded = Base64.getDecoder().decode(keyString);

        // Cria uma especificação de chave pública no formato X.509.
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);

        // Obtém uma instância de KeyFactory para RSA e gera a chave pública.
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }


    // Método auxiliar para salvar a chave privada em arquivo.
//    private void savePrivateKey(PrivateKey privateKey, String filename) throws IOException {
//        try (FileOutputStream fos = new FileOutputStream(filename)) {
//            String privateKeyContent = "-----BEGIN PRIVATE KEY-----\n" +
//                    Base64.getEncoder().encodeToString(privateKey.getEncoded()) +
//                    "\n-----END PRIVATE KEY-----";
//            fos.write(privateKeyContent.getBytes(StandardCharsets.UTF_8));
//        }
//    }

    // Método auxiliar para salvar a chave pública em arquivo.
//    private void savePublicKey(PublicKey publicKey, String filename) throws IOException {
//        try (FileOutputStream fos = new FileOutputStream(filename)) {
//            String publicKeyContent = "-----BEGIN PUBLIC KEY-----\n" +
//                    Base64.getEncoder().encodeToString(publicKey.getEncoded()) +
//                    "\n-----END PUBLIC KEY-----";
//            fos.write(publicKeyContent.getBytes(StandardCharsets.UTF_8));
//        }
//    }

}