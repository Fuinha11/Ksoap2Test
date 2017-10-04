package com.example.fuinha.ksoap2requestmanager.soap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Fuinha on 19/09/2017.
 */

public class MartinsTokenService {

    private String key;
    private String tksGerado;

    public String getTksGerado() {
        return tksGerado;
    }
    public void setTksGerado(String tksGerado) {
        this.tksGerado = tksGerado;
    }

    public MartinsTokenService(Long codCartao) {
        this.key = codCartao.toString();
        this.tksGerado = " ";
    }

    public String GetToken() {
        return GetToken(new Date());
    }

    public String GetToken(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        //Long hash = this.codigoCartao + new Long(hours);
        String hash = this.key + hours;
        String tkMD5 = this.encrypt(hash);

        hash = tkMD5.substring(29,30) +
                tkMD5.substring(4,5) +
                tkMD5.substring(18,19) +
                tkMD5.substring(23,24) +
                tkMD5.substring(6,7) +
                tkMD5.substring(31,32) +
                tkMD5.substring(11,12) +
                tkMD5.substring(13,14);
        return hash;
    }

    public boolean ValidaToken(String token, Date dataRequisicao) {
        String tktemp = GetToken(dataRequisicao);
        if (token.equals(tktemp))
            return true;

        this.tksGerado = tktemp;
        tktemp += ", " +GetToken(this.AddHours(dataRequisicao,-1));
        if (token.equals(tktemp))
            return true;

        this.tksGerado = tktemp;
        tktemp += ", " + GetToken(this.AddHours(dataRequisicao,1));
        if (token.equals(tktemp))
            return true;

        this.tksGerado = tktemp;
        return false;
    }

    private Date AddHours(Date data, int horas) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.HOUR_OF_DAY, horas);
        return cal.getTime();
    }

    private Date convertDate(String data) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date novaData = null;
        try {
            novaData = df.parse(data);
        } catch (ParseException ex) {
            System.out.println("** Erro ao converter data! Informe o padrao dd/MM/yyyy");
        }
        return novaData;
    }

    public static String encrypt(String sign) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sign.getBytes());
            byte[] hash = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10)
                    hexString.append("0").append(Integer.toHexString((0xFF & hash[i])));
                else
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
            sign = hexString.toString();
        }
        catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
        return sign;
    }

    private static void validaParametros(String[] params) throws Exception {
        if (params == null || params.length == 0) {
            throw new Exception("Tipo da acao nao informada. 1 = Gera Token, 2 = Valida Token");
        }
        if (params[0].equals("1")) {
            if (params.length < 2) {
                throw new Exception("Informe acao = 1 e codigo do cartao");
            }
        }
        else {
            if (params.length < 4) {
                throw new Exception("Informe acao = 2, codigo do cartao, token e data");
            }
        }
    }

    public static void main(String[] args) {
        //args = new String[]{"2","303133153851","asd2323asda","01/01/2016"};

        try {
            validaParametros(args);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        Long codigoCartao = null;
        int acao = 0;
        try {
            acao = Integer.parseInt(args[0]);
            codigoCartao = Long.parseLong(args[1]);
        }catch (Exception e) {
            System.out.println("** Informe acao = 1 ou 2");
            System.out.println("** Informe codigo do cartao valido");
            return;
        }

        MartinsTokenService martinsTokenService = new MartinsTokenService(codigoCartao);

        //acao = 1 Gera Token
        if (acao == 1) {
            System.out.println("** Gerando token do cartao: "+codigoCartao);
            String hash = martinsTokenService.GetToken();
            System.out.println("** Token gerado: "+hash);
        }
        //acao = 2 Valida Token
        else {
            System.out.println("** Valida token do cartao: "+codigoCartao);
            Date dataRequisicao = martinsTokenService.convertDate(args[3]);
            if (dataRequisicao != null) {
                if (martinsTokenService.ValidaToken(args[2], dataRequisicao)) {
                    System.out.println("** Token validado com sucesso!");
                }
                else {
                    System.out.println("** Token invalido!");
                }
                System.out.println("** Tokens gerados: "+ martinsTokenService.getTksGerado());
            }
        }
    }
}
