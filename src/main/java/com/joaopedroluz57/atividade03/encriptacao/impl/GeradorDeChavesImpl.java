package com.joaopedroluz57.atividade03.encriptacao.impl;

import com.joaopedroluz57.atividade03.encriptacao.chaves.FalhaGeracaoDeChaves;
import com.joaopedroluz57.atividade03.encriptacao.chaves.GeradorDeChaves;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeradorDeChavesImpl implements GeradorDeChaves {

    private static int TENTATIVAS_CAPTURA_DE_QUADRO = 20;

    private FFmpegFrameGrabber grabber;
    private String ultimaChave = "";

    public void iniciar(String caminhoVideo) throws FalhaGeracaoDeChaves {
        Loader.load(org.bytedeco.opencv.global.opencv_core.class);

        grabber = new FFmpegFrameGrabber(caminhoVideo);
        try {
            grabber.start();
        } catch (Exception e) {
            throw new FalhaGeracaoDeChaves("falha de inicialização: " + e.getMessage());
        }
    }

    public Frame proximoQuadro() throws FalhaGeracaoDeChaves {
        Frame quadro = null;

        try {
            quadro = grabber.grab();
        } catch (Exception e) {
            throw new FalhaGeracaoDeChaves("falha lendo quadro do vídeo: " + e.getMessage());
        }

        return quadro;
    }

    public BufferedImage proximaImagem() throws FalhaGeracaoDeChaves {
        BufferedImage imagem = null;

        Java2DFrameConverter converter = new Java2DFrameConverter();
        int tentativas = 0;
        do {
            tentativas++;

            Frame quadro = proximoQuadro();
            imagem = converter.convert(quadro);
        } while ((imagem == null) && (tentativas <= TENTATIVAS_CAPTURA_DE_QUADRO));
        converter.close();

        return imagem;
    }

    @Override
    public String gerarChave() throws FalhaGeracaoDeChaves {
        ultimaChave = "";
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(proximaImagem(), "jpg", stream);
            byte[] bytes = stream.toByteArray();

            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(bytes);

            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }

            ultimaChave = hexString.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new FalhaGeracaoDeChaves("falha gerando chave: " + e.getMessage());
        }

        return ultimaChave;
    }

    @Override
    public String getUltimaChave() {
        return ultimaChave;
    }

    public void finalizar() throws FalhaGeracaoDeChaves {
        try {
            grabber.stop();
            grabber.release();
        } catch (Exception e) {
            throw new FalhaGeracaoDeChaves("falha lendo quadro do vídeo: " + e.getMessage());
        }
    }

}
