package uga.framework.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import uga.framework.exception.UgaException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Json实用类，扩展封装Jackson.
 *
 * @author Peter Wei edited by lingyl
 * @since 2011
 * @version 1.0.0
 */
public class Json2Utils {


    private static final SimpleModule DOUBLE_SERIALIZER_MODULE = doubleSerializerModule();

    private static SimpleModule doubleSerializerModule() {
		SimpleModule module = new SimpleModule("CustomModule", Version.unknownVersion());
		module.addSerializer(Double.class, new JsonSerializer<Double>(){
			@Override
			public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
				if(value == null)
					gen.writeNull();
				else{
					BigDecimal num = new BigDecimal(value.toString());
					gen.writeNumber(num.toPlainString());
				}

			}

		});
		return module;
	}

	private static final JavaTimeModule JAVA_TIME_MODULE = javaTimeModule();

    private static JavaTimeModule javaTimeModule() {
		JavaTimeModule timeModule = new JavaTimeModule();
		timeModule.addSerializer(LocalDateTime.class, UcaLocalDateTimeSerializer.INSTANCE);
		timeModule.addDeserializer(LocalDateTime.class, UcaLocalDateTimeDeserializer.INSTANCE);
		return timeModule;
	}

    /**
     * 是否打印美观格式
     */
    private final static boolean isPretty = true;

    private static final ObjectMapper DEFAULT_OBJECT_MAPPER = newObjectMapper();

    public static ObjectMapper newObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		objectMapper.registerModule(DOUBLE_SERIALIZER_MODULE);
		objectMapper.registerModule(JAVA_TIME_MODULE);
		return objectMapper;
	}

	public static ObjectMapper defaultObjectMapper() {
		return DEFAULT_OBJECT_MAPPER;
	}

	/**
	 * Added by andy.lv on 20171130
	 */
	private static class UcaLocalDateTimeSerializer extends LocalDateTimeSerializer {

		public static final LocalDateTimeSerializer INSTANCE = new UcaLocalDateTimeSerializer(LocalDateTimeUtils.DATE_TIME_FORMATTER);

		protected UcaLocalDateTimeSerializer(DateTimeFormatter dtf) {
			super(dtf);
		}
	}

	/**
	 * Added by andy.lv on 20171130
	 */
	private static class UcaLocalDateTimeDeserializer extends LocalDateTimeDeserializer {

		public static final UcaLocalDateTimeDeserializer INSTANCE = new UcaLocalDateTimeDeserializer(LocalDateTimeUtils.DATE_TIME_FORMATTER);

		protected UcaLocalDateTimeDeserializer(DateTimeFormatter dtf) {
			super(dtf);
		}
	}


    /**
     * Java对象转Json字符串
     * 
     * @param object Java对象，可以是对象，数组，List,Map等
     * @return json 字符串
     * @ 
     */
	public static String toJson(Object object){
    	try{
    		String jsonString = "";
            if (isPretty) {
                jsonString = defaultObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } else {
                jsonString = defaultObjectMapper().writeValueAsString(object);
            }

            return jsonString;
    	}catch(Exception e){
    		throw new UgaException(e);
    	}
        
 
    }
 
    /**
     * Json字符串转Java对象
     * 
     * @param jsonString
     * @param c
     * @return
     * @ 
     */
    public static <T> T json2Object(String jsonString, Class<T> c){
    	try{
    		 if (jsonString == null || "".equals(jsonString)) {
    	            return null;
    	        } else {
    	        	if(jsonString.endsWith("/r/n")) {
    					jsonString = jsonString.substring(0, jsonString.length() - "/r/n".length());
    				}
    				return defaultObjectMapper().readValue(jsonString, c);
    	        }
    	}catch(Exception e){
    		throw new UgaException(e);
    	}
       
    }

}