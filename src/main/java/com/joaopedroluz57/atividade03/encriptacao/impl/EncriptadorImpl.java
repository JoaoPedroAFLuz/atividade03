package com.joaopedroluz57.atividade03.encriptacao.impl;


import com.joaopedroluz57.atividade03.encriptacao.chaves.FalhaGeracaoDeChaves;
import com.joaopedroluz57.atividade03.encriptacao.encriptador.Encriptador;
import com.joaopedroluz57.atividade03.encriptacao.encriptador.FalhaEncriptacao;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncriptadorImpl extends Encriptador<GeradorDeChavesImpl> {

    private static final String ALGORITMO_DE_ENCRIPTACAO = "AES";

    public EncriptadorImpl(GeradorDeChavesImpl geradorDeChaves) {
        super(geradorDeChaves);
    }

    private byte[] chaveToBytes(String chave) {
        int len = chave.length();

        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(chave.charAt(i), 16) << 4)
                    + Character.digit(chave.charAt(i + 1), 16));
        }

        return bytes;
    }

    public String encriptar(String dados) throws FalhaEncriptacao {
        String chave = gerador.getUltimaChave();

        String encriptacao = null;
        try {
            Cipher cifrador = Cipher.getInstance(ALGORITMO_DE_ENCRIPTACAO);
            cifrador.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chaveToBytes(chave), ALGORITMO_DE_ENCRIPTACAO));

            byte[] cifragem = cifrador.doFinal(dados.getBytes(StandardCharsets.UTF_8));
            encriptacao = Base64.getEncoder().encodeToString(cifragem);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            throw new FalhaEncriptacao("falha encriptando dados: " + e.getMessage());
        }

        return encriptacao;
    }

    public String desencriptar(String encriptacao) throws FalhaEncriptacao {
        String chave = gerador.getUltimaChave();

        String dados = null;
        try {
            Cipher cifrador = Cipher.getInstance(ALGORITMO_DE_ENCRIPTACAO);
            cifrador.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chaveToBytes(chave), ALGORITMO_DE_ENCRIPTACAO));

            byte[] bytes = Base64.getDecoder().decode(encriptacao);
            byte[] bytesDecifrados = cifrador.doFinal(bytes);

            dados = new String(bytesDecifrados, StandardCharsets.UTF_8);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            throw new FalhaEncriptacao("falha desencriptando dados: " + e.getMessage());
        }

        return dados;
    }

}
