package com.example.fuinha.ksoap2requestmanager.soap;

/**
 * Created by Fuinha on 11/09/2017.
 */

public abstract class MartinsSoapManager {
    private static MartinsSoapManager soapInterface;
    private static String BaseUrl = "http://service.martins.com.br/b2bservice.asmx?WSDL";
    private static String BaseUrlTest = "http://servicemarketup.martins.com.br/b2bservice.asmx";
    private static String NAMESPACE = "http://tempuri.org/";

    static {
    }

    public static MartinsSoapManager getInterface(){
        return soapInterface;
    }

    public static String getURL() {
        // FIXME: 11/09/2017 fix when finish testings
        return BaseUrlTest;
    }

    public static String getNAMESPACE(){
        return NAMESPACE;
    }

    /**
     * Parameters related to Login
     */
//    public static String LOGIN_METHOD = "logarUsuario";
//    public static String LOGIN_ACTION = NAMESPACE + LOGIN_METHOD;
//    public abstract void login(MartinsLoginApi.Request request, Listener<MartinsLoginApi.Response> listener);
//
//    /**
//     * Parameters related to Cadastro
//     */
//    public static String CADASTRO_METHOD = "cadastrarCliente";
//    public static String CADASTRO_ACTION = NAMESPACE + CADASTRO_METHOD;
//    public abstract void cadastrarCliente(MartinsCadastroAPI.Request request, Listener<MartinsCadastroAPI.Response> listener);
//
//    /**
//     * Parameters related to Consulta de preço online
//     */
//    public static String CONSULTA_PRECO_METHOD = "consultarPreco";
//    public static String CONSULTA_PRECO_ACTION = NAMESPACE + CONSULTA_PRECO_METHOD;
//    public abstract void checkPrices(MartinsCheckPrice.Request request, Listener<MartinsCheckPrice.Response> listener);
//
//    /**
//     * Parameters related to Consulta de estoque online
//     */
//    public static String CONSULTA_ESTOQUE_METHOD = "consultarEstoque";
//    public static String CONSULTA_ESTOQUE_ACTION = NAMESPACE + CONSULTA_ESTOQUE_METHOD;
//    public abstract void checkEstoque(MartinsCheckEstoque.Request request, Listener<MartinsCheckEstoque.Response> listener);
//
//    /**
//     * Parameters related to Cadartrar pedido
//     */
//    public static String CADASTRAR_PEDIDO_METHOD = "cadastrarPedido";
//    public static String CADASTRAR_PEDIDO_ACTION = NAMESPACE + CADASTRAR_PEDIDO_METHOD;
//    public abstract void placeOrder(MartinsPlaceOrder.Request request, Listener<MartinsPlaceOrder.Response> listener);
//
//    /**
//     * Parameters related to Tracking do pedido
//     */
//    public static String TRACKING_METHOD = "trankingPedido";
//    public static String TRACKING_ACTION = NAMESPACE + TRACKING_METHOD;
//    public abstract void trackOrder(MartinsTrackOrder.Request request, Listener<MartinsTrackOrder.Response> listener);
//
//    /**
//     * Parameters related to Get Boletos
//     */
//    public static String BOLETOS_METHOD = "consultaBoletosPendente";
//    public static String BOLETOS_ACTION = NAMESPACE + BOLETOS_METHOD;
//    public abstract void getBoletos(MartinsGetBoletos.Request request, Listener<MartinsGetBoletos.Response> listener);
//
//    /**
//     * Parameters related to Get Products Information by Code
//     */
//    public static String INFO_BY_CODE_METHOD = "consultarInfoMercadoriasPorCodigo";
//    public static String INFO_BY_CODE_ACTION = NAMESPACE + INFO_BY_CODE_METHOD;
//    public abstract void getInfoByCode(MartinsGetProdInfoByCode.Request request, Listener<MartinsGetProdInfoByCode.Response> listener);
//
//    /**
//     * Parameters related to Get Products Information by EAN
//     */
//    public static String INFO_BY_EAN_METHOD = "consultarInfoMercadoriasPorEAN";
//    public static String INFO_BY_EAN_ACTION = NAMESPACE + INFO_BY_EAN_METHOD;
//    public abstract void getInfoByEan(MartinsGetProdInfoByEan.Request request, Listener<MartinsGetProdInfoByEan.Response> listener);
//
//    /**
//     * Parameters related to Get DANFE
//     */
//    public static String DANFE_METHOD = "consultarDANFENotaFiscal";
//    public static String DANFE_ACTION = NAMESPACE + DANFE_METHOD;
//    public abstract void getDanfe(MartinsGetDanfe.Request request, Listener<MartinsGetDanfe.Response> listener);

}
