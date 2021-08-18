package cronapi;

import cronapi.CronapiMetaData;
import cronapi.ParamMetaData;
import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

/**
 * PagamentoPixJuno ...
 *
 * @author Samuel
 * @version 1.0
 * @since 2021-08-18
 *
 */

@CronapiMetaData(categoryName = "Pagamento")
public class PagamentoPixJuno {

/**
 *
 * @param @ParamMetaData
 * @return Var
 */
// Pagamento
public static Var getAccessToken(@ParamMetaData(description = "clientID") Var clientID, @ParamMetaData(description = "clientSecret") Var clientSecret) throws Exception {
 return new Callable<Var>() {

   private Var idempotencyKey = Var.VAR_NULL;
   private Var res = Var.VAR_NULL;
   private Var err = Var.VAR_NULL;

   public Var call() throws Exception {

    idempotencyKey =
    cronapi.util.Operations.generateUUID();

    try {

        res =
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("sucesso",
        Var.VAR_TRUE) , Var.valueOf("dados",
        cronapi.util.Operations.getURLFromOthers(
        Var.valueOf("POST"),
        Var.valueOf("application/x-www-form-urlencoded"),
        Var.valueOf("https://sandbox.boletobancario.com/authorization-server/oauth/token"), Var.VAR_NULL,
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("Authorization",
        Var.valueOf(
        Var.valueOf("Basic ").toString() +
        cronapi.conversion.Operations.StringToBase64(
        Var.valueOf(
        clientID.toString() +
        Var.valueOf(":").toString() +
        clientSecret.toString())).toString()))),
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("grant_type",
        Var.valueOf("client_credentials"))),
        Var.valueOf(""),
        Var.valueOf("BODY"))) , Var.valueOf("idempotency",idempotencyKey));
     } catch (Exception err_exception) {
          err = Var.valueOf(err_exception);

        res =
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("sucesso",
        Var.VAR_FALSE) , Var.valueOf("dados",err) , Var.valueOf("idempotency",idempotencyKey));
     }
    return res;
   }
 }.call();
}

/**
 *
 * @param @ParamMetaData
 * @return Var
 */
// Describe this function...
public static Var getPixKey(@ParamMetaData(description = "clientID") Var clientID, @ParamMetaData(description = "clientSecret") Var clientSecret, @ParamMetaData(description = "resourceToken") Var resourceToken) throws Exception {
 return new Callable<Var>() {

   private Var idempotencyKey = Var.VAR_NULL;
   private Var res = Var.VAR_NULL;
   private Var err = Var.VAR_NULL;
   private Var authRequest = Var.VAR_NULL;
   private Var authData = Var.VAR_NULL;
   private Var accessToken = Var.VAR_NULL;

   public Var call() throws Exception {

    authRequest =
    getAccessToken(clientID, clientSecret);

    authData =
    cronapi.json.Operations.toJson(
    cronapi.json.Operations.getJsonOrMapField(authRequest,
    Var.valueOf("dados")));

    accessToken =
    cronapi.json.Operations.getJsonOrMapField(authData,
    Var.valueOf("access_token"));

    idempotencyKey =
    cronapi.json.Operations.getJsonOrMapField(authRequest,
    Var.valueOf("idempotency"));

    try {

        res =
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("sucesso",
        Var.VAR_TRUE) , Var.valueOf("dados",
        cronapi.util.Operations.getURLFromOthers(
        Var.valueOf("POST"),
        Var.valueOf("application/json"),
        Var.valueOf("https://sandbox.boletobancario.com/api-integration/pix/keys"), Var.VAR_NULL,
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("Authorization",
        Var.valueOf(
        Var.valueOf("Bearer ").toString() +
        accessToken.toString())) , Var.valueOf("X-Idempotency-Key",idempotencyKey) , Var.valueOf("X-Resource-Token",resourceToken) , Var.valueOf("X-Api-Version",
        Var.valueOf("2"))),
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("type",
        Var.valueOf("RANDOM_KEY"))),
        Var.valueOf(""),
        Var.valueOf("BODY"))));
     } catch (Exception err_exception) {
          err = Var.valueOf(err_exception);

        res =
        cronapi.map.Operations.createObjectMapWith(Var.valueOf("sucesso",
        Var.VAR_FALSE) , Var.valueOf("dados",err));
     }
    return res;
   }
 }.call();
}

}