package bg.sirma.ims.serializer;

import bg.sirma.ims.model.payment.PaymentMethod;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PaymentMethodDeserializer implements JsonDeserializer<PaymentMethod> {
    private String paymentMethodTypeElementName;
    private Map<String, Class<? extends PaymentMethod>> methodTypeRegistry;
    private Gson gson;

    public PaymentMethodDeserializer(String paymentMethodTypeElementName) {
        this.paymentMethodTypeElementName = paymentMethodTypeElementName;
        this.methodTypeRegistry = new HashMap<>();
        this.gson = new Gson();
    }

    public PaymentMethodDeserializer registerMethodType(String methodTypeName, Class<? extends PaymentMethod> methodType) {
        methodTypeRegistry.put(methodTypeName, methodType);
        return this;
    }

    @Override
    public PaymentMethod deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject paymentMethodObject = jsonElement.getAsJsonObject();
        JsonElement methodTypeElement = paymentMethodObject.get(paymentMethodTypeElementName);
        Class<? extends PaymentMethod> methodType = methodTypeRegistry.get(methodTypeElement.getAsString());
        return gson.fromJson(paymentMethodObject, methodType);
    }
}
